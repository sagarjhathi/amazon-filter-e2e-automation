package googleaggregator;

import  googleaggregator.GenericHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.Iterator;
import java.util.List;

public class GoogleAggregatorTests {

    private WebDriver driver;
    String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    String url="https://shopping.google.com/?nord=1";
    int implicitWait=30;
    int pageLoadOutTime=30;
    String inputForSearch="Mobile";
    List<WebElement> productList;
    
    @BeforeClass
    public void setup() {
    	this.driver=AllFunctions.initDriver(userAgent);
    }

    @Test(priority = 1)
    public void navigatingToTheUrl() {
        AllFunctions.navigateToURL(driver, url);
    }

    @Test(priority = 2)
    public void maximizingTheWindow() {
        AllFunctions.maximizeWindow(driver);
    }

    @Test(priority = 3)
    public void enableImplicitWaits() {
        AllFunctions.setImplicitWait(driver, implicitWait);
    }

    @Test(priority = 4)
    public void enablePageLoadTimeOut() {
        AllFunctions.setPageLoadOutTimeOut(driver, pageLoadOutTime);
    }

    @Test(priority = 5)
    public void clickingOnSearchBar() {
        AllFunctions.clickOnSearchBarLandingPage(driver);
    }

    @Test(priority = 6)
    public void givingInputIntoSearchBar() {
        AllFunctions.inputWithInTheSearchBar(driver, inputForSearch);
    }
    
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
    	this.productList=AllFunctions.getAllProducts(driver);
    }
    
    @Test(priority = 14)
    public void applyingPriceFilter() {
        try {
			AllFunctions.applyingPriceFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 15)
    public void applying5gFilter() {
        try {
			AllFunctions.applying5gFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 16)
    public void applyingBrandFilter() {
        try {
			AllFunctions.applyingBrandFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 17)
    public void applyingCameraFilter() {
        try {
			AllFunctions.applyingCameraFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 18)
    public void applyingCellularNetworkFilter() {
        try {
			AllFunctions.applyingCellularNetworkFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 19)
    public void applyingColorFilter() {
        try {
			AllFunctions.applyingColorFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 20)
    public void applyingDeliveryFilter() {
        try {
			AllFunctions.applyingDeliveryFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 21)
    public void applyingDualSimFilter() {
        try {
			AllFunctions.applyingDualSimFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 22)
    public void applyingHeadphoneFilter() {
        try {
			AllFunctions.applyingHeadphoneFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 23)
    public void applyingLensFilter() {
        try {
			AllFunctions.applyingLensFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 24)
    public void applyingOsFilter() {
        try {
			AllFunctions.applyingOsFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 25)
    public void applyingRamFilter() {
        try {
			AllFunctions.applyingRamFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 26)
    public void applyingRatingFilter() {
        try {
			AllFunctions.applyingRatingFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 27)
    public void applyingScreenResolutionFilter() {
        try {
			AllFunctions.applyingScreenResolutionFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 28)
    public void applyingSecurityFeaturesFilter() {
        try {
			AllFunctions.applyingSecurityFeaturesFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 29)
    public void applyingStorageFilter() {
        try {
			AllFunctions.applyingStorageFilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test(priority = 30)
    public void applyingWeightFilter() {
        try {
			AllFunctions.applyingWeightfilter(driver, productList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
}
