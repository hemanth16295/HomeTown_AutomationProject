package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentReportManager
 * -------------------
 * Utility class to configure and manage Extent Reports for test execution.
 *
 * Responsibilities:
 * - Implements Singleton pattern to ensure only one ExtentReports instance exists
 * - Configures ExtentSparkReporter for HTML output
 * - Sets report name and document title for HTML report
 * - Attaches SparkReporter to ExtentReports instance
 * - Adds system/environment information (Tester, Framework, Browser)
 * - Provides a globally accessible ExtentReports instance for listeners and tests
 *
 * Usage:
 * - Used by TestListener to create test logs and generate HTML reports
 */


public class ExtentReportManager {
    
	// Singleton instance of ExtentReports
    // Ensures only one report is generated during entire test execution
    private static ExtentReports extent;
    
    /**
     * Returns the ExtentReports instance
     * If not already created, it initializes and configures the report
     * @return ExtentReports instance to be used in tests/listeners
     */
    public static ExtentReports getExtentReports() {
    	// Check if ExtentReports instance is already created
        if (extent == null) {
        	/// Create a new HTML reporter (Spark Reporter)
            // This is the actual HTML file that will be generated
            ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ExtentReport.html");
            
            // Report UI configuration
            reporter.config().setReportName("Automation Test Report"); // Set the name of the report that will appear on the HTML page
            reporter.config().setDocumentTitle("Hometown Automation"); // Set the title of the HTML document (shown in browser tab)
            
            // Create the ExtentReports instance
            extent = new ExtentReports();
            // Attach the SparkReporter to ExtentReports instance
            // Without this, no HTML report will be generated
            extent.attachReporter(reporter);
            
            // Add custom system/environment information to the report
            // These details are visible on the report dashboard
            extent.setSystemInfo("Tester", "Hemanth"); // Name of the tester
            extent.setSystemInfo("Framework", "Selenium + TestNG + POM"); // Test framework used
            extent.setSystemInfo("Browser", "Chrome"); // Browser used for tests
        }
        return extent;
    }
}
