import Utils.Reporter;
import Pages.Email.LoginPage;
import Pages.Email.SendMail;
import Pages.ForecastPage;
import Utils.*;
import com.aventstack.extentreports.ExtentTest;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SearchAndSendWeatherForecast extends BaseTest {

    ExtentTest test = Reporter.addTest("SendWeatherForecast");

    @Test
    public void execute() {

        try {
            ForecastPage forecast = ForecastPage.Instance;
            LoginPage loginPage = LoginPage.Instance;
            SendMail sendMail = SendMail.Instance;
            test.info("Starting test for searching weather forecast");
            forecast.searchCity();
            test.info("A city: " + PropertiesReader.getConfigProperty("searchCity") + " entered into search field");
            forecast.takeScreenshotForecast();
            test.info("Screenshot with forecast taken");
            test.info("Starting test for sending forecast via email");
            test.info("Trying to log in to " + PropertiesReader.getConfigProperty("URL2")).toString();
            loginPage.login();
            test.info("User is logged in. Sending mail.");
            sendMail.sendMailWithFile();
            test.info("User is logged in. Sending mail.");
            test.pass("Test finished, please see the report");
        } catch(Exception e){
            e.printStackTrace();
            Reporter.takeScreenshot(getClass().getName().toString());
            test.fail("Test failed becasue of: " + e.getMessage());
            throw new TestException("Test has failed.");
        }
    }
}