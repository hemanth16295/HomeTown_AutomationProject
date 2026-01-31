package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import listeners.TestListener;

/**
 * ProductListingPage
 * ------------------
 * Handles user interactions and helper methods on the Product Listing Page.
 *
 * - Performs product sorting using sort dropdown
 * - Applies availability and price range filters
 * - Checks keyword presence in PLP URL
 */


public class ProductListingPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
    
    // Constructor initializes driver, wait and web elements
    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    // ================== LOCATORS ==================
    
    // sort drop down
    @FindBy(xpath = "//*[@id=\"SortBy-search\"]")
    private WebElement sortDropdown;
    
    // Availability filter expand
    By availabilityFilter = By.xpath("//*[@id='FacetFiltersContainer']/div/form/details[1]/summary");

    // buy online check box element
    By buyOnlineCheckbox = By.xpath("//*[@id=\"vertical-filter.v.availability-1\"]");
    
    // Price filter summary (click to expand)
    By priceFilter = By.xpath("//*[@id=\"FacetFiltersContainer\"]/div/form/details[2]/summary");

    // Price min & max input fields
    By priceMinInputField = By.xpath("//*[@id=\"FacetFormInputFromPrice-vertical\"]");
    By priceMaxInputField = By.xpath("//*[@id=\"FacetFormInputToPrice-vertical\"]");


    // ================== METHODS ==================
    
    /**
     * Verifies whether searched keyword is reflected in the PLP URL
     */
    public boolean isKeywordPresentInUrl(String keyword) {
        return driver.getCurrentUrl().toLowerCase().contains(keyword.toLowerCase());
    }
    
    /**
     * Applies "Price: Low to High" sorting using dropdown
     */
    public void sortLowToHigh() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price, low to high");
    }
    
    /**
     * Applies "Price: High to Low" sorting using dropdown
     */
    public void sortHighToLow() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price, high to low");
    }
    
    /**
     * Applies Availability filter by selecting "Buy Online" checkbox
     */
    public void applyAvailabilityFilter() {
    	try {
    		// Expand Availability filter section
            WebElement availability = wait.until(
                    ExpectedConditions.elementToBeClickable(availabilityFilter)
            );
            availability.click();
            
            TestListener.getTest().log(Status.INFO, "Availability filter opened");
            System.out.println("Availability filter opened");

            // Select Buy Online checkbox
            WebElement buyOnline = wait.until(
                    ExpectedConditions.elementToBeClickable(buyOnlineCheckbox)
            );
            buyOnline.click();
            
            Thread.sleep(3000);
            
            TestListener.getTest().log(Status.INFO, "Buy Online filter applied - PASS");
            System.out.println("Buy Online filter applied - PASS");
            } catch (Exception e) {
            	TestListener.getTest().log(Status.INFO, "Failed to apply Buy Online filter - FAIL");
                System.out.println("Failed to apply Buy Online filter - FAIL");
            e.printStackTrace();
            }
    }
    
    /**
     * Applies price range filter by entering minimum and maximum values
     */
    public void applyPriceRangeFilter(String minPrice, String maxPrice) {
        try {
        	// Expand Price filter section
            WebElement priceSection = wait.until(
                    ExpectedConditions.elementToBeClickable(priceFilter)
            );
            priceSection.click();
            
            TestListener.getTest().log(Status.INFO, "Price filter expanded");
            System.out.println("Price filter expanded");

            Thread.sleep(1000); // allow animation to finish

            // Enter minimum price
            WebElement minInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(priceMinInputField)
            );
            minInput.clear();
            minInput.sendKeys(minPrice);
            
            TestListener.getTest().log(Status.INFO, "Min price entered: " + minPrice);
            System.out.println("Min price entered: " + minPrice);

            Thread.sleep(500);

            // Enter maximum price
            WebElement maxInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(priceMaxInputField)
            );
            maxInput.clear();
            maxInput.sendKeys(maxPrice);
            
            Thread.sleep(3000);
            
            TestListener.getTest().log(Status.INFO, "Max price entered: " + maxPrice);
            System.out.println("Max price entered: " + maxPrice);
            
            TestListener.getTest().log(Status.INFO, "Price range filter applied - PASS");
            System.out.println("Price range filter applied - PASS");

        } catch (Exception e) {
        	TestListener.getTest().log(Status.INFO, "Failed to apply price range filter - FAIL");
            System.out.println("Failed to apply price range filter - FAIL");
            e.printStackTrace();
        }
    }
 
}

