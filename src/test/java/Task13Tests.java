import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
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
    private static WebDriverWait waitTime;

    @Test
    public void addCartTest(){
        init("http://localhost/litecart/en/", "chrome");
        createWaits();
        getDucks();
        goToCart();
        String message = dleteDucksFromCart();
        Assertions.assertEquals("There are no items in your cart.", message);
        quit();
    }

    private String dleteDucksFromCart() {
        while(!driver.findElements(By.cssSelector("li.item")).isEmpty()) {
            try {
                WebElement elem = waitTime.until(ExpectedConditions.presenceOfElementLocated(By.name("remove_cart_item")));
                waitTime.until(ExpectedConditions.visibilityOf(elem)).click();
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
        waitTime.until(ExpectedConditions.invisibilityOfElementLocated(By.className("viewport")));
        String message = waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("em"))).getText();
        return message;
    }

    private void goToCart() {
        click(By.xpath("//a[contains(text(), 'Checkout')]"));
    }

    private void getDucks() {
        while(count < 2) {
            count = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            click(By.cssSelector("#box-most-popular .link"));
            if(!driver.findElements(By.name("options[Size]")).isEmpty()) {
                Select select = new Select(driver.findElement(By.name("options[Size]")));
                select.selectByValue("Small");
            }
            click(By.name("add_cart_product"));
            waitTime.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(count + 1)));
            click(By.cssSelector("[title='Home']"));
        };
    }

    private void createWaits() {
        waitTime = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
