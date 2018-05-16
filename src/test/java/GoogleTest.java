import org.testng.annotations.Test;
import pages.Google;
import utils.BaseTest;
import utils.Reporter;


public class GoogleTest extends BaseTest {

    //ExtentTest test = Reporter.addTest(getClass().getName().toString());

    @Test
    public void execute() {

        Google google = Google.Instance;
        Reporter.log("Starting test for Google");
        google.start();
        Reporter.log("Navigated to google.com");
    }
}
