package Browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by owner on 07-Jul-17.
 */
public class Firefox implements AbstractBrowser {

    WebDriver driver = null;

    public WebDriver getBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        this.driver = new FirefoxDriver();

        return driver;
    }
}
