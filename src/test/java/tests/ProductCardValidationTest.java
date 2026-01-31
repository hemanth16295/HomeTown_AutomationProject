package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.ProductCardValidationPage;
import pages.ProductSearchPage;

/**
 * ProductCardValidationTest
 * -------------------------
 * Verifies that product cards meet mandatory display requirements.
 *
 * - Opens product listing page via search
 * - Ensures every product card displays a price
 * - Confirms discount information is handled correctly
 */


@Listeners(listeners.TestListener.class)
public class ProductCardValidationTest extends Base {
    
	// Search keyword for product listing
    String keyword = "sofa";
    
    /**
     * Pre-condition:
     * - Navigate to home page
     * - Perform product search before each test
     */
    @BeforeMethod
    public void searchProduct() {
    	// Open Home page 
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        // Perform product search // object
        ProductSearchPage searchPage = new ProductSearchPage(driver);
        searchPage.searchProduct(keyword);
    }
    
    /**
     * Test:
     * Validates that:
     * - Each product card has a price
     * - Discount presence is logged if available
     */
    @Test
    public void validateProductCardDetails() {
        ProductCardValidationPage page = new ProductCardValidationPage(driver);
        page.validateDiscountAndPriceForAllProducts();
    }
}
