import Pages.BasePage;
import Pages.HomePage;
import Utils.DriverManager;
import Utils.PropertiesReader;
import Utils.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by owner on 04-Sep-17.
 */
public class LoginTest {
    Reporter reporter = new Reporter();

    @BeforeTest
    public void beforeTest() {

        BasePage.driver.set(DriverManager.getDriver());
        reporter.instantiate();
        Reporter.addTest(getClass().getName(), "Project version: " + PropertiesReader.getProjectVersion() +
                " Operating System: "
                + System.getProperty("os.name").toUpperCase() + ";"
                + " Browser: " + DriverManager.getCurrentBrowserName().toUpperCase());
    }

    @Test
    public void login() throws Exception {

        HomePage home = HomePage.Instance;
        home.open();
    }

    @AfterTest
    public void endTest(){
        Reporter.flush();
        BasePage.driver().quit();
        DriverManager.closeDriver();
    }
}
