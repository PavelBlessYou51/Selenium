import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task8Tests {
    private WebDriver driver;

    @Test
    public void AlphabetOrderTest() {
        login();
        List<WebElement> countries = List.of();
        while (countries.isEmpty()) {
            countries = driver.findElements(By.cssSelector("form td:nth-of-type(5) a"));
        }
        List<String> actualCountries = countries
                .stream()
                .map(WebElement::getText)
                .toList();
        List<String> expectedCountries = actualCountries
                .stream()
                .sorted()
                .toList();
        System.out.println(actualCountries.toString());
        System.out.println(expectedCountries.toString());
        Assertions.assertEquals(expectedCountries, actualCountries); // сравниваем списки стран
        String countryLineLocator = "form tr:nth-of-type(%s) td:nth-of-type(6)";
        String countryLinkLocator = "form tr:nth-of-type(%s) td:nth-of-type(5) a";
        for(int i = 2; i <= countries.size() + 1; i++) {
           WebElement zone = driver.findElement(By.cssSelector(String.format(countryLineLocator, i)));
           if(!zone.getText().equals("0")) {
               WebElement countryName = driver.findElement(By.cssSelector(String.format(countryLinkLocator, i)));
               countryName.click();
               List<String> actualZones = getZones();
               List<String> expectedZones = getZones().stream().sorted().toList();
               Assertions.assertEquals(expectedZones, actualZones); // сравниваем списки зон
               System.out.println(actualZones.toString());
               System.out.println(expectedZones.toString());
               driver.findElement(By.cssSelector("[name=cancel]")).click();
           }
        }
    }

    public List<String> getZones() {
        List<String> zones = driver.findElements(By.cssSelector("[name*='[name]'][type='hidden']"))
                .stream()
                .map(elem -> elem.getAttribute("value"))
                .toList();
        return zones;
    }

    public void login() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
