package googleaggregatorPOM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
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
	
	
	@BeforeClass
    public void setup() {
		 this.globalUtility = new GlobalUtility(driver); 
		this.genericHelper=new GenericHelper();
		this.driverManager=new DriverManager();
		this.driver=driverManager.setDriver(globalUtility.initDriver(genericHelper.userAgent));
		this.googleShoppingLandingPage=new GoogleShoppingLandingPage(driver);
		 this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
    }
	
	
	@Test(priority = 1)
	public void navigatingToUrl() {
		globalUtility.navigateToURL(driver, genericHelper.url);
		System.out.println(1);
	}
		
	
	
	@Test(priority = 2)
	public void maximizingTheWindow() {
		globalUtility.maximizeWindow(driver);
		System.out.println(3);
	}
	
	@Test(priority = 3)
	public void applyingAllWaits() {
		globalUtility.setImplicitWait(driver, 10);
		globalUtility.setPageLoadOutTimeOut(driver, 20);
		System.out.println(2);
	}
	
	@Test(priority = 4)
	public void inputWithIntheSearchBar() {
		googleShoppingLandingPage.clickOnSearchBarLandingPage();
	    googleShoppingLandingPage.inputWithInTheSearchBar();
	    System.out.println(4);
	}
	
	@Test(priority = 5)
	public void selectingFromRecommendations() throws InterruptedException {
		Thread.sleep(3000);
		googleShoppingLandingPage.selectFromRecommendations();
		System.out.println(5);
	}
	
	

	
	@Test(priority=6)
	public void scrollToSellerMenu() {
		googleShoppingMainPage.scrollToSellerLandingPage(driver);
	}
	
	
	@Test(priority=7)
	public void  clickingOnMorInSellerMenuLandingPage() {
		googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
	}
	
	
	@Test(priority=8)
	public void clickingOnAmazonFromSellerMenu() {
		googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
	//	googleShoppingMainPage.locateAllFiltersAndApplyEachFilterWithAlloptions(driver);
	}
	
	
	
	@Test(priority = 9)
	public void applyingAllFiltersOptionsForLensType() throws InterruptedException {
		googleShoppingMainPage.applyingLensTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	
	
	
	@Test(priority = 10)
	public void applyingAllFiltersOptionsForWeight() throws InterruptedException {
		googleShoppingMainPage.applyingWeightFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	
	@Test(priority = 11)
	public void applyingAllFiltersOptionsForScreenResolution() throws InterruptedException {
		googleShoppingMainPage.applyingScreenResolutionFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 12)
	public void applyingAllFiltersOptionsForStorageCapacity() throws InterruptedException {
		googleShoppingMainPage.applyingStorageCapacityFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
	}



	@Test(priority = 13)
	public void applyingAllFilterOptionsForPrice() throws InterruptedException {
		googleShoppingMainPage.applyingPriceFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}  
//
	@Test(priority = 14)
	public void applyingAllFilterOptionsForBrand() throws InterruptedException {
		googleShoppingMainPage.applyingBrandFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 15)
	public void applyingAllFiltersOptionsForBroadBandGeneration() throws InterruptedException {
		googleShoppingMainPage.applyingBroadbandGenerationFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 16)
	public void applyingAllFiltersOptionsForOperatingSystem() throws InterruptedException {
		googleShoppingMainPage.applyingOperatingSystemFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 17)
	public void applyingAllFiltersOptionsForColour() throws InterruptedException {
		googleShoppingMainPage.applyingColourFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 18)
	public void applyingAllFiltersOptionsForScreenSize() throws InterruptedException {
		googleShoppingMainPage.applyingScreenSizeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 19)
	public void applyingAllFiltersOptionsForSIMSlots() throws InterruptedException {
		googleShoppingMainPage.applyingSIMSlotsFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 20)
	public void applyingAllFiltersOptionsForCellularNetwork() throws InterruptedException {
		googleShoppingMainPage.applyingCellularNetworkFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
//	
	@Test(priority = 21)
	public void applyingAllFiltersOptionsForSecurityFeatures() throws InterruptedException {
		googleShoppingMainPage.applyingSecurityFeaturesFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 22)
	public void applyingAllFiltersOptionsForFrontCameraResolution() throws InterruptedException {
		googleShoppingMainPage.applyingFrontCameraResolutionFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 23)
	public void applyingAllFiltersOptionsForConnectorType() throws InterruptedException {
		googleShoppingMainPage.applyingConnectorTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 24)
	public void applyingAllFiltersOptionsForRAM() throws InterruptedException {
		googleShoppingMainPage.applyingRAMFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 25)
	public void applyingAllFiltersOptionsForDelivery() throws InterruptedException {
		googleShoppingMainPage.applyingDeliveryFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
	}


	

	
	
	
	
	
	
	
	
}
