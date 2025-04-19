import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LessonFourTask7Tests {
    public static WebDriver driver;

    @BeforeAll
    public static void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void checkStickers() {
        List<WebElement> cards = driver.findElements(By.className("li.product.column.shadow.hover-light"));
        int count = 0;
        for(WebElement card : cards) {
            Assertions.assertTrue(check(card));
            count++;
        }
        Assertions.assertEquals(cards.size(), count);

    }

    public boolean check(WebElement elem) {
        return elem.findElements(By.cssSelector("[class^=sticker]")).size() == 1;
    }


    @AfterAll
    public static void quit() {
        driver.quit();
    }
}
