package Pages;

import Utils.PropertiesReader;
import Utils.Reporter;
import Utils.Tools;
import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzosin on 23-Aug-17.
 */
public class BasePage {

    public static Logger logger = Logger.getLogger(BasePage.class);
    public final static String URL = (PropertiesReader.getConfigProperty("URL"));
    public String pageURL = "";
    public String pageTitle = "";
    static String TARGET_FOLDER = "target";
    public static final int DEFAULT_SHORT_TIMEOUT = 10;
    public static final int DEFAULT_LONG_TIMEOUT = 50;
    public static final int STATIC_TIMEOUT =  1;

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public BasePage() {
        //reporter.instantiate();
    }

    public static WebDriver driver(){
        return driver.get();
    }

    public boolean isPageLoaded() {
        boolean result = false;
        logger.info("Page title is: " + driver().getTitle());
        logger.info("Page URL is: " + driver().getCurrentUrl());
        if (driver().getTitle().contains(pageTitle))
            result = true;
        else {
            logger.info("Expected title: " + pageTitle);
            result = false;
        }

        if (driver().getCurrentUrl().contains(pageURL))
            result = true;
        else {
            logger.info("Expected URL: " + pageURL);
            result = false;
        }
        return result;
    }

    public void reloadPage() {
        driver().navigate().refresh();
    }

    public void open() {
        Reporter.log("Opening the page: " + "\"" + URL);
        driver().get(URL);
    }

    public String getTitle() {
        Reporter.log("The page title is: " + "\"" + pageTitle + "\"");
        return pageTitle;
    }

    public String getURL() {
        Reporter.log("The requested URL is: " + URL + pageURL);
        return URL + pageURL;
    }

    public void setText(By element, String value){
        if (value != null) {
            findElement(element).clear();
            findElement(element).sendKeys(value);
        }
    }

    public boolean isTextPresent(String text) {
        return driver().getPageSource().contains(text);
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean isElementPresentAndDisplay(By by) {
        try {
            return findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getWebElement(By by) {
        return findElement(by);
    }

    public static void selectFromDropdown(By element, String value){
        Select dropdown = new Select(findElement(element));
        dropdown.selectByVisibleText(value);
    }

    public static void clickOnElement(By element) {
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), STATIC_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            driver().findElement(element).click();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failure clicking on element" );
        }
        waitForPageToLoad();
    }

    public static WebElement findElement(By element) {
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), DEFAULT_SHORT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver().findElement(element);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failure finding element");
        }
    }

    public static List<WebElement> findElements(By element) {
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver(), DEFAULT_SHORT_TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver().findElements(element);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failure by finding elements");
        }
    }

    public static void scrollToElement(WebElement element) {
        waitForPageToLoad();
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);",element);

    }

    public static void waitForPageToLoad(){
        Wait<WebDriver> wait = new WebDriverWait(driver(), STATIC_TIMEOUT).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    static void waitForElement(By by){
        WebDriverWait wait = new WebDriverWait(driver(), DEFAULT_SHORT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void sleepFor(int timeout){
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(STATIC_TIMEOUT, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
        }
    }

    static void waitForAlert(WebDriver driver, int timeout) {
        int i = 0;
        while (i++ < timeout) {
            try {
                Alert alert = driver.switchTo().alert();
                break;
            } catch (NoAlertPresentException e)
            {
                sleepFor(1);
                continue;
            }
        }
    }

    public void switchToFrame(By by) {
        logger.info("Switching to frame: " + by.toString());
        driver().switchTo().frame(findElement(by));
    }

    public void switchToDefaultContent(){
        logger.info("Switching to default content");
        driver().switchTo().defaultContent();
    }

    public static String takeScreenshot(WebDriver driver, String name){
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = name + "Screenshot.png";
        try {
            FileUtils.copyFile(file, new File(TARGET_FOLDER + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
