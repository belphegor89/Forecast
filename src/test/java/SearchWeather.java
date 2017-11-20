import Pages.ForecastPage;
import Utils.*;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchWeather extends BaseTest {

    public static Logger logger = Logger.getLogger(SearchWeather.class);

    @Test
    public void execute() throws Exception {

        ForecastPage forecast = ForecastPage.Instance;
        logger.info("Starting test for searching weather forecast");
        forecast.searchCity();
        forecast.takeScreenshotForecast();
        Reporter.log("Screenshot with forecast taken");
    }
}