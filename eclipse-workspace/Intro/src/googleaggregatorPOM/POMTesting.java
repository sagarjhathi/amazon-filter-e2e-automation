package googleaggregatorPOM;

import org.openqa.selenium.WebDriver;
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
	FactoryPattern factory;
	
	
	@BeforeClass
    public void setup() {
		 this.globalUtility = new GlobalUtility(); // Initialize globalUtility first
		this.genericHelper=new GenericHelper();
		this.driver=globalUtility.initDriver(genericHelper.userAgent);
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
	public void selectingFromRecommendations() {
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
	}
	
	
	@Test(priority = 9)
	public void applyingScreenResolutionFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingScreenResolutionFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	@Test(priority = 10)
	public void applyingWeightFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingWeightfilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	

	@Test(priority = 11)
	public void applyingSecurityFeaturesFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingSecurityFeaturesFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 12)
	public void applyingStorageFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingStorageFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	@Test(priority = 13)
	public void applyingPriceFilterInGoogleMainPage() throws InterruptedException {
	    googleShoppingMainPage.applyingPriceFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 14)
	public void applying5gFilterInGoogleMainPage() throws InterruptedException {
	    googleShoppingMainPage.applying5GFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 15)
	public void applyingBrandFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingBrandFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 16)
	public void applyingCameraFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingCameraFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 17)
	public void applyingCellularNetworkFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingCellularNetworkFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 18)
	public void applyingColorFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingColorFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 19)
	public void applyingDeliveryFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingDeliveryFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 20)
	public void applyingDualSimFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingDualSimFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 21)
	public void applyingHeadphoneFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingHeadphoneFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 22)
	public void applyingLensFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingLensFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 23)
	public void applyingOsFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingOsFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 24)
	public void applyingRamFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingRamFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}

	@Test(priority = 25)
	public void applyingRatingFilterInGoogleMainPage() throws InterruptedException {
	    AllFunctions.applyingRatingFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
