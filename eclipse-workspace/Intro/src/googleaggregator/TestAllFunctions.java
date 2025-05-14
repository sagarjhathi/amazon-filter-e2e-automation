package googleaggregator;




import java.util.List ; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import googleaggregator.AllFunctions;
import googleaggregatorPOM.GoogleShoppingMainPage;

public class TestAllFunctions {

    public static void main(String[] args) {
        // 1. Initialize WebDriver
       // String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36";
        WebDriver driver = AllFunctions.initDriver(userAgent);
        
        if (driver == null) {
            System.out.println("WebDriver initialization failed.");
            return;
        }
        
        try {
            // 2. Set up the initial conditions for testing
            AllFunctions.setImplicitWait(driver, 10); // Set implicit wait of 10 seconds
            AllFunctions.maximizeWindow(driver);      // Maximize the window
            AllFunctions.navigateToURL(driver, "https://shopping.google.com/"); // Navigate to the URL
            
            // 3. Test the click on search bar
            AllFunctions.clickOnSearchBarLandingPage(driver);
            
            // 4. Test typing in the search bar
            AllFunctions.inputWithInTheSearchBar(driver, "Mobile");

            // 5. Test selecting from recommendations
            AllFunctions.selectFromRecommendations(driver);

            // 6. Test scrolling to seller section
            AllFunctions.scrollToSellerLandingPage(driver);

            // 7. Test clicking on "More Seller" in the seller menu
            AllFunctions.clickOnMoreInSellerMenuLandingPage(driver);

            // 8. Test scrolling by pixels (example scrolling up)
            AllFunctions.scrollByPixels(driver, 0, -300);

            // 9. Test selecting Amazon from the seller menu
            AllFunctions.selectAmazonFromSellerMenu(driver);

            // 10. Test getting all products from the product grid
            System.out.println("Fetching all products...");
            driver.navigate().refresh();  // Refresh the page to ensure we're fetching fresh products
            Thread.sleep(3000);  // Wait for page to load
            
            var productList = AllFunctions.getAllProducts(driver);
            System.out.println("Found " + productList.size() + " products.");

            
            //Applying price filter and iterating over the products
           // GoogleShoppingMainPage gp=new GoogleShoppingMainPage(driver);
//            AllFunctions.switchWindowAndCompare(driver, productList);
            AllFunctions.applyingScreenResolutionFilter(driver, productList);
            AllFunctions.applyingPriceFilter(driver, productList);
            AllFunctions.applying5gFilter(driver, productList);
            AllFunctions.applyingBrandFilter(driver, productList);
            AllFunctions.applyingCameraFilter(driver, productList);
            AllFunctions.applyingCellularNetworkFilter(driver, productList);
            AllFunctions.applyingColorFilter(driver, productList);
            AllFunctions.applyingDeliveryFilter(driver, productList);
            AllFunctions.applyingDualSimFilter(driver, productList);
            AllFunctions.applyingHeadphoneFilter(driver, productList);
            AllFunctions.applyingLensFilter(driver, productList);
            AllFunctions.applyingOsFilter(driver, productList);
            AllFunctions.applyingRamFilter(driver, productList);
            AllFunctions.applyingRatingFilter(driver, productList);
           
            AllFunctions.applyingSecurityFeaturesFilter(driver, productList);
            AllFunctions.applyingStorageFilter(driver, productList);
            AllFunctions.applyingWeightfilter(driver, productList);


        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
        } finally {
            // 12. Quit the driver after the test
            if (driver != null) {
//                driver.quit();
            }
        }
    }
}


