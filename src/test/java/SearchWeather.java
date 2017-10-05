import Pages.ForecastPage;
import Utils.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchWeather extends BaseTest {

    @Test
    public void getForecast() throws Exception {
        ForecastPage forecast = ForecastPage.Instance;
        forecast.open();
        forecast.searchCity();
        forecast.takeScreenshotForecast();
    }
}