import org.testng.Assert;
import utils.Reporter;
import pages.Email.LoginPage;
import pages.Email.SendMail;
import pages.ForecastPage;
import utils.*;
import com.aventstack.extentreports.ExtentTest;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SendWeatherForecast extends BaseTest {

    @Test(invocationCount = 1)
    public void execute() {


        ForecastPage forecast = ForecastPage.Instance;
        LoginPage loginPage = LoginPage.Instance;
        SendMail sendMail = SendMail.Instance;
        Reporter.log("Starting test for searching weather forecast");
        forecast.searchCity();
        Reporter.log("A city: " + PropertiesReader.getConfigProperty("searchCity") + " entered into search field");
        forecast.takeScreenshotForecast();
        Reporter.log("Screenshot with forecast taken");
        Reporter.log("Starting next part for sending forecast via email");
        Reporter.log("Trying to log in to " + PropertiesReader.getConfigProperty("URL2"));
        loginPage.login();
        Reporter.log("User is logged in. Sending mail.");
        sendMail.sendMailWithFile();
        Assert.assertTrue(sendMail.validateMessageSent());
        //test.pass("Test finished, please see the report");
        //Reporter.takeScreenshot(getClass().getName().toString());
        //test.fail("Test failed becasue of: " + e.getMessage().toString());


    }
}