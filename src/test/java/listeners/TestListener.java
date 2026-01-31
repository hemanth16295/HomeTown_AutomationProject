package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import utils.ExtentReportManager;

/**
 * TestListener
 * ------------
 * Listens to TestNG test execution events and integrates with Extent Reports.
 *
 * Responsibilities:
 * - Create and manage ExtentTest entries for each test
 * - Log test start, pass, and failure events
 * - Capture exception details for failed tests
 * - Support parallel execution using ThreadLocal
 * - Flush final Extent Report after all tests complete
 *
 * Provides a utility method getTest() to access the current ExtentTest instance
 * from test classes or page classes for logging steps.
 */


public class TestListener implements ITestListener {
    
	// Thread-safe ExtentTest instance for parallel execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    // Shared ExtentReports instance
    private static ExtentReports extent = ExtentReportManager.getExtentReports();
    
    /**
     * Triggered before each test method starts
     * Creates a new test entry in the Extent Report
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        test.log(Status.INFO, "Test Started");
    }
    
    /**
     * Triggered when a test method passes successfully
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }
     
    /**
     * Triggered when a test method fails
     * Logs the exception/stack trace in the Extent Report
     */
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getThrowable());
    }
    
    /**
     * Triggered after all tests are executed
     * Flushes the Extent Report to generate the final HTML report
     */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    /**
     * Utility method to access the current ExtentTest instance
     * from test classes or page classes
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
