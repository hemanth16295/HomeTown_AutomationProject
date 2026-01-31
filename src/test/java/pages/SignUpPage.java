package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * SignUpPage
 * ----------
 * Handles user interactions required to complete the sign-up process.
 *
 * - Enters personal details and credentials
 * - Accepts terms and conditions
 * - Submits the registration form
 */

public class SignUpPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;

    // locators
    @FindBy(id = "RegisterForm-FirstName")
    private WebElement firstName;

    @FindBy(id = "RegisterForm-LastName")
    private WebElement lastName;

    @FindBy(id = "RegisterForm-email")
    private WebElement email;

    @FindBy(id = "RegisterForm-phone")
    private WebElement phoneNo;

    @FindBy(id = "RegisterForm-password")
    private WebElement password;
    
    @FindBy(xpath = "//*[@id=\"RegisterForm-terms\"]")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//*[@id=\"create_customer\"]/button")
    private WebElement createButton;
    
    // constructor & initializing web elements using page factory
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // actions methods
    // Enter first name and last name
    public void enterPersonalDetails(String fn, String ln) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fn);
        lastName.sendKeys(ln);
    }
    
    // Enter email, phone number and password
    public void enterCredentials(String mail, String phone, String pwd) {
        email.sendKeys(mail);
        phoneNo.sendKeys(phone);
        password.sendKeys(pwd);
    }
    
    // Accept Terms & Conditions check box
    public void acceptTerms() {
        if (!agreeCheckbox.isSelected()) {
            wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox)).click();
        }
    }

    // method to click create button
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    // Executes complete sign up flow in sequence
    public void registerUser(String fn, String ln, String mail, String phone, String pwd) {
    	// calling all methods 
        enterPersonalDetails(fn, ln);
        enterCredentials(mail, phone, pwd);
        acceptTerms();
        clickRegister();
    }
}
