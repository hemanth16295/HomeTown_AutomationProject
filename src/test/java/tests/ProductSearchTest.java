package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Base;
import listeners.TestListener;
import pages.HomePage;
import pages.ProductSearchPage;
import utils.ExcelUtils;

/**
 * ProductSearchTest
 * -----------------
 * Verifies product search functionality using different inputs.
 *
 * - Searches products using valid and invalid keywords
 * - Confirms results are shown for valid searches
 * - Confirms "No results" message for invalid searches
 */

@Listeners(listeners.TestListener.class)
public class ProductSearchTest extends Base {
    
	// Excel configuration
    String excelFilePath = "D:\\HomeTown_Automation\\hometown_data_driven.xlsx";
    String sheetName = "ProductSearch"; //sheet with product keywords

    // Provides product search keywords from Excel
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException {
        return ExcelUtils.getExcelData(excelFilePath, sheetName);
    }

    // Load home page before each test iteration
    @BeforeMethod
    public void loadHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    /**
     * Searches for a product and validates search results
     */
    @Test(dataProvider = "searchData")
    public void productSearchTest(String productName, String expectedResult) {

        ProductSearchPage searchPage = new ProductSearchPage(driver);
        searchPage.searchProduct(productName);

        boolean noResultDisplayed = searchPage.isNoResultsMessageDisplayed();
        
        // for valid scenarios
        if (expectedResult.equalsIgnoreCase("success")) {

            // Valid search → No-results message should NOT be visible
            Assert.assertFalse(noResultDisplayed,"No results message displayed for valid search");
            TestListener.getTest().pass("Search results displayed for valid product: " + productName);
            System.out.println("Search results displayed for: " + productName);

        } else {

            // Invalid search → No-results message SHOULD be visible
            Assert.assertTrue(noResultDisplayed,"No results message NOT displayed for invalid search");
            TestListener.getTest().pass("No results message displayed for invalid search: " + productName);
            System.out.println("No results message displayed for invalid search: " + productName);
        }

    }
}
