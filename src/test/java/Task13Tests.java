import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task13Tests extends BaseTests{
    private static int count;

    @Test
    public void addCartTest(){
        init("http://localhost/litecart/en/", "chrome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // набираем уток
        while(count < 2) {
            count = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            click(By.cssSelector("#box-most-popular .link"));
            if(!driver.findElements(By.name("options[Size]")).isEmpty()) {
                Select select = new Select(driver.findElement(By.name("options[Size]")));
                select.selectByValue("Small");
            }
            click(By.name("add_cart_product"));
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(count + 1)));
            click(By.cssSelector("[title='Home']"));
        }
        click(By.xpath("//a[contains(text(), 'Checkout')]")); // переходим в корзину
        // удаляем уток
        while(!driver.findElements(By.cssSelector("li.item")).isEmpty()) {
            try {
                WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("remove_cart_item")));
                wait.until(ExpectedConditions.visibilityOf(elem)).click();
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("em"))).getText();
        Assertions.assertEquals("There are no items in your cart.", message); // проверяем, что все утки удалены
    }
}
