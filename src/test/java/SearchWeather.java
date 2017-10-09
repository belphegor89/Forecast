import Pages.ForecastPage;
import Pages.LoginPage;
import Pages.SendMail;
import Utils.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchWeather extends BaseTest {

    public static Logger logger = Logger.getLogger(SearchWeather.class);

    @Test
    public void execute() throws Exception {
        ForecastPage forecast = ForecastPage.Instance;
        LoginPage loginPage = LoginPage.Instance;
        SendMail sendMail = SendMail.Instance;
        forecast.open();
        forecast.searchCity();
        forecast.takeScreenshotForecast();
        logger.info("Screenshot with forecast taken");
        logger.info("Starting test for sending forecast via email");
        loginPage.open();
        loginPage.login();
        sendMail.sendMailWithFile();
    }
}