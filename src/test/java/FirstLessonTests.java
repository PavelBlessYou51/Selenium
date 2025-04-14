import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstLessonTests {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void start() {
        driver.get("https://www.google.ru/");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
    
}
