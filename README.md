# HomeTown Automation Testing 

## Project Overview
This project is an automation testing framework developed to validate key functionalities of the HomeTown e-commerce web application using Selenium WebDriver, Java, TestNG, and Extent Reports.

The framework follows Page Object Model (POM) and Data-Driven Testing principles to ensure scalability, maintainability, and reusable test scripts.

---

## Project Structure

## HomeTown_Automation

### base
- Base.java

### pages
- HomePage.java
- SignUpPage.java
- LoginPage.java
- ProductSearchPage.java
- ProductCardValidationPage.java
- AddToWishListPage.java
- AddToCartPage.java

### tests
- LoginTest.java
- SignUpTEst.java
- ProductSearchTest.java
- ProductCardValidationTest.java
- AddToWishListTest.java
- AddToCartTest.java

### utils
- ExcelUtils.java
- ExtentReportManager.java

### listeners
- TestListener.java

### testdata
- hometown_data_driven.xlsx

### Root Files
- pom.xml


---

## Automated Test Scenarios

### Login Module
- Valid login using correct credentials
- Invalid login using incorrect credentials
- Login validation based on URL change
- Data-driven login testing using Excel

### Product Search Module
- Search using valid product keywords
- Search using invalid product keywords
- Validation of “No results found” message
- Data-driven testing using Excel

### Product Card Validation
- Product cards displayed on Product Listing Page (PLP)
- Price validation for every product (mandatory)
- Discount validation (optional, logged if present)

### Add to Wishlist Module
- User login before wishlist operation
- Product search
- Open product details page
- Add product to wishlist
- Navigate to wishlist page

---

## Reporting
- Extent Reports integration
- Step-by-step execution logs
- Pass and fail status for each test
- Clear execution summary

---

## Test Data Management
- Excel-based test data management using Apache POI
- Separate sheets used for different modules
  - SignUp 
  - SignIn
  - ProductSearch
- Supports both positive and negative test scenarios

---

## How to Run the Tests
### Prerequisites
- Java 8 or higher
- Maven
- Chrome browser
- Compatible ChromeDriver

## Run Using TestNG
- Right-click on the test class or `testng.xml`
- Select **Run As → TestNG Test**

---

## Framework Highlights
- Centralized WebDriver management
- Reusable Page Object classes
- Clean separation between test logic and UI actions
- Scalable and maintainable automation framework

---

## Author
**Hemanth R**  


