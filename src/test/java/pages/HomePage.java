package pages;

import org.openqa.selenium.WebDriver;

/**
 * HomePage
 * ---------
 * Handles common navigations
 * Acts as a routing page to Login, Sign up & Home
 */

public class HomePage {
    
	// WebDriver interface reference
    WebDriver driver;
    
    // Application URLs
    private String signUpUrl = "https://www.hometown.in/account/register";
    private String loginUrl  = "https://www.hometown.in/account/login";
    private final String homeUrl  = "https://www.hometown.in/";
    
    // Constructor 
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Navigate to Sign up page
    public void goToSignUpPage() {
        driver.get(signUpUrl);
    }
    
    // Navigate to Login page
    public void goToLoginPage() {
        driver.get(loginUrl);
    }
    
    // Open Home page
    public void openHomePage() {
        driver.get(homeUrl);
    }
}
