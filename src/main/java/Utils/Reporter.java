package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Created by owner on 04-Sep-17.
 */
public class Reporter {

    private static Logger logger = Logger.getLogger(Reporter.class);
    private static String root = System.getProperty("user.dir");
    private static String filePath = "extentreport.html";
    private static ExtentReports extent;
    private static WebDriver driver;
    private static ExtentTest test;
    private static ExtentTest childTest;
    private static ExtentHtmlReporter htmlReporter;
    private static String testCaseName;
    private static Path screenshotFolder;
    private static Path reportPath;
    private static Path logFolder;
    private static boolean buildStatus = true;
    private static ArrayList failuresList = new ArrayList<String>();

    public void instantiate(){
        try {
            // generate report folder name
            Path rootPath = getNewReportPath();
            // create directory if not exists
            if(Files.notExists(rootPath)){
                reportPath = Files.createDirectories(rootPath);
            } else{
                reportPath = rootPath;
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        if (null == extent) {
            extent = new ExtentReports();

            htmlReporter = new ExtentHtmlReporter(Paths.get(reportPath.toString(), filePath).toAbsolutePath().toFile());
            htmlReporter.config().setChartVisibilityOnOpen(false);
            htmlReporter.config().setDocumentTitle("Yura");
            htmlReporter.config().setReportName("Testing Purpose");
            htmlReporter.config().setTheme(Theme.DARK);

            extent.attachReporter(htmlReporter);
        }
    }

    private static Path getNewReportPath() {
        LocalDateTime dateTime = LocalDateTime.now();
        String reportName = "report" + "_" + dateTime.toLocalDate() + "_" + dateTime.toLocalTime().getHour() + "_"
                + dateTime.toLocalTime().getMinute();

        return Paths.get(root, "report", reportName);
    }

    /**
     * <p>
     * Add test to the report with the give test name
     * </p>
     *
     * @param testName    name of the test executing
     * @param description populate information about the test executing
     */
    public static void addTest(String testName, String description) {
        driver = DriverManager.getDriver();

        testCaseName = testName;
        test = extent.createTest(testName, description);
    }

    public static void log(String log) {
        test.info(log);
    }

    public static void pass(String log) {
        test.pass(log);
    }

    public static void fail(String log) {
        try {
            String screenshotPath = takeScreenshot().substring(takeScreenshot().indexOf("screenshots"));
            childTest.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            buildStatus = false;
            failuresList.add(log);
        } catch (Exception e) {
            test.fail(log);
        }
    }

    public static void skip(String log) {
		childTest.skip(log);
        //childTest = test.createNode(log).skip(log);
    }


    public static void flush() {
        extent.flush();
    }

    public void stopReport(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE)
            fail("Test failed because of: " + result.getThrowable());
        else if (result.getStatus()== ITestResult.SKIP)
            skip("Test skipped" );
        else
            pass("Test passed");
    }

    public static String takeScreenshot() {
        try {
            screenshotFolder = Paths.get(reportPath.toString(), "screenshots");

            if (Files.notExists(screenshotFolder))
                Files.createDirectory(screenshotFolder);

            String fileName = testCaseName.replace(" ", "_");
            Path screenshotPath = Paths.get(screenshotFolder.toString(), fileName + ".png");

            Screenshot screenshot = null;
            screenshot = new AShot().takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath.toString()));

            return screenshotPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * Create log for every test in test suite
     * </p>
     */
    public static void logForEveryTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy_HH.mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        logFolder = Paths.get(reportPath.toString(), "Logs");
        try {
            logFolder = Paths.get(reportPath.toString(), "Logs");
            if (Files.notExists(logFolder))
                Files.createDirectory(logFolder);
        } catch (IOException e) {
            logger.info("Unable to create logs folder");
        }

        String fileName = testCaseName.replace(" ", "_") + "_" + formattedDateTime + ".log";
        FileAppender fileApp = new FileAppender();
        fileApp.setFile(logFolder + "/" + fileName);
        fileApp.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %C{1}:%L - %m%n"));
        fileApp.setAppend(true);
        fileApp.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fileApp);
    }
}
