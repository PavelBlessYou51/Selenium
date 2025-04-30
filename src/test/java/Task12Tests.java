import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Task12Tests extends BaseTests{

    @Test
    public void addItemTest(){
        init("http://localhost/litecart/admin/", "chrome");
        login();
        click(By.xpath("//span[contains(text(), 'Catalog')]"));
        click(By.cssSelector(".button:nth-child(2)"));
        String name = randomAlphanumericString();
        fillGeneral(name);
        click(By.xpath("//a[contains(text(), 'Information')]"));
        fillInformation();
        click(By.xpath("//a[contains(text(), 'Prices')]"));
        fillIPrices();
        click(By.name("save"));
        String notice = driver.findElement(By.xpath("//div[contains(text(), 'Changes saved successfully.')]")).getText();
        Assertions.assertEquals("Changes saved successfully.", notice);
        int addedGoods = driver.findElements(By.xpath(String.format("//a[contains(text(), '%s')]", name))).size();
        Assertions.assertTrue(addedGoods > 0);
        quit();
    }

    public void fillGeneral(String name){
        click(By.cssSelector("input[name=status][value='1']"));
        type(name, By.name("name[en]"));
        type("№1", By.name("code"));
        click(By.cssSelector("[name='product_groups[]'][value='1-1']"));
        type("10", By.name("quantity"));
        uploadFile(By.name("new_images[]"), "src/test/resources/plant.jpg");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByName('date_valid_from').value = '2025-04-27'");
        js.executeScript("document.getElementsByName('date_valid_to').value = '2026-04-27'");
    }

    public void fillInformation(){
        Select select = new Select(driver.findElement(By.name("manufacturer_id")));
        select.selectByValue("1");
        type("Goose", By.name("keywords"));
        type("Goose", By.name("short_description[en]"));
        type("Goose like duck, but bigger", By.className("trumbowyg-editor"));
        type("GOOOOOOSE", By.name("head_title[en]"));
        type("---GOOSE---", By.name("meta_description[en]"));
    }

    public void fillIPrices(){
        type("15", By.name("purchase_price"));
        Select select = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        select.selectByIndex(1);
        type("100", By.name("prices[USD]"));
        type("10", By.name("gross_prices[USD]"));
        type("120", By.name("prices[EUR]"));
        type("12", By.name("gross_prices[EUR]"));
    }

    public static String randomAlphanumericString() {
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
        Random random = new Random();
        int length = random.nextInt(12);
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphanumericCharacters.length());
            char randomChar = alphanumericCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }
}
