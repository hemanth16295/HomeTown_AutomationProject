package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listeners.TestListener;
import pages.HomePage;
import pages.SignUpPage;
import utils.ExcelUtils;

/**
 * SignUpTest
 * ----------
 * Verifies sign-up functionality using different user inputs.
 *
 * - Executes sign-up with Excel-driven data
 * - Validates successful registration using URL
 * - Validates failed registration for invalid data
 */


@Listeners(listeners.TestListener.class)
public class SignUpTest extends Base {

	// Excel file path for test data
    String excelFilePath = "D:\\HomeTown_Automation\\hometown_data_driven.xlsx";
    // Excel sheet name
    String sheetName = "SignUp";
    
    /**
     * DataProvider method
     * -------------------
     * Reads test data from Excel and supplies it to test method
     */
    @DataProvider(name = "signUpData")
    public Object[][] getData() throws IOException {
        return ExcelUtils.getExcelData(excelFilePath, sheetName);
    }

    /**
     * Reset browser state before each test iteration
     * Ensures clean execution for every data set
     */
    @BeforeMethod
    public void navigateToHome() {
    	HomePage homePage = new HomePage(driver);
	    homePage.goToSignUpPage();
    }
    
    /**
     * Sign Up Test
     * ------------
     * Executes Sign Up functionality using Excel data.
     *
     * Test Flow:
     * 1. Navigate to Sign Up page
     * 2. Enter user details
     * 3. Submit registration form
     * 4. Validate success or failure based on expected result
     *
     * Extent Report:
     * - Logs each sign-up attempt
     * - Marks test as PASS for expected outcomes
     */
    @Test(dataProvider = "signUpData")
    public void registerUserTest(
            String firstName,
            String lastName,
            String email,
            String phone,
            String password,
            String expectedResult) {
        
    	// Navigate to Sign up page
        HomePage homePage = new HomePage(driver);
        homePage.goToSignUpPage();
        
        // Perform Sign up actions
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.registerUser(firstName, lastName, email, phone, password);
        
        // Capture current URL for validation
        String currentUrl = driver.getCurrentUrl();
        
        // =================== EXTENT REPORT LOGGING ===================
        if (expectedResult.equalsIgnoreCase("success")) {
        	
        	// test listener for extent report
        	TestListener.getTest().log(Status.INFO,"Signup attempt | User: " + email + " | Password: " + password);
            
        	// Validate successful sign up
            Assert.assertEquals(currentUrl,"https://www.hometown.in/","Signup should succeed but did not");
            
            // Mark test as PASS in Extent Report
            TestListener.getTest().pass("Signup passes | User: " + email);

            System.out.println("Signup success for: " + email);

        } else {
        	
        	// Log negative scenario attempt
        	TestListener.getTest().log(Status.INFO,"Signup attempt negative | User: " + email + " | Password: " + password);
            
        	// Validate sign up failure
            Assert.assertTrue(currentUrl.contains("/account/register"),"Signup should fail but user redirected");
            
            // Mark expected failure as PASS in Extent Report
            TestListener.getTest().pass("Signup failed as expected | User: " + email);

            System.out.println("Signup failed as expected for: " + email);
        }
    }
}
