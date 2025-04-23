import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Task9Tests extends BaseTests{

    @Test
    public void orderGeoZonesTest() {
        init("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        login();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(presenceOfElementLocated(By.className("row")));
        int countCountries = driver.findElements(By.className("row")).size();
        Assertions.assertTrue(countCountries > 0);
        System.out.println(countCountries);
        String countryLocator = ".row:nth-child(%s) td:nth-child(3) a";
        for(int i = 2; i < 2 + countCountries; i++) {
            driver.findElement(By.cssSelector(String.format(countryLocator, i))).click();
            List<String> actual = getZones();
            List<String> expected = actual.stream().sorted().toList();
            Assertions.assertEquals(expected, actual); // сравниваем списки зон
            System.out.println(expected.toString());
            System.out.println(actual.toString());
        }
    }

    public List<String> getZones() {
        List<WebElement> zoneSelectors = driver.findElements(By.cssSelector("[name$='[zone_code]']"));
        List<String> result = new ArrayList<>();
        for(WebElement elem : zoneSelectors) {
            String zone = elem.findElement(By.cssSelector("[name$='[zone_code]'] [selected]")).getText();
            result.add(zone);
        }
        driver.findElement(By.cssSelector("[name=cancel]")).click();
        return result;
    }

}
