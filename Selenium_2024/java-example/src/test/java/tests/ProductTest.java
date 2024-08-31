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

public class ProductTest {
    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void correctProductPage() {
        driver.get("http://localhost/litecart/en/");

        var product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));
        var productName = product.findElement(By.cssSelector("div.name")).getText();
        int regularPrice = Integer.valueOf(product.findElement(By.cssSelector("s.regular-price")).getText().substring(1));
        int promotionalPrice = Integer.valueOf(product.findElement(By.cssSelector("strong.campaign-price")).getText().substring(1));

        //Get css properties of product's regular price
        var regularPriceColor = product.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        var regularPriceFontSize = product.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        var regularPriceFontSizeNumber = Double.valueOf(regularPriceFontSize.substring(0, regularPriceFontSize.length() - 2));
        var regularPriceTextDecoration = product.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");

        //Get css properties of product's promotional price
        var promotionalPriceColor = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        var promotionalPriceFontSize = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        var promotionalPriceFontSizeNumber = Double.valueOf(promotionalPriceFontSize.substring(0, promotionalPriceFontSize.length() - 2));
        var promotionalPriceFontWeight = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");

        product.click();
        var productNamePage = driver.findElement(By.cssSelector("h1.title")).getText();
        int regularPricePage = Integer.valueOf(driver.findElement(By.cssSelector("s.regular-price")).getText().substring(1));
        int promotionalPricePage = Integer.valueOf(driver.findElement(By.cssSelector("strong.campaign-price")).getText().substring(1));

        //Get css properties of regular price on product page
        var regularPricePageColor = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("Color");
        var regularPricePageFontSize = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        var regularPricePageFontSizeNumber = Double.valueOf(regularPricePageFontSize.substring(0, regularPricePageFontSize.length() - 2));
        var regularPricePageTextDecoration = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");

        //Get css properties of promotional price on product page
        var promotionalPricePageColor = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("Color");
        var promotionalPricePageFontSize = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        var promotionalPricePageFontSizeNumber = Double.valueOf(promotionalPricePageFontSize.substring(0, promotionalPricePageFontSize.length() - 2));
        var promotionalPricePageFontWeight = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");

        //CHECKS
        //1
        if (!productName.equals(productNamePage)) {
            System.out.println("Product names are different");
        }
        //2
        if (regularPrice != regularPricePage) {
            System.out.println("Regular prices are different");
        }
        if (promotionalPrice != promotionalPricePage) {
            System.out.println("Promotional prices are different");
        }

        //3
        var rColor = regularPriceColor.substring(5, 8);
        var gColor = regularPriceColor.substring(10, 13);
        var bColor = regularPriceColor.substring(15, 18);
        if (rColor.equals(gColor) && rColor.equals(bColor)) {
            System.out.println("Regular price color is grey");
        }

        rColor = regularPricePageColor.substring(4, 7);
        gColor = regularPricePageColor.substring(9, 12);
        bColor = regularPricePageColor.substring(14, 17);
        if (rColor.equals(gColor) && rColor.equals(bColor)) {
            System.out.println("Regular price color on product page is grey");
        }

        if (regularPriceTextDecoration.contains("line-through")) {
            System.out.println("Regular price is crossed out");
        }

        if (regularPricePageTextDecoration.contains("line-through")) {
            System.out.println("Regular price on product page is crossed out");
        }

        //4
        gColor = promotionalPriceColor.substring(10, 11);
        bColor = promotionalPriceColor.substring(13, 14);
        if (gColor.equals("0") && bColor.equals("0")) {
            System.out.println("Promotional price color is red");
        }

        gColor = promotionalPricePageColor.substring(9, 10);
        bColor = promotionalPricePageColor.substring(12, 13);
        if (gColor.equals("0") && bColor.equals("0")) {
            System.out.println("Promotional price color on product page is red");
        }

        if (promotionalPriceFontWeight.equals("700")) {
            System.out.println("Promotional price color is bold");
        }

        if (promotionalPricePageFontWeight.equals("700")) {
            System.out.println("Promotional price color on product page is bold");
        }

        //5
        if (promotionalPriceFontSizeNumber > regularPriceFontSizeNumber) {
            System.out.println("Promotional price size is bigger than regular price size");
        }

        if (promotionalPricePageFontSizeNumber > regularPricePageFontSizeNumber) {
            System.out.println("Promotional price size is bigger than regular price size on product page");
        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
