import Pages.ForecastPage;
import Utils.*;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchWeatherForecast extends BaseTest {

    ExtentTest test = Reporter.addTest("SearchWeather");

    @Test
    public void execute() {

        try {
            ForecastPage forecast = ForecastPage.Instance;
            test.info("Starting test for searching weather forecast");
            forecast.searchCity();
            forecast.takeScreenshotForecast();
            test.pass("Screenshot with forecast taken");
        } catch(Exception e){
            e.printStackTrace();
            Reporter.takeScreenshot(getClass().getName().toString());
            test.fail("Test failed becasue of: " + e.getMessage());
            throw new TestException("Test has failed.");
        }
    }
}