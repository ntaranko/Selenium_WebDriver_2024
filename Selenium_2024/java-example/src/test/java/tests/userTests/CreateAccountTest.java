package tests.userTests;

import common.CommonFunctions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAccountTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        /*ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));*/
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void correctProductPage() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("form [href=\"http://localhost/litecart/en/create_account\"]")).click();
        driver.findElement(By.cssSelector("[name=\"firstname\"]")).sendKeys(CommonFunctions.randomString(5));
        driver.findElement(By.cssSelector("[name=\"lastname\"]")).sendKeys(CommonFunctions.randomString(5));
        driver.findElement(By.cssSelector("[name=\"address1\"]")).sendKeys(CommonFunctions.randomString(10));
        driver.findElement(By.cssSelector("[name=\"postcode\"]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=\"city\"]")).sendKeys(CommonFunctions.randomString(7));

        driver.findElement(By.cssSelector("span.select2-selection")).click();
        driver.findElement(By.cssSelector("li.select2-results__option[id$=\"US\"")).click();

        Select selectZone = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        selectZone.selectByValue("FL");

        var email = CommonFunctions.randomEmail(7);
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=\"phone\"]")).sendKeys("+375292217201");

        var password = CommonFunctions.randomString(10);
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=\"confirmed_password\"]")).sendKeys(password);

        driver.findElement(By.cssSelector("button[type=submit]")).click();

        driver.findElement(By.cssSelector("div.content [href=\"http://localhost/litecart/en/logout\"]")).click();

        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=login]")).click();

        driver.findElement(By.cssSelector("div.content [href=\"http://localhost/litecart/en/logout\"]")).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
