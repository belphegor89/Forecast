import Pages.ForecastPage;
import Utils.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchWeather extends BaseTest {

    public static Logger logger = Logger.getLogger(SearchWeather.class);

    @Test
    public void getForecast() throws Exception {
        ForecastPage forecast = ForecastPage.Instance;
        forecast.open();
        forecast.searchCity();
        forecast.takeScreenshotForecast();
        logger.info("Screenshot with forecast taken");
    }
}