package Pages;

import Utils.Reporter;
import org.openqa.selenium.By;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class HomePage extends BasePage {

    private static HomePage instance;
    public static HomePage Instance =(instance!=null) ? instance: new HomePage();

    public HomePage(){

    }
    By loginButton = By.xpath(".//button[@type='submit']");

    public void clickLogin() {
        Reporter.log("Clicking login button");
        findElement(loginButton).click();
    }
}
