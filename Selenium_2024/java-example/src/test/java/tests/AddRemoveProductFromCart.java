package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AddRemoveProductFromCart {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Test
    public void addRemoveProductFromCart() {
        driver.get("http://localhost/litecart/en/");

        for (int i = 1; i <= 3; i++) {
            var mostPopularProducts = driver.findElements(By.cssSelector("div#box-most-popular li"));
            mostPopularProducts.get(0).click();
            try {
                driver.findElement(By.cssSelector("select[name]"));
                Select selectSize = new Select(driver.findElement(By.cssSelector("select[name]")));
                selectSize.selectByValue("Small");
            } catch (NoSuchElementException ignored) {
            }
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            wait.until(textToBe(By.cssSelector("span.quantity"),
                    Integer.toString(i)));
            driver.get("http://localhost/litecart/en/");
        }

        driver.findElement(By.cssSelector("a[href$=checkout].link")).click();

        driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
        wait.until(visibilityOf(driver.findElement(By.cssSelector("table.dataTable"))));
        driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
        wait.until(visibilityOf(driver.findElement(By.cssSelector("table.dataTable"))));
        driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
