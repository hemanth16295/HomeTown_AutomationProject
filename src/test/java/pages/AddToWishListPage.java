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
 * AddToWishListPage
 * -----------------
 * Handles user interactions related to Wishlist functionality.
 *
 * - Opens a product from the product listing page
 * - Adds product to the wishlist from product detail page
 * - Navigates to the wishlist page
 */


public class AddToWishListPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
    
    // Constructor initializes WebDriver, WebDriverWait and PageFactory elements
    public AddToWishListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    // ================= LOCATORS =================
    // First product 
    By firstProduct = By.xpath("//*[@id='ProductsList']/div[1]//a");

    // Wish list button 
    By wishlistBtn = By.xpath("//*[@id=\"shopify-block-AcmpBOE1iWUNLeXFCZ__mst_wishlist_marketing_flow_v3_app_block_product_fwFj3F\"]/a");

    // Wish list page link
    By wishlistPageLink = By.xpath("//*[@id=\"shopify-section-sections--26699243946353__header\"]/header/div[1]/div[4]/div/div/div/div");

    // ================= ACTION METHODS =================
    /**
     * Opens the first product from the Product Listing Page
     */
    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        TestListener.getTest().log(Status.INFO, "Product page opened");
        System.out.println("Product opened from PLP");
    }
    
    /**
     * Clicks on Add to Wish list button on Product Detail Page
     */
    public void addToWishlist() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn)).click();
        TestListener.getTest().log(Status.INFO, "Add to Wishlist clicked");
        System.out.println("Add to Wishlist clicked");
    }
    
    /**
     * Navigates to Wish list page from header
     */
    public void openWishlistPage() {
        wait.until(ExpectedConditions.elementToBeClickable(wishlistPageLink)).click();
        TestListener.getTest().log(Status.INFO, "Navigated to Wishlist page");
        System.out.println("Navigated to Wishlist page");
    }
}
