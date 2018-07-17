package utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import pages.BasePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class BaseTest {

    public static Logger logger = Logger.getLogger(BaseTest.class);

    //ExtentTest test = Reporter.addTest(getClass().getName().toString());
    Reporter reporter;


    @BeforeMethod
    public void before() {

        reporter = Reporter.Instance;

        Reporter.startTest(getClass().getName().toString());
        try {
            logger.info("Creating driver for " + getClass().getName().toString() + " test");
            BasePage.driver.set(DriverManager.getDriver());
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.fail("Failed during driver creation", getClass().getName().toString());
            Reporter.saveAndQuit();
            Assert.fail();
        }
    }

    @AfterMethod
    public void endTest(ITestResult testResult) {
        Reporter.stopReporting(testResult);
        BasePage.driver().quit();
        DriverManager.closeDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void flushReporter() {
        Reporter.saveAndQuit();
    }
}
