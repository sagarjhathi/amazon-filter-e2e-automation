package googleaggregator;

import  googleaggregator.GenericHelper ;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

public class GoogleAggregatorTests  implements ITestListener{

    
	 private WebDriver driver;
	    private GenericHelper helper;

	    @BeforeClass
	    public void setup() {
	        // Initialize the GenericHelper object
	        this.helper = new GenericHelper();
	        
	        // Initialize the WebDriver using the helper's userAgent
	        this.driver = AllFunctions.initDriver(helper.userAgent);
	    }

	    @Test(priority = 1)
	    public void navigatingToTheUrl() {
	        AllFunctions.navigateToURL(driver, helper.url);
	    }

	    @Test(priority = 2)
	    public void maximizingTheWindow() {
	        AllFunctions.maximizeWindow(driver);
	    }

	    @Test(priority = 3)
	    public void enableImplicitWaits() {
	        AllFunctions.setImplicitWait(driver, helper.implicitWait);
	    }

	    @Test(priority = 4)
	    public void enablePageLoadTimeOut() {
	        AllFunctions.setPageLoadOutTimeOut(driver, helper.pageLoadOutTime);
	    }

	    @Test(priority = 5)
	    public void clickingOnSearchBar() {
	        AllFunctions.clickOnSearchBarLandingPage(driver);
	    }

	    
	    @Test(priority = 6)
	    public void givingInputIntoSearchBar() {
	        AllFunctions.inputWithInTheSearchBar(driver, helper.inputForSearch);
	    }
	    
//	    @DataProvider
//	    public Object[][] getData() {
//	        Object[][] data = new Object[3][1]; // 3 rows, 1 column
//	        data[0][0] = "Laptop";  // First row, first column
//	        data[1][0] = "Camera";  // Second row, first column
//	        data[2][0] = "Phone";   // Third row, first column (you need to add this or a similar value)
//	        return data;
//	    }
	    
	    
	    @Test(priority = 7)
	    public void selectingFromRecommendation() {
	        AllFunctions.selectFromRecommendations(driver);
	    }

	    @Test(priority = 8)
	    public void scrollingToSellerMenuInLandingPage() {
	        AllFunctions.scrollToSellerLandingPage(driver);
	    }
	    
	    
	    @Test(priority = 9)
	    public void clickingOnMoreButtonUnderSellerMenu() {
	        AllFunctions.clickOnMoreInSellerMenuLandingPage(driver);
	    }
	    
	    @Test(priority = 10)
	    public void scrollingUpByPixels() {
	        AllFunctions.scrollByPixels(driver, 0, -300);
	    }
	      
	    @Test(priority=11)
	    public void selectingAmazonFromSellerMenu() {
	        AllFunctions.selectAmazonFromSellerMenu(driver);
	    }
	    
	    @Test(priority=12)
	    public void refreshPageToAvoidDomIssues() {
	        driver.navigate().refresh();
	    }
	    
	    @Test(priority=13)
	    public void creatingListofProducts() {
	        helper.productList = AllFunctions.getAllProducts(driver);
	    }
	    
	    @Test(priority = 14)
	    public void applyingPriceFilter() {
	        try {
	            AllFunctions.applyingPriceFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 15)
	    public void applying5gFilter() {
	        try {
	            AllFunctions.applying5gFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 16)
	    public void applyingBrandFilter() {
	        try {
	            AllFunctions.applyingBrandFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 17)
	    public void applyingCameraFilter() {
	        try {
	            AllFunctions.applyingCameraFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 18)
	    public void applyingCellularNetworkFilter() {
	        try {
	            AllFunctions.applyingCellularNetworkFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 19)
	    public void applyingColorFilter() {
	        try {
	            AllFunctions.applyingColorFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 20)
	    public void applyingDeliveryFilter() {
	        try {
	            AllFunctions.applyingDeliveryFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 21)
	    public void applyingDualSimFilter() {
	        try {
	            AllFunctions.applyingDualSimFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 22)
	    public void applyingHeadphoneFilter() {
	        try {
	            AllFunctions.applyingHeadphoneFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 23)
	    public void applyingLensFilter() {
	        try {
	            AllFunctions.applyingLensFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 24)
	    public void applyingOsFilter() {
	        try {
	            AllFunctions.applyingOsFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 25)
	    public void applyingRamFilter() {
	        try {
	            AllFunctions.applyingRamFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 26)
	    public void applyingRatingFilter() {
	        try {
	            AllFunctions.applyingRatingFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 27)
	    public void applyingScreenResolutionFilter() {
	        try {
	            AllFunctions.applyingScreenResolutionFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 28)
	    public void applyingSecurityFeaturesFilter() {
	        try {
	            AllFunctions.applyingSecurityFeaturesFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 29)
	    public void applyingStorageFilter() {
	        try {
	            AllFunctions.applyingStorageFilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Test(priority = 30)
	    public void applyingWeightFilter() {
	        try {
	            AllFunctions.applyingWeightfilter(driver, helper.productList);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	   
	    
	    

}
