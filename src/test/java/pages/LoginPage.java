package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage
 * ---------
 * Handles user interactions and helper checks for the Login page.
 *
 * - Enters login credentials
 * - Submits login form
 * - Provides URL-based login success check
 */

public class LoginPage {
	
	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
	
	// web elements locators
	@FindBy(id = "CustomerEmail")
    private WebElement email;

    @FindBy(id = "CustomerPassword")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"customer_login\"]/button")
    private WebElement loginButton;
		
    // ================== Constructor ==================
    /**
     * Initializes WebDriver, WebDriverWait, and PageFactory elements
     */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// ================== Action Methods ==================
	
	 /**
     * Enters email and password values
     */
	public void setValues(String email_val, String password_val) {
		email.clear();
	    email.sendKeys(email_val);

	    password.clear();
	    password.sendKeys(password_val);
	}
	
	/**
     * Clicks Login button
     */
	public void clickLogin() throws InterruptedException {
		loginButton.click();
	}
	
	/**
     * Validates login success by checking URL change
     * @return true if login successful, false otherwise
     */
	public boolean isLoginSuccessfulByUrl() {
	    return !driver.getCurrentUrl().contains("/account/login");
	}


}

