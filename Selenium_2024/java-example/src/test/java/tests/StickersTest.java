package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StickersTest {
    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeEach
    public void start() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void allStickersTest() {
        driver.get("http://localhost/litecart/en/");
        var allProducts = driver.findElements(By.cssSelector("li.product"));
        for (var oneProduct : allProducts) {
            var allStickers = oneProduct.findElements(By.cssSelector("div.sticker"));
            if (allStickers.size() == 1) {
                System.out.println("The product has only 1 sticker");
            } else if (allStickers.size() > 1) {
                System.out.println("The product has more than 1 sticker");
            } else {
                System.out.println("The product doesn't have stickers");
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}

