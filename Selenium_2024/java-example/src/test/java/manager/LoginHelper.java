package manager;

import org.openqa.selenium.By;


public class LoginHelper extends HelperBase {


    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void loginAdmin(String user, String password) {
        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.name("login"));
    }

}
