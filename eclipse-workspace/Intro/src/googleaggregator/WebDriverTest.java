package googleaggregator;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.Test;

	public class WebDriverTest {

		private WebDriver driver;

	    @BeforeTest
	    public void setUp() {
	        driver = AllFunctions.initDriver("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
	        AllFunctions.navigateToURL(driver, "https://shopping.google.com/?nord=1");
	        AllFunctions.maximizeWindow(driver);
	        AllFunctions.setImplicitWait(driver, 10);
	    }

	    @AfterTest
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @Test
	    public void testNavigateToURL() {
	        String currentUrl = driver.getCurrentUrl();
	      //  assertEquals(currentUrl, "https://www.example.com", "URL should match.");
	    }

	    @Test
	    public void testClickOnSearchBarLandingPage() {
	        AllFunctions.clickOnSearchBarLandingPage(driver);
	        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
	        assertTrue(searchBar.isDisplayed(), "Search bar should be visible.");
	    }

	    @Test
	    public void testInputInSearchBar() {
	        String searchText = "Selenium WebDriver";
	        AllFunctions.inputWithInTheSearchBar(driver, searchText);
	        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
	      //  assertEquals(searchBar.getAttribute("value"), searchText, "Search bar should contain the input text.");
	    }

	    @Test
	    public void testSelectFromRecommendations() {
	        AllFunctions.selectFromRecommendations(driver);
	        // Assuming that this action leads to a new page or changes the state in some way, check that.
	        String pageSource = driver.getPageSource();
	        assertTrue(pageSource.contains("Results for Selenium WebDriver"), "The recommendation should lead to the correct page.");
	    }

	    @Test
	    public void testScrollToSeller() {
	        AllFunctions.scrollToSellerLandingPage(driver);
	        WebElement sellerElement = driver.findElement(By.xpath("//div[@class='lg3aE' and text()='Seller']"));
	        assertTrue(sellerElement.isDisplayed(), "Seller section should be visible after scrolling.");
	    }

	    @Test
	    public void testClickOnMoreInSellerMenu() {
	        AllFunctions.clickOnMoreInSellerMenuLandingPage(driver);
	        WebElement moreButton = driver.findElement(By.xpath("//div[@aria-label='More Seller']"));
	        assertFalse(moreButton.isDisplayed(), "The 'More Seller' menu should no longer be visible after clicking.");
	    }

	    @Test
	    public void testGetAllProducts() {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        assertTrue(products.size() > 0, "The product list should not be empty.");
	    }

	    @Test
	    public void testApplyPriceFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applyingPriceFilter(driver, products);
	        // After applying filter, check if price range is applied (this part depends on your UI).
	        assertTrue(driver.getPageSource().contains("₹"), "Price filter should be applied.");
	    }

	    @Test
	    public void testApplyRamFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applyingRamFilter(driver, products);
	        // After applying filter, check if RAM filter is applied (this part depends on your UI).
	        assertTrue(driver.getPageSource().contains("GB"), "RAM filter should be applied.");
	    }

	    @Test
	    public void testCompareName() {
	        String productName1 = "Google Pixel 4";
	        String productName2 = "Pixel 4";
	        AllFunctions.compareName(productName1, productName2);
	        // Since this is a print-based function, you should manually check the output in the console.
	    }

	    @Test
	    public void testCompareNameViaHashMapMethod() {
	        String productName1 = "Google Pixel 4";
	        String productName2 = "Pixel 4";
	        AllFunctions.compareNameViaHashMapMethod(productName1, productName2);
	        // Check the console output for comparisons.
	    }

	    @Test
	    public void testComparePrice() {
	        String price1 = "₹39,999";
	        String price2 = "₹39,999";
	        AllFunctions.compareprice(price1, price2);
	        // Check the console output for comparison.
	    }

	    @Test
	    public void testApplyBrandFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applyingBrandFilter(driver, products);
	        // Check if the filter was applied by checking the brand name.
	        assertTrue(driver.getPageSource().contains("Realme"), "Brand filter should be applied to Realme.");
	    }

	    @Test
	    public void testApply5GFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applying5gFilter(driver, products);
	        // Check if the 5G filter is applied.
	        assertTrue(driver.getPageSource().contains("5G"), "5G filter should be applied.");
	    }

	    @Test
	    public void testApplyDualSimFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applyingDualSimFilter(driver, products);
	        // Check if the Dual SIM filter is applied.
	        assertTrue(driver.getPageSource().contains("Dual SIM"), "Dual SIM filter should be applied.");
	    }

	    @Test
	    public void testApplyColorFilter() throws InterruptedException {
	        List<WebElement> products = AllFunctions.getAllProducts(driver);
	        AllFunctions.applyingColorFilter(driver, products);
	        // Check if the color filter is applied.
	        assertTrue(driver.getPageSource().contains("Black"), "Color filter should be applied.");
	    }
	}


