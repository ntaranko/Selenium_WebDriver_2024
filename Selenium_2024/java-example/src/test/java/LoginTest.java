import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("user");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("login"));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
