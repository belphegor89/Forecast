package pages.Email;

import pages.BasePage;
import utils.PropertiesReader;
import org.openqa.selenium.By;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class LoginPage extends BasePage {

    private static LoginPage instance;
    public static LoginPage Instance =(instance!=null) ? instance: new LoginPage();

    By username = By.xpath(".//input[@name='regular']");
    By password = By.xpath(".//input[@type='password']");
    By loginbtn = By.xpath(".//button[@type='submit']");
    By writeMail = By.xpath(".//*[@id='content']//button");

    public void login() {
        open(PropertiesReader.getConfigProperty("URL2"));
        logger.info("Entering username");
        findElement(username).sendKeys(PropertiesReader.getConfigProperty("username"));
        logger.info("Entering password");
        findElement(password).sendKeys(PropertiesReader.getConfigProperty("password"));
        logger.info("Clicking login");
        findElement(loginbtn).click();
        validateLogin();
        logger.info("User is successfully logged in!");
        waitForPageToLoad();
        isPageLoaded();
    }

    public void validateLogin(){
        isElementPresentAndDisplay(writeMail);
    }
}
