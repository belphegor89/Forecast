
import pages.violity.ViolityLoginPage;
import utils.BaseTest;
import utils.Reporter;
import com.aventstack.extentreports.ExtentTest;
import org.testng.TestException;
import org.testng.annotations.Test;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class ViolityLogin extends BaseTest {

    @Test
    public void execute() {

        ViolityLoginPage violityLoginPage = ViolityLoginPage.Instance;
        Reporter.log("Starting test for violity login");
        violityLoginPage.navigate();
        violityLoginPage.login();
        Reporter.log("User is logged in");
        violityLoginPage.validateLogin();
    }
}