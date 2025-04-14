import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTests {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("Admin");
        driver.findElement(By.name("login")).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
