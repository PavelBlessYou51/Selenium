import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstLessonTests {
    private WebDriver driver;


    @Test
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
        driver.quit();
        driver = new FirefoxDriver();
        driver.get("http://localhost/litecart/admin/");
        driver.quit();
        driver = new EdgeDriver();
        driver.get("http://localhost/litecart/admin/");
        driver.quit();
    }
}


