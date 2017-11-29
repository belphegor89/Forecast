import Pages.LoginPage;
import Pages.SendMail;
import Utils.*;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class SendForecast extends BaseTest {

    ExtentTest test = Reporter.addTest("SendForecast");

    @Test
    public void execute() {

        try {
            LoginPage loginPage = LoginPage.Instance;
            SendMail sendMail = SendMail.Instance;
            test.info("Starting test for sending forecast via email");
            loginPage.login();
            sendMail.sendMailWithFile();
            test.pass("Test finished, please see the report");
        } catch(Exception e){
            e.printStackTrace();
            Reporter.takeScreenshot(getClass().getName().toString());
            test.fail("Test failed becasue of: " + e.getMessage());
            throw new TestException("Test has failed.");
        }
    }
}