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

public class LessonFourTask6Tests {
    public static WebDriver driver;

    @BeforeAll
    public static void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin");
    }


    public void logIn() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=login]")).click();
    }

    public void logOut() {
        driver.findElement(By.cssSelector("a[title=Logout]")).click();
    }

    public boolean hasInnerList(WebElement elem) {
        return !elem.findElements(By.cssSelector("ul.docs")).isEmpty();
    }

    public boolean hasHeader() {
        return !driver.findElements(By.cssSelector("h1")).isEmpty();
    }

    public WebElement getElement(int i) {
        WebElement list = driver.findElement(By.id("box-apps-menu"));
        List<WebElement> listOfElements = list.findElements(By.id("app-"));
        return listOfElements.get(i);
    }

    public WebElement getInnerElement(int i, int l) {
        WebElement element = getElement(i);
        List<WebElement> listOfInnerElements = element.findElements(By.cssSelector("ul.docs li"));
        return listOfInnerElements.get(l);
    }


    @Test
    public void listCheckingTest() {
        logIn();
        WebElement list = driver.findElement(By.id("box-apps-menu"));
        int count = list.findElements(By.id("app-")).size();
        for(int i = 0; i < count; i++) {
            WebElement elem = getElement(i);
            elem.click();
            elem = getElement(i);
            boolean flag = hasInnerList(elem);
            Assertions.assertTrue(hasHeader());
            if(flag) {
                int innerListCount = elem.findElements(By.cssSelector("ul.docs li")).size();
                for(int l = 1; l < innerListCount; l++) {
                    WebElement innerElem = getInnerElement(i, l);
                    innerElem.click();
                    Assertions.assertTrue(hasHeader());
                }
            }
        }
        logOut();

    }

    @AfterAll
    public static void quit() {
        driver.quit();
    }
}
