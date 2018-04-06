package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.log4testng.Logger;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by owner on 04-Sep-17.
 */
public class Reporter {

    private static Logger logger = Logger.getLogger(Reporter.class);
    private static String root = System.getProperty("user.dir");
    private static String filePath = "extentreport.html";
    private static String testCaseName;
    private static final Reporter REPORTER = new Reporter();
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentTest childTest;
    private static ExtentHtmlReporter htmlReporter;
    private static Path reportPath;
    private static Path screenshotFolder;
    private static boolean buildStatus = true;
    private static ArrayList failuresBucket = new ArrayList<String>();
    private static Path logFolder;
    private static ConcurrentHashMap<Long, ExtentTest> testStorage = new ConcurrentHashMap<>();

    private Reporter() {

        logger.info("Creating the Reporter");

        try {
            // generate report folder name
            Path rootPath = getNewReportPath();
            // create directory if not exists
            if (Files.notExists(rootPath)) {
                reportPath = Files.createDirectories(rootPath);
            } else {
                reportPath = rootPath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == extent)
            extent = new ExtentReports();

        htmlReporter = new ExtentHtmlReporter(Paths.get(reportPath.toString(), filePath).toAbsolutePath().toFile());
        htmlReporter.config().setChartVisibilityOnOpen(false);
        htmlReporter.config().setDocumentTitle("Yura");
        htmlReporter.config().setReportName("Testing parallel execution with TestNG");
        htmlReporter.config().setTheme(Theme.DARK);

        extent.attachReporter(htmlReporter);

        logger.info("The ReportManger has been successfully created.");
    }

    public static synchronized ExtentTest addTest(String testName) {
        ExtentTest testCase = extent.createTest(testName);
        testStorage.put(Thread.currentThread().getId(), testCase);
        return testCase;
        //return extent.createTest(testName);
    }

    public static void saveAndQuit() {
        extent.flush();
    }

    private static Path getNewReportPath() {
        LocalDateTime dateTime = LocalDateTime.now();
        String reportName = "report" + "_" + dateTime.toLocalDate() + "_" + dateTime.toLocalTime().getHour() + "_"
                + dateTime.toLocalTime().getMinute();

        return Paths.get(root, "report", reportName);
    }


    public static void fail(String log,
                            String testCaseName) {
        try {
            String screenshotPath = takeScreenshot(testCaseName).substring(takeScreenshot(testCaseName).indexOf("screenshots"));
            childTest.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            buildStatus = false;
            failuresBucket.add(log);
        } catch (Exception e) {
            test.fail(log);
        }
    }

    public static synchronized String takeScreenshot(String testCaseName) {

        try {
            screenshotFolder = Paths.get(reportPath.toString(), "screenshots");

            if (Files.notExists(screenshotFolder))
                Files.createDirectory(screenshotFolder);

            String fileName = testCaseName.replace(" ", "_");
            Path screenshotPath = Paths.get(screenshotFolder.toString(), fileName + ".png");
            Screenshot screenshot = null;
            screenshot = new AShot()
                    .takeScreenshot(DriverManager.getDriver());
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath.toString()));

            return screenshotPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
