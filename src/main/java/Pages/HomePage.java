package Pages;

import Utils.DataFilesReader;
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
    By searchField = By.id("search_city");
    By searchButton = By.xpath(".//*[@id='form-search']//input[2]");

    public void searchCity() {
        Reporter.log("Entering Ternopil into search field");
        findElement(searchField).sendKeys("Тернопіль");
        Reporter.log("Clicking search icon");
        findElement(searchButton).click();
    }

    public void takeScreenshotForecast() {
        Reporter.log("Entering Ternopil into search field");
        takeScreenshot(driver(),"Sinoptik");
    }
}
