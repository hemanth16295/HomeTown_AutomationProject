package pages;

import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ProductSearchPage
 * -----------------
 * Handles product search actions and result detection on the search page.
 *
 * - Enters product keyword in search box
 * - Submits search request
 * - Identifies product results or "No results" message
 */

public class ProductSearchPage {

	// WebDriver interface reference
	WebDriver driver;
	// WebDriverWait reference
	WebDriverWait wait;
    
    // Initialize driver, wait and page elements
    public ProductSearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    // search box
    @FindBy(name = "q")   
    private WebElement searchBox;
    
    // first product result
    @FindBy(xpath = "//div[contains(@class,'product')]")  
    private WebElement searchResult;
    
    // No results message (shown ONLY for invalid search)
    @FindBy(xpath = "//*[@id='shopify-section-template--26699252564337__main']/div/div[1]/div[2]/h4")
    private WebElement noResultsMessage;

    /**
     * Enters product name in search box and submits search
     */
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox))
            .clear(); // clear any previous text
        searchBox.sendKeys(productName, Keys.ENTER);
    }
    
    /**
     * Verifies whether search results are displayed
     * true  → Product results are visible (valid search)
     * false → No product results found or element not present
     */
    public boolean isSearchResultDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(searchResult)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Returns true if "No results" message is visible
     * This happens ONLY for invalid searches
     */
    public boolean isNoResultsMessageDisplayed() {
        try {
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false; // element not present or not visible
        }
    }
}
