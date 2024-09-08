package tests.adminTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import tests.TestBase;

public class AdminMenuTest extends TestBase {

    @Test
    public void navigateThroughMenu() {
        var menu = app.driver.findElements(By.id("app-"));
        for (int i = 1; i <= menu.size(); i++) {
            var menuItem = By.cssSelector(String.format("#app-:nth-child(%s)", i));
            app.adminHelper().selectElement(menuItem);
            app.adminHelper().isElementPresent(By.cssSelector("h1"));

            var secondaryMenu = app.driver.findElements(By.cssSelector("#app- li"));
            if (secondaryMenu.size() != 0) {
                for (int j = 1; j <= secondaryMenu.size(); j++) {
                    var secondaryMenuItem = By.cssSelector(String.format("#app-:nth-child(%s) li:nth-child(%s)", i, j));
                    app.adminHelper().selectElement(secondaryMenuItem);
                    app.adminHelper().isElementPresent(By.cssSelector("h1"));
                }
            }
        }
    }

}
