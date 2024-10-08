package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.nio.file.Paths;
import java.util.List;

public class AdminHelper extends HelperBase {
    public AdminHelper(ApplicationManager manager) {
        super(manager);
    }

    public void selectElement(By locator) {
        click(locator);
    }

    public void fillField(By locator, String text) {
        type(locator, text);
    }

    public boolean isElementPresent(By locator) {
        try {
            manager.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isListSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }
}

