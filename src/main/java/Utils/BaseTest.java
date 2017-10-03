package Utils;

import Pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class BaseTest {
    Reporter reporter = new Reporter();
    public static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeMethod
    public void before(){
        reporter.instantiate();
        Reporter.addTest(getClass().getName(), "Project version: " + PropertiesReader.getProjectVersion() +
                " Operating System: "
                + System.getProperty("os.name").toUpperCase() + ";"
                + " Browser: " + DriverManager.getCurrentBrowserName().toUpperCase()
                + " " + ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities().getVersion());

        try{
            logger.info("Creating driver");
            BasePage.driver.set(DriverManager.getDriver());
        } catch(Exception e){
            Reporter.fail("Failed while driver creation");
            Reporter.flush();
            Assert.fail();
        }
    }

    @AfterMethod
    public void endTest(ITestResult testResult){
        reporter.stopReport(testResult);
        Reporter.flush();
        BasePage.driver().quit();
        DriverManager.closeDriver();
    }
}
