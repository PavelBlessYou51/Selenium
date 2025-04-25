import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task10Tests extends BaseTests{

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    public void priceTest(String browser) {
        init("http://localhost/litecart/en/", browser);
        // проверяем внешние элементы
        String outerTitle = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        WebElement usualPriceElem = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        Assertions.assertTrue(usualPriceElem.getCssValue("text-decoration").contains("line-through"));
        Assertions.assertTrue(usualPriceElem.getCssValue("color").contains("119, 119, 119"));
        String usualOuterPrice = usualPriceElem
                .getText()
                .substring(1);
        WebElement discountPriceElem = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        Assertions.assertTrue(discountPriceElem.getCssValue("font-weight").contains("900"));
        Assertions.assertTrue(discountPriceElem.getCssValue("color").contains("204, 0, 0"));
        String discountOuterPrice = discountPriceElem
                .getText()
                .substring(1);
        Assertions.assertTrue(Integer.parseInt(usualOuterPrice) > Integer.parseInt(discountOuterPrice));
        driver.findElement(By.cssSelector("#box-campaigns a")).click();
        // проверяем внутренние элементы
        String innerTitle = driver.findElement(By.cssSelector("h1")).getText();
        Assertions.assertEquals(outerTitle, innerTitle);
        WebElement usualPriceInnerElem = driver.findElement(By.className("regular-price"));
        Assertions.assertTrue(usualPriceInnerElem.getCssValue("text-decoration").contains("line-through"));
        Assertions.assertTrue(usualPriceInnerElem.getCssValue("color").contains("102, 102, 102"));
        String usualInnerPrice = usualPriceInnerElem
                .getText()
                .substring(1);
        WebElement discountPriceInnerElem = driver.findElement(By.className("campaign-price"));
        Assertions.assertTrue(discountPriceInnerElem.getCssValue("font-weight").contains("700"));
        Assertions.assertTrue(discountPriceInnerElem.getCssValue("color").contains("204, 0, 0"));
        String discountInnerPrice = discountPriceInnerElem
                .getText()
                .substring(1);
        Assertions.assertTrue(Integer.parseInt(usualInnerPrice) > Integer.parseInt(discountInnerPrice));
        quit();
    }
}
