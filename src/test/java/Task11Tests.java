import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class Task11Tests extends BaseTests{
    private static final Random random = new Random();

    @Test
    public void registrationTest() {
        init("http://localhost/litecart/en/", "chrome");
        click(By.cssSelector("form a"));
        type("Pavel", By.cssSelector("[name=firstname]"));
        type("Pro", By.cssSelector("[name=lastname]"));
        type("Pushkina str.", By.cssSelector("[name=address1]"));
        type("12345", By.cssSelector("[name=postcode]"));
        type("New York", By.cssSelector("[name=city]"));
        String email = String.format("prokoshev%s@mail.ru", random.nextInt());
        type(email, By.cssSelector("[name=email]"));
        type("+79600238889", By.cssSelector("[name=phone]"));
        click(By.className("select2-selection__arrow"));
        type("United States", By.className("select2-search__field"));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).perform();
        type("123456", By.cssSelector("[name=password]"));
        type("123456", By.cssSelector("[name=confirmed_password]"));
        click(By.cssSelector("[name=create_account]"));
        Assertions.assertEquals("Your customer account has been created.", driver.findElement(By.cssSelector(".notice.success")).getText());
        click(By.xpath("//a[contains(text(), 'Logout')]"));
        Assertions.assertEquals("You are now logged out.", driver.findElement(By.cssSelector(".notice.success")).getText());
        type(email, By.cssSelector("[name=email]"));
        type("123456", By.cssSelector("[name=password]"));
        click(By.cssSelector("[name=login]"));
        Assertions.assertEquals("You are now logged in as Pavel Pro.", driver.findElement(By.cssSelector(".notice.success")).getText());
        click(By.xpath("//a[contains(text(), 'Logout')]"));
        Assertions.assertEquals("You are now logged out.", driver.findElement(By.cssSelector(".notice.success")).getText());
    }




}
