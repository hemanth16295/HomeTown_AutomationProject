package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listeners.TestListener;
import pages.AddToCartPage;
import pages.HomePage;
import pages.ProductSearchPage;

/**
 * AddToCartTest
 * -------------
 * Verifies the Add to Cart functionality end to end.
 *
 * - Searches for a product and opens it
 * - Adds the product to the cart
 * - Validates quantity increase and decrease actions
 * - Confirms navigation to the cart page
 */


@Listeners(listeners.TestListener.class)
public class AddToCartTest extends Base {

    String keyword = "sofa";
    
    /**
     * Searches for a product before each test
     */
    @BeforeMethod
    public void searchProduct() {
    	// Open Home page 
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        ProductSearchPage searchPage = new ProductSearchPage(driver);
        searchPage.searchProduct(keyword);
        System.out.println("Product searched");
    }
    
    /**
     * Validates Add to Cart flow:
     * 1. Open first product
     * 2. Add to cart
     * 3. Increase/decrease quantity
     * 4. Navigate to Cart page
     * 5. Verify cart page opened
     */
    @Test
    public void validateAddToCartFlow() throws InterruptedException {

        AddToCartPage cart = new AddToCartPage(driver);

        cart.openFirstProduct(); // Open first product
        cart.addProductToCart(); // Add product to cart
        Thread.sleep(3000);

        cart.increaseQuantity(); // Increase quantity
        Thread.sleep(2000);

        cart.decreaseQuantity(); // Decrease quantity
        Thread.sleep(2000);

        cart.clickViewCart(); // Navigate to cart
        Thread.sleep(3000);

        try {
            TestListener.getTest().log(Status.INFO, "Verifying navigation to Cart page");

            Assert.assertTrue(driver.getCurrentUrl().contains("cart"),"Not navigated to Cart page");

            TestListener.getTest().log(Status.PASS, "Add to Cart flow completed successfully");
            System.out.println("Add to Cart flow completed successfully");

        } catch (AssertionError e) {
            TestListener.getTest().log(Status.FAIL, "Cart page not opened after Add to Cart");
            throw e;
        }

    }
}
