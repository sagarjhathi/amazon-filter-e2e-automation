

	
	package googleaggregatorPOM;

	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;
	import googleaggregator.GenericHelper;

	public class TestForParalleExecution {

	    WebDriver driver;
	    GlobalUtility globalUtility;
	    GoogleShoppingMainPage googleShoppingMainPage;
	    GoogleShoppingLandingPage googleShoppingLandingPage;
	    GenericHelper genericHelper;
	    DriverManager driverManager;

//	    @BeforeMethod
//	    public void setup() {
//	    	this.driver = DriverManager.setDriver(globalUtility.initDriver(genericHelper.userAgent));
//	        this.genericHelper = new GenericHelper();
//	        this.driverManager = new DriverManager();
//	        this.globalUtility = new GlobalUtility(driver);
//	        this.googleShoppingLandingPage = new GoogleShoppingLandingPage(driver);
//	        this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
//
//	        globalUtility.navigateToURL(driver, genericHelper.url);
//	        globalUtility.maximizeWindow(driver);
//	        globalUtility.setImplicitWait(driver, 10);
//	        globalUtility.setPageLoadOutTimeOut(driver, 20);
//
//	        googleShoppingLandingPage.clickOnSearchBarLandingPage();
//	        googleShoppingLandingPage.inputWithInTheSearchBar();
//	        googleShoppingLandingPage.selectFromRecommendations();
//
//	        googleShoppingMainPage.scrollToSellerLandingPage(driver);
//	        googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
//	        googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
//	    }
	    
	    @BeforeMethod
	    public void setup() throws InterruptedException {
	        this.genericHelper = new GenericHelper(); // 1. Init first
	        this.driver = DriverManager.setDriver(new GlobalUtility(driver).initDriver(genericHelper.userAgent)); // 2. Init driver
	        this.globalUtility = new GlobalUtility(driver); // 3. Init utility with driver
	        this.driverManager = new DriverManager(); // optional, but ok

	        this.googleShoppingLandingPage = new GoogleShoppingLandingPage(driver);
	        this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);

	        globalUtility.navigateToURL(driver, genericHelper.url);
	        globalUtility.maximizeWindow(driver);
	        globalUtility.setImplicitWait(driver, 10);
	        globalUtility.setPageLoadOutTimeOut(driver, 20);

	        googleShoppingLandingPage.clickOnSearchBarLandingPage();
	        googleShoppingLandingPage.inputWithInTheSearchBar();
	        Thread.sleep(2000); // delay you mentioned helped
	        googleShoppingLandingPage.selectFromRecommendations();

	        googleShoppingMainPage.scrollToSellerLandingPage(driver);
	        googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
	        googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
	    }


	    
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

	
