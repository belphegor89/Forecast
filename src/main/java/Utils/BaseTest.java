package Utils;

import Pages.BasePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by yzosin on 20-Sep-17.
 */
public class BaseTest {

    public static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeMethod
    public void before(){

        try{
            BasePage.driver.set(DriverManager.getDriver());
        } catch(Exception e){
            e.printStackTrace();
            Reporter.fail("Failed during driver creation", "");
            Reporter.saveAndQuit();
            Assert.fail();
        }
    }

    @AfterMethod
    public void endTest(){

        Reporter.saveAndQuit();
        BasePage.driver().quit();
        DriverManager.closeDriver();
    }
}
