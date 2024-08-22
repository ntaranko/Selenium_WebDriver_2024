package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AdminHelper extends HelperBase {
    public AdminHelper(ApplicationManager manager) {
        super(manager);
    }

    public void clickMenu(By menuItem) {
        click(menuItem);
    }

    public boolean isElementPresent(By locator) {
        try {
            manager.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}

