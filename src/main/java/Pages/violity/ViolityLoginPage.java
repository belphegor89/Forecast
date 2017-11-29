package Pages.violity;

import Pages.BasePage;
import Pages.LoginPage;
import Utils.PropertiesReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by yzosin on 20-Nov-17.
 */
public class ViolityLoginPage extends BasePage {

    private static ViolityLoginPage instance;
    public static ViolityLoginPage Instance = (instance != null) ? instance : new ViolityLoginPage();
    public static Logger logger = Logger.getLogger(BasePage.class);

    By loginLink = By.xpath(".//*[@id='user_login']/a");
    By username = By.xpath(".//input[contains(@name,'login')]");
    By password = By.xpath(".//input[contains(@name,'password')]");
    By loginButton = By.xpath(".//input[@type='submit']");
    By userLobbyLink = By.xpath(".//a[text()='Мій кабінет']");

    public void navigate() {
        open(PropertiesReader.getConfigProperty("URL3"));
    }

    public void login() {
        logger.info("Clicking on Login link");
        clickOnElement(loginLink);
        logger.info("Entering username and password");
        setText(username, PropertiesReader.getConfigProperty("usernameViolity"));
        setText(password, PropertiesReader.getConfigProperty("passwordViolity"));
        clickOnElement(loginButton);
    }

    public void validateLogin() {
        logger.info("Validating user lobby link");
        isElementPresentAndDisplay(userLobbyLink);
    }
}
