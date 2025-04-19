import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirstLessonTests {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        System.out.println(((HasCapabilities)driver).getCapabilities());
    }

    @Test
    public void start() {
        //driver.get("http://localhost/litecart/admin/");
        System.out.println("!!!");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
    
}
