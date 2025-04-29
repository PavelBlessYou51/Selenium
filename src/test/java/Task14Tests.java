import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task14Tests extends BaseTests{

    @Test
    public void windowsTest() {
        init("http://localhost/litecart/admin/?app=countries&doc=countries", "chrome");
        login();
        click(By.className("button"));
        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String titleCurrentWindow = driver.getTitle();
        String currentHandle = driver.getWindowHandle();
        for (WebElement link : links) {
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Object [] windows = driver.getWindowHandles().toArray();
            driver.switchTo().window((String) windows[1]);
            Assertions.assertNotEquals(titleCurrentWindow, driver.getTitle());
            driver.close();
            driver.switchTo().window(currentHandle);
            Assertions.assertEquals(titleCurrentWindow, driver.getTitle());
        }
        driver.quit();
    }
}
