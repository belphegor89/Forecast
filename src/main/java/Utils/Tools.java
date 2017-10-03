package Utils;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Tools {
    static void waitForElementVisible(WebDriver driver, By by, int timeout) {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    static void waitForAlert(WebDriver driver, int timeout) throws InterruptedException {

        int i = 0;
        while (i++ < timeout) {
            try {
                Alert alert = driver.switchTo().alert();
                break;
            } catch (NoAlertPresentException e) {
                Sleeper.SYSTEM_SLEEPER.sleep(new Duration(1, TimeUnit.SECONDS));
                continue;
            }
        }
    }

    /**
     * Waits for a page to load completely
     *
     * @param timeoutSeconds: the integer value that specifies the timeout
     */
    public void waitForPageLoad(int timeoutSeconds, WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutSeconds, 500).ignoring(WebDriverException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public void autoSelectFromDropdown(WebDriver driver, By by) {
        WebElement selectElement = driver.findElement(by);
        Select select = new Select(selectElement);

        int value;
        Random random = new Random();
        List<WebElement> allOptions = select.getOptions();

        if (allOptions.get(0).getText().toLowerCase().contains("none")) {
            value = 1 + random.nextInt(allOptions.size() - 1);
        } else {
            value = random.nextInt(allOptions.size() - 1);
        }

        select.selectByIndex(value);
        allOptions.get(value).getText();
    }

    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }
}

