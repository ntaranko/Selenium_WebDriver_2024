package tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CreateProductTest extends TestBase {

    @Test
    public void createProduct() {
        app.adminHelper().selectElement(By.xpath("//*[contains(text(),'Catalog')]"));
        app.adminHelper().selectElement(By.xpath("//*[contains(text(),' Add New Product')]"));

        app.adminHelper().selectElement(By.xpath("//*[contains(text(),' Enabled')]"));

        String productName = CommonFunctions.randomString(10);
        app.adminHelper().fillField(By.cssSelector("input[name=\"name[en]\"]"), productName);
        app.adminHelper().fillField(By.cssSelector("input[name=\"code\"]"), CommonFunctions.randomString(4));
        app.adminHelper().selectElement(By.cssSelector("input[name=\"categories[]\"][value=\"1\"]")); //to write method to verify if option is not selected

        app.adminHelper().selectElement(By.cssSelector("input[name=\"product_groups[]\"][value=\"1-3\"]"));
        app.adminHelper().fillField(By.cssSelector(" [name=\"quantity\"]"), "12.3");

        app.adminHelper().attach(By.cssSelector("[name=\"new_images[]\"]"), randomFile("src/test/resources/images/"));

        app.driver.findElement(By.cssSelector("input[name=\"date_valid_from\"]")).sendKeys("02/20/2024");
        app.driver.findElement(By.cssSelector("input[name=\"date_valid_to\"]")).sendKeys("02/20/2025");

        //Tab Information
        app.adminHelper().selectElement(By.cssSelector("[href=\"#tab-information\"]"));

        Select selectManufacturer = new Select(app.driver.findElement(By.cssSelector("select[name=\"manufacturer_id\"]")));
        selectManufacturer.selectByValue("1");

        app.adminHelper().fillField(By.cssSelector("[name=\"keywords\"]"), CommonFunctions.randomString(4));
        app.adminHelper().fillField(By.cssSelector("[name=\"short_description[en]\"]"),
                CommonFunctions.randomString(5) + " " + CommonFunctions.randomString(8) + " " + CommonFunctions.randomString(15));


        app.adminHelper().fillField(By.cssSelector("div.trumbowyg-editor"), CommonFunctions.randomString(40));
        app.adminHelper().fillField(By.cssSelector("[name=\"head_title[en]\"]"), CommonFunctions.randomString(10));
        app.adminHelper().fillField(By.cssSelector("[name=\"meta_description[en]\"]"), CommonFunctions.randomString(10));

        //Tab Prices
        app.adminHelper().selectElement(By.cssSelector("[href=\"#tab-prices\"]"));
        app.adminHelper().fillField(By.cssSelector("[name=\"purchase_price\"]"), "20");

        Select selectCurrency = new Select(app.driver.findElement(By.cssSelector("select[name=\"purchase_price_currency_code\"]")));
        selectCurrency.selectByValue("USD");

        app.adminHelper().fillField(By.cssSelector("[name=\"prices[USD]\"]"), "22");
        app.adminHelper().fillField(By.cssSelector("[name=\"gross_prices[USD]\"]"), "2.2");
        app.adminHelper().fillField(By.cssSelector("[name=\"prices[EUR]\"]"), "18");
        app.adminHelper().fillField(By.cssSelector("[name=\"gross_prices[EUR]\"]"), "1.8");

        app.adminHelper().selectElement(By.cssSelector("[name=\"save\"]"));

        if (app.adminHelper().isElementPresent(By.xpath(String.format("//a[contains(., '%s')]", productName)))) {
            System.out.println("Product " + productName + " is created");
        } else {
            System.out.println("Product " + productName + " is not created");
        }
    }

}


