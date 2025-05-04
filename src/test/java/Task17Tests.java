import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.module.LogInspector;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Task17Tests extends BaseTests{
    @Test
    public void consoleLogTest() {
        init("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1", "chrome");
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Subcategory')]"))).click();
        int rowCount = driver.findElements(By.className("row")).size();
        for(int i = 0; i < rowCount; i++){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
            List<WebElement> row = driver.findElements(By.className("row"));
            row.get(i).findElement(By.cssSelector("td:nth-child(3) a")).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) { // проверяем логи
                System.out.println(l);
            }
            if(!driver.getTitle().equals("Catalog | My Store")){
                driver.navigate().back();
            }
        }
    }
}
