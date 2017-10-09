package Pages;

import Utils.Reporter;
import org.openqa.selenium.By;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class ForecastPage extends BasePage {

    private static ForecastPage instance;
    public static ForecastPage Instance =(instance!=null) ? instance: new ForecastPage();

    By searchField = By.id("search_city");
    By searchButton = By.xpath(".//*[@id='form-search']//input[2]");

    public void searchCity() {
        Reporter.log("Entering Ternopil into search field");
        findElement(searchField).sendKeys("Тернопіль");
        Reporter.log("Clicking search icon");
        findElement(searchButton).click();
    }

    public void takeScreenshotForecast() {
        Reporter.log("Taking screenshot for page!!!");
        logger.info("");
        takeScreenshot(driver(),"Sinoptik");
    }
}
