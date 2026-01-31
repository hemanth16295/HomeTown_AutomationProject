package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listeners.TestListener;
import pages.HomePage;
import pages.LoginPage;

import utils.ExcelUtils;

/**
 * LoginTest
 * ---------
 * Verifies login functionality using different credentials.
 *
 * - Executes login with valid and invalid data
 * - Validates successful login using URL change
 * - Validates failed login by staying on login page
 */

@Listeners(listeners.TestListener.class)
public class LoginTest extends Base {
	
	// Excel file path and sheet name
	String excelFilePath = "D:\\HomeTown_Automation\\hometown_data_driven.xlsx";
	String sheetName = "SignIn";
		
	 /**
     * DataProvider to read login test data from Excel
     */
	@DataProvider(name = "fbLoginData")
	public Object[][] getData() throws IOException {
		return ExcelUtils.getExcelData(excelFilePath, sheetName);
	}
    
	/**
     * Login Test
     * ----------
     * Executes login flow for each Excel data set
     * Logs execution steps and results to Extent Report
     */
	@Test(dataProvider = "fbLoginData")
	public void facebookLoginTest(String email, String password, String expectedResult) throws InterruptedException {
        
		// Navigate to Login page
	    HomePage homePage = new HomePage(driver);
	    homePage.goToLoginPage();
        
	    // Perform Login actions
	    LoginPage page = new LoginPage(driver);
	    page.setValues(email, password);
	    page.clickLogin();
        
	    // ================== LOGIN VALIDATION USING EXPECTED RESULT + URL ==================
	    
	    // Validate based on expected result from Excel
	    if (expectedResult.equalsIgnoreCase("success")) {
	    	// If success is expected, URL should change (login page → account page)
	        if (page.isLoginSuccessfulByUrl()) {
	        	// Log success in Extent Report
	        	TestListener.getTest().log(Status.INFO, "Login passed | User: " + email + "," + "Password: " +password);
	            System.out.println("Login passed | User: " + email + "," +"Password: " +password);
	            Assert.assertTrue(true);
	        } else {
	        	// Expected success but URL did not change → FAIL
	        	TestListener.getTest().log(Status.INFO, "Login failed expected success | User: " + email + "," + "Password: " +password);
	            System.out.println("Login failed expected success | User: " + email + "," + "Password: " +password);
	            Assert.fail("Login should be successful but URL did not change");
	        }

	    } else {
	    	// If failure is expected, user should remain on login page
	        if (driver.getCurrentUrl().contains("/account/login")) {
	        	// Expected failure logged as PASS
	        	TestListener.getTest().log(Status.INFO, "Login failed as expected | Invalid User: " + email + "," + "Password: " +password);
	            System.out.println("Login failed as expected | Invalid User: " + email + "," +"Password: " +password);
	            Assert.assertTrue(true);
	        } else {
	        	// Expected failure but URL changed → FAIL
	        	TestListener.getTest().log(Status.INFO, "Login passed Expected Failure | User: " + email + "," +"Password: " +password);
	            System.out.println("Login passed Expected Failure | User: " + email + "," +"Password: " +password);
	            Assert.fail("Login should fail but URL changed");
	        }
	    }
	}

}
