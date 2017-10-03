package Browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Chrome implements AbstractBrowser {

    WebDriver driver = null;

    public WebDriver getBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.driver = new ChromeDriver();

        return driver;
    }
}
