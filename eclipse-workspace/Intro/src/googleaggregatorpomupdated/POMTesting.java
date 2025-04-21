package googleaggregatorpomupdated;

import java.time.Duration;  

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import googleaggregator.AllFunctions;
import googleaggregator.GenericHelper;

public class POMTesting {

	WebDriver driver;
	GlobalUtility globalUtility;
	ViewMoreDetailsPage viewMoreDetailsPage;
	GoogleShoppingProductPopup googleShoppingProductPopup;
	AmazonProductMainPage amazonProductMainPage;
	GenericHelper genericHelper;
	GoogleShoppingMainPage googleShoppingMainPage;
	GoogleShoppingLandingPage  	googleShoppingLandingPage;
	DriverManager driverManager;
	
	
	@BeforeMethod
    public void setup() throws InterruptedException {
		this.driver = DriverManager.initDriver(); // Ensure this is the first step
	    this.globalUtility = new GlobalUtility(driver); // Then initialize the GlobalUtility with the driver
	    this.genericHelper = new GenericHelper();
	    this.driverManager = new DriverManager();
	    this.googleShoppingLandingPage = new GoogleShoppingLandingPage(driver);
	    this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
		 
		 globalUtility.navigateToURL(driver, genericHelper.url);
		 globalUtility.maximizeWindow(driver);
		 globalUtility.setImplicitWait(driver, 10);
			globalUtility.setPageLoadOutTimeOut(driver, 20);
			
			googleShoppingLandingPage.clickOnSearchBarLandingPage();
		    googleShoppingLandingPage.inputWithInTheSearchBar();
		    
		    Thread.sleep(3000);
			googleShoppingLandingPage.selectFromRecommendations();
			
			googleShoppingMainPage.scrollToSellerLandingPage(driver);
			
			googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
			
			googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
    }
	
	
	@AfterMethod
	public static void quitDriver() {
        DriverManager.quitDriver();
    }
	
//	@Test(priority = 1)
//	public void navigatingToUrl() {
//		globalUtility.navigateToURL(driver, genericHelper.url);
//		System.out.println(1);
//	}
//		
//	
//	
//	@Test(priority = 2)
//	public void maximizingTheWindow() {
//		globalUtility.maximizeWindow(driver);
//		System.out.println(3);
//	}
//	
//	@Test(priority = 3)
//	public void applyingAllWaits() {
//		globalUtility.setImplicitWait(driver, 10);
//		globalUtility.setPageLoadOutTimeOut(driver, 20);
//		System.out.println(2);
//	}
//	
//	@Test(priority = 4)
//	public void inputWithIntheSearchBar() {
//		googleShoppingLandingPage.clickOnSearchBarLandingPage();
//	    googleShoppingLandingPage.inputWithInTheSearchBar();
//	    System.out.println(4);
//	}
//	
//	@Test(priority = 5)
//	public void selectingFromRecommendations() throws InterruptedException {
//		Thread.sleep(3000);
//		googleShoppingLandingPage.selectFromRecommendations();
//		System.out.println(5);
//	}
//	
//	
//
//	
//	@Test(priority=6)
//	public void scrollToSellerMenu() {
//		googleShoppingMainPage.scrollToSellerLandingPage(driver);
//	}
//	
//	
//	@Test(priority=7)
//	public void  clickingOnMorInSellerMenuLandingPage() {
//		googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
//	}
//	
//	
//	@Test(priority=8)
//	public void clickingOnAmazonFromSellerMenu() {
//		googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
//	//	googleShoppingMainPage.locateAllFiltersAndApplyEachFilterWithAlloptions(driver);
//	}
	
	
	
	
	
	@Test
	public void applyingAllFiltersOptionsForLensType() throws InterruptedException {
	    googleShoppingMainPage.applyingLensTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForWeight() throws InterruptedException {
	    googleShoppingMainPage.applyingWeightFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForScreenResolution() throws InterruptedException {
	    googleShoppingMainPage.applyingScreenResolutionFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForStorageCapacity() throws InterruptedException {
	    googleShoppingMainPage.applyingStorageCapacityFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFilterOptionsForPrice() throws InterruptedException {
	    googleShoppingMainPage.applyingPriceFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFilterOptionsForBrand() throws InterruptedException {
	    googleShoppingMainPage.applyingBrandFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForBroadBandGeneration() throws InterruptedException {
	    googleShoppingMainPage.applyingBroadbandGenerationFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForOperatingSystem() throws InterruptedException {
	    googleShoppingMainPage.applyingOperatingSystemFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForColour() throws InterruptedException {
	    googleShoppingMainPage.applyingColourFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForScreenSize() throws InterruptedException {
	    googleShoppingMainPage.applyingScreenSizeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForSIMSlots() throws InterruptedException {
	    googleShoppingMainPage.applyingSIMSlotsFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForCellularNetwork() throws InterruptedException {
	    googleShoppingMainPage.applyingCellularNetworkFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForSecurityFeatures() throws InterruptedException {
	    googleShoppingMainPage.applyingSecurityFeaturesFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForFrontCameraResolution() throws InterruptedException {
	    googleShoppingMainPage.applyingFrontCameraResolutionFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForConnectorType() throws InterruptedException {
	    googleShoppingMainPage.applyingConnectorTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForRAM() throws InterruptedException {
	    googleShoppingMainPage.applyingRAMFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test
	public void applyingAllFiltersOptionsForDelivery() throws InterruptedException {
	    googleShoppingMainPage.applyingDeliveryFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}


	

	
	
	
	
	
	
	
	
}
