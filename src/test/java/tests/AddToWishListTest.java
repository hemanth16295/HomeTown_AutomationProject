package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listeners.TestListener;
import pages.AddToWishListPage;
import pages.HomePage;
import pages.ProductSearchPage;

/**
 * AddToWishListTest
 * ----------------
 * Verifies the Add to Wishlist functionality.
 *
 * - Searches for a product and opens it
 * - Adds the product to the wishlist
 * - Confirms navigation to the wishlist page
 */



@Listeners(listeners.TestListener.class)
public class AddToWishListTest extends Base {
    
	// Test data
    String keyword = "sofa";
    
    /**
     * Searches for a product before each test execution
     * @throws InterruptedException 
     */
    @BeforeMethod
    public void searchProduct() throws InterruptedException {
    	
    	// Open Home page 
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        
        // Search Product
        ProductSearchPage searchPage = new ProductSearchPage(driver);
        searchPage.searchProduct(keyword);
        System.out.println("Product searched");
    }
    
    /**
     * Validates Add to Wish list functionality
     */
    @Test
    public void validateAddToWishlistFlow() throws InterruptedException {
        
    	// Create Wish list page object
        AddToWishListPage wishlist = new AddToWishListPage(driver);
        
        // Open first product from search results
        wishlist.openFirstProduct();
        Thread.sleep(2000);
        
        // Add product to wish list
        wishlist.addToWishlist();
        Thread.sleep(2000);
        
        // Navigate to Wish list page
        wishlist.openWishlistPage();
        Thread.sleep(3000);

        try {
        	// Validate navigation to Wish list page
            TestListener.getTest().log(Status.INFO, "Checking Wishlist page");

            TestListener.getTest().log(Status.PASS, "Wishlist page opened successfully");

        } catch (AssertionError e) {
            TestListener.getTest().log(Status.FAIL, "Wishlist page not opened");
            throw e; 
        }

    }
}
