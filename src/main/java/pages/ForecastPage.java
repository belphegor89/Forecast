package pages;

import utils.PropertiesReader;
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
        open(PropertiesReader.getConfigProperty("URL"));
        waitForPageToLoad();
        logger.info("Entering " + PropertiesReader.getConfigProperty("searchCity") + " into search field");
        findElement(searchField).sendKeys(PropertiesReader.getConfigProperty("searchCity"));
        logger.info("Clicking search icon");
        findElement(searchButton).click();
    }

    public void takeScreenshotForecast() {
        logger.info("Taking screenshot for page");
        takeScreenshot(driver(),"Sinoptik");
    }
}
