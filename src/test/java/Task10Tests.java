import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class Task10Tests extends BaseTests{

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    public void priceTest(String browser) {
        init("http://localhost/litecart/en/", browser);
        // проверяем внешние элементы
        String outerTitle = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        WebElement usualPriceElem = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        Assertions.assertTrue(usualPriceElem.getCssValue("text-decoration").contains("line-through"));
        Color usualPriceColor = Color.fromString(usualPriceElem.getCssValue("color"));
        int redUs = usualPriceColor.getColor().getRed();
        int greenUs = usualPriceColor.getColor().getGreen();
        int blueUs = usualPriceColor.getColor().getBlue();
        Assertions.assertTrue(redUs == greenUs && greenUs == blueUs);
        String usualOuterPrice = usualPriceElem
                .getText()
                .substring(1);
        WebElement discountPriceElem = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        Assertions.assertTrue(discountPriceElem.getCssValue("font-weight").contains("900"));
        Color discountPriceColor = Color.fromString(discountPriceElem.getCssValue("color"));
        int greenDis = discountPriceColor.getColor().getGreen();
        int blueDis = discountPriceColor.getColor().getBlue();
        Assertions.assertEquals(0, greenDis + blueDis);
        String discountOuterPrice = discountPriceElem
                .getText()
                .substring(1);
        Assertions.assertTrue(Integer.parseInt(usualOuterPrice) > Integer.parseInt(discountOuterPrice)); // проверяем, что акционная цена меньше
        driver.findElement(By.cssSelector("#box-campaigns a")).click();
        // проверяем внутренние элементы
        String innerTitle = driver.findElement(By.cssSelector("h1")).getText();
        Assertions.assertEquals(outerTitle, innerTitle);
        WebElement usualPriceInnerElem = driver.findElement(By.className("regular-price"));
        Assertions.assertTrue(usualPriceInnerElem.getCssValue("text-decoration").contains("line-through"));
        Color usualPriceInnerColor = Color.fromString(usualPriceInnerElem.getCssValue("color"));
        int redInner = usualPriceInnerColor.getColor().getRed();
        int greenInner = usualPriceInnerColor.getColor().getGreen();
        int blueInner = usualPriceInnerColor.getColor().getBlue();
        Assertions.assertTrue(redInner == greenInner && greenInner == blueInner);
        String usualInnerPrice = usualPriceInnerElem
                .getText()
                .substring(1);
        WebElement discountPriceInnerElem = driver.findElement(By.className("campaign-price"));
        Assertions.assertTrue(discountPriceInnerElem.getCssValue("font-weight").contains("700"));
        Color discountPriceInnerColor = Color.fromString(discountPriceInnerElem.getCssValue("color"));
        int greenDiscountInner = discountPriceInnerColor.getColor().getGreen();
        int blueDiscountInner = discountPriceInnerColor.getColor().getBlue();
        Assertions.assertEquals(0, greenDiscountInner + blueDiscountInner);
        String discountInnerPrice = discountPriceInnerElem
                .getText()
                .substring(1);
        Assertions.assertTrue(Integer.parseInt(usualInnerPrice) > Integer.parseInt(discountInnerPrice)); // проверяем, что акционная цена меньше
        quit();
    }
}
