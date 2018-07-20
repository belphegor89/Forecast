package apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Reporter;

public class BaseTestAPI {

    public static Logger logger = Logger.getLogger(BaseTestAPI.class);
    Reporter reporter;
    protected RequestSpecification requestSpecification;


    @BeforeMethod
    public void before() {

        reporter = Reporter.Instance;
        Reporter.startTest(getClass().getName().toString());
        logger.info("Starting api test");
        RequestSpecBuilder builder = new RequestSpecBuilder();
        requestSpecification = builder.setBaseUri("https://maps.googleapis.com")
                .build();

    }

    @AfterMethod
    public void flushReporter(ITestResult testResult) {
        Reporter.stopReportingAPI(testResult);

    }
}
