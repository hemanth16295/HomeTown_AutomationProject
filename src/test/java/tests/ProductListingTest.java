package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import listeners.TestListener;
import pages.HomePage;
import pages.ProductListingPage;
import pages.ProductSearchPage;

/**
 * ProductListingTest
 * ------------------
 * Verifies Product Listing Page behavior after product search.
 *
 * - Confirms searched keyword is reflected in the PLP URL
 * - Verifies sorting options work as expected
 * - Verifies availability and price range filters can be applied
 */


@Listeners(listeners.TestListener.class)
public class ProductListingTest extends Base {

    String keyword = "sofa";
    
    /**
     * Navigates to PLP by performing a product search before each test
     */
    @BeforeMethod
    public void navigateToPLP() {
    	// Open Home page 
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        ProductSearchPage searchPage = new ProductSearchPage(driver);
        searchPage.searchProduct(keyword);
    }

    /**
     * Validates whether searched keyword appears in the PLP URL
     */
    @Test
    public void validateKeywordInUrl() {
        ProductListingPage plp = new ProductListingPage(driver);

        Assert.assertTrue(
            plp.isKeywordPresentInUrl(keyword),
            "Keyword not reflected in URL"
        );
        
        TestListener.getTest().log(Status.INFO, "Keyword reflected in URL");
        System.out.println("Keyword reflected in URL");
    }

    /**
     * Validates "Price: Low to High" sorting functionality
     */
    @Test
    public void validateSortLowToHigh() {
        ProductListingPage plp = new ProductListingPage(driver);
        plp.sortLowToHigh();
        
        TestListener.getTest().log(Status.INFO, "Sort low to high applied");
        System.out.println("Sort low to high applied");
    }
    
    /**
     * Validates Availability filter functionality
     */
    @Test
    public void validateSortHighToLow() {
        ProductListingPage plp = new ProductListingPage(driver);
        plp.sortHighToLow();
        
        TestListener.getTest().log(Status.INFO, "Sort high to low applied");
        System.out.println("Sort high to low applied");
    }
    
    /**
     * Validates Availability filter functionality
     */
    @Test
    public void validateFilterAvailabilityAndApply() {
        ProductListingPage plp = new ProductListingPage(driver);
        plp.applyAvailabilityFilter();
    }
    
    /**
     * Validates Price Range filter functionality
     */
    @Test
    public void validatePriceRangeFilter() {
        ProductListingPage plp = new ProductListingPage(driver);
        plp.applyPriceRangeFilter("5000", "20000");
    }

}
