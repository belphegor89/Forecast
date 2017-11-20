package Pages;

import Utils.PropertiesReader;
import Utils.Reporter;
import org.openqa.selenium.By;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class LoginPage extends BasePage {

    private static LoginPage instance;
    public static LoginPage Instance =(instance!=null) ? instance: new LoginPage();

    By username = By.xpath(".//input[@name='Login']");
    By password = By.xpath(".//input[@type='password']");
    By loginbtn = By.xpath(".//button[@type='submit']");
    By writeMail = By.xpath(".//*[@id='content']//button");

    public void login() {
        open(PropertiesReader.getConfigProperty("URL2"));
        Reporter.log("Entering username");
        findElement(username).sendKeys(PropertiesReader.getConfigProperty("username"));
        Reporter.log("Entering password");
        findElement(password).sendKeys(PropertiesReader.getConfigProperty("password"));
        Reporter.log("Clicking login");
        findElement(loginbtn).click();
        validateLogin();
        Reporter.log("User is successfully logged in!");
        waitForPageToLoad();
    }

    public void validateLogin(){
        isElementPresentAndDisplay(writeMail);
    }
}
