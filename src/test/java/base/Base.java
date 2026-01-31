package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Base class
 * -----------
 * Initializing WebDriver
 * Browser configuration
 * Opening application URL
 * Common setup & tear down for all tests
 */

public class Base {
    
	// Shared WebDriver instance for all test classes
    public static WebDriver driver;
    
    /**
     * Runs once before all tests
     * Launches browser and navigates to application
     */
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver(); // Launch browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
        driver.manage().window().maximize(); // Maximize browser
        driver.get("https://www.hometown.in/"); // Open application
    }
    
    /**
     * Runs once after all tests
     * Browser quit is optional (kept commented for debugging)
     */
    @AfterTest
    public void tearDown() {
        // driver.quit();
    }
}

