
import Pages.violity.ViolityLoginPage;
import Utils.BaseTest;
import Utils.Reporter;
import com.aventstack.extentreports.ExtentTest;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class ViolityLogin extends BaseTest {

    ExtentTest test = Reporter.addTest("ViolityLogin");

    @Test(threadPoolSize = 2)
    public void execute() {
        try {
            ViolityLoginPage violityLoginPage = ViolityLoginPage.Instance;
            test.info("Starting test for violity login");
            violityLoginPage.navigate();
            violityLoginPage.login();
            test.info("User is logged in");
            violityLoginPage.validateLogin();
            test.pass("Test finished, please see the report");
        } catch(Exception e){
            e.printStackTrace();
            Reporter.takeScreenshot(getClass().getName().toString());
            test.fail("Test failed becasue of: " + e.getMessage());
            throw new TestException("Test has failed.");
        }
    }
}