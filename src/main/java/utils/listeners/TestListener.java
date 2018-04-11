package utils.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public synchronized void onTestStart(ITestResult iTestResult) {
        System.out.println("THE EXECUTION OF " + getTestMethodName(iTestResult) + " STARTED!");
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        System.out.println("THE TEST " + getTestMethodName(iTestResult) + " PASSED!");
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        System.out.println("THE TEST " + getTestMethodName(iTestResult) + " FAILED!. Please see the logs and report for more details.");
    }

    @Override
    public synchronized void onTestSkipped(ITestResult iTestResult) {
        System.out.println("THE TEST " + getTestMethodName(iTestResult) + " SKIPPED");
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public synchronized void onStart(ITestContext iTestContext) {

    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {

    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getName().toString().toUpperCase();
    }

}
