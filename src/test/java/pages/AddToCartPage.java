package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import listeners.TestListener;

/**
 * AddToCartPage
 * -------------
 * Handles user interactions related to adding products to the cart.
 *
 * - Opens a product from the listing page
 * - Adds product to cart
 * - Increases and decreases product quantity
 * - Navigates to the cart page
 */

public class AddToCartPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
    
    // Constructor initializes driver, wait, and page elements
    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    // ================== LOCATORS ==================
    
    // First product 
    By firstProduct = By.xpath("//*[@id='ProductsList']/div[1]//a");

    // Add to cart button 
    By addToCartBtn = By.xpath("//*[@id='ProductSubmitButton-template--26699252466033__main']");

    // Quantity increase
    By increaseQtyBtn = By.xpath("//button[contains(@aria-label,'Increase')]");

    // Quantity decrease
    By decreaseQtyBtn = By.xpath("//button[contains(@aria-label,'Decrease')]");

    // View cart button
    By viewCartBtn = By.xpath("//*[@id=\"CartDrawerFooter-sections--26699245158769__cart-drawer\"]/div[2]/div/form/a");

    // ================== ACTION METHODS ==================
    
    /**
     * Opens first product from listing page
     */
    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        TestListener.getTest().log(Status.INFO, "Product opened successfull");
        System.out.println("Product page opened");
    }
    
    /**
     * Clicks Add to Cart button
     */
    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        TestListener.getTest().log(Status.INFO, "Add to Cart clicked successfull");
        System.out.println("Add to Cart clicked");
    }
    
    /**
     * Increases product quantity by 1
     */
    public void increaseQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(increaseQtyBtn)).click();
        TestListener.getTest().log(Status.INFO, "Quantity increased successfull");
        System.out.println("Quantity increased");
    }
    
    /**
     * Decreases product quantity by 1
     */
    public void decreaseQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(decreaseQtyBtn)).click();
        TestListener.getTest().log(Status.INFO, "Quantity decreased successfull");
        System.out.println("Quantity decreased");
    }
    
    /**
     * Clicks View Cart button to navigate to Cart page
     */
    public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
        TestListener.getTest().log(Status.INFO, "Navigated to Cart page successfull");
        System.out.println("Navigated to Cart page");
    }
}
