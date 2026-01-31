package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import listeners.TestListener;

/**
 * ProductCardValidationPage
 * -------------------------
 * Performs validations on product card UI elements in the listing page.
 *
 * - Identifies all product cards on the page
 * - Checks presence of price element inside each card
 * - Detects discount element if available
 * - Logs product-level validation details
 */


public class ProductCardValidationPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
	
    // web elements 
    By products = By.xpath("//*[@id='ProductsList']/div");

    // Discount inside product card (relative)
    By discount = By.xpath(".//span[contains(text(),'%')]");

    // Price inside product card (relative)
    By price = By.xpath(".//span[contains(@class,'price')]");
    
    // Constructor initializes driver, wait and page elements
    public ProductCardValidationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * Validates discount and price presence for all displayed products
     *
     * Logic:
     * - Fetch all product cards
     * - Loop through each product
     * - Discount: Optional (log info only)
     * - Price: Mandatory (fail test if missing)
     *
     * Logs results to:
     * - Console
     * - Extent Report
     */
    public void validateDiscountAndPriceForAllProducts() {
        
    	// Get all product cards
        List<WebElement> productList = driver.findElements(products);
        System.out.println("Total products displayed: " + productList.size());

        int index = 1;
        
        // Iterate through each product card
        for (WebElement product : productList) {

            System.out.println("\nChecking product #" + index);

            // ---------------- Discount validation ----------------
            if (product.findElements(discount).size() > 0) {
            	TestListener.getTest().log(Status.INFO, "Discount present");
                System.out.println("Discount present");
            } else {
            	TestListener.getTest().log(Status.INFO, "Discount not available (acceptable)");
                System.out.println("Discount not available (acceptable)");
            }

             // ---------------- Price validation ----------------
            if (product.findElements(price).size() > 0) {
            	TestListener.getTest().log(Status.INFO, "Price present");
                System.out.println("Price present");
            } else {
            	// Fail immediately if price is missing
                throw new AssertionError("Price missing for product #" + index);
            }

            index++;
        }
        
         // Final summary log
        TestListener.getTest().log(Status.INFO, "\n Discount & Price validation completed for all products");
        System.out.println("\n Discount & Price validation completed for all products");
    }
}
