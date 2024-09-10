package tests.adminTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import tests.TestBase;

public class TakeLogs extends TestBase {

    @Test
    public void takeLogs() {
        app.driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        System.out.println(app.driver.manage().logs().getAvailableLogTypes());

        var products = app.driver.findElements(By.cssSelector("tr.row"));
        for (int i = 3; i < products.size(); i++) {
            products.get(i).findElement(By.cssSelector("td a[href]")).click();
            app.driver.manage().logs().get("browser").forEach(l -> System.out.println("log ============ " + l));
            app.driver.findElement(By.cssSelector("[name=cancel]"));
            products = app.driver.findElements(By.cssSelector("tr.row"));
        }
    }
}
