package googleaggregatorPOM;



	import org.openqa.selenium.WebDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;
	import googleaggregator.GenericHelper; // Assuming this path is correct
	import java.util.List; // Import List
	import org.openqa.selenium.WebElement; //Import WebElement


	public class ParallelPOMTests {

	    // No class-level WebDriver or PageObjects needed here for thread safety

	    GenericHelper genericHelper;

	    // Setup runs before EACH @Test method in parallel
	    @BeforeMethod
	    public void setup() throws InterruptedException {
	        this.genericHelper = new GenericHelper();

	        // 1. Initialize driver for the current thread
	        // Using the GlobalUtility's initDriver method - ensure it just creates and returns a driver
	        WebDriver currentDriver = new GlobalUtility(null).initDriver(genericHelper.userAgent); // Pass null or refactor initDriver
	        DriverManager.setDriver(currentDriver); // Set driver for this thread

	        // 2. Get the thread-safe driver
	        WebDriver driver = DriverManager.getDriver();

	        // 3. Initialize necessary Page Objects and Utilities FOR THIS THREAD
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingLandingPage googleShoppingLandingPage = new GoogleShoppingLandingPage(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);

	        // 4. Perform initial common steps from POMTesting's @BeforeClass/@Test(priority=1-8)
	        globalUtility.navigateToURL(driver, genericHelper.url); // Priority 1
	        globalUtility.maximizeWindow(driver); // Priority 2
	        globalUtility.setImplicitWait(driver, 10); // Priority 3 (part 1)
	        globalUtility.setPageLoadOutTimeOut(driver, 20); // Priority 3 (part 2)

	        googleShoppingLandingPage.clickOnSearchBarLandingPage(); // Priority 4 (part 1)
	        googleShoppingLandingPage.inputWithInTheSearchBar(); // Priority 4 (part 2)
	        Thread.sleep(3000); // Consider replacing with explicit wait if possible
	        googleShoppingLandingPage.selectFromRecommendations(); // Priority 5

	        googleShoppingMainPage.scrollToSellerLandingPage(driver); // Priority 6
	        googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver); // Priority 7
	        googleShoppingMainPage.selectingAmazonFromSellerMenu(driver); // Priority 8
	        System.out.println("Setup complete for thread: " + Thread.currentThread().getId());
	    }

	    // --- Individual Filter Tests (from POMTesting priorities 9+) ---
	    // Each @Test will run in parallel, starting after its own @BeforeMethod setup

	    @Test
	    public void testLensTypeFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver); // Re-init or pass from BeforeMethod if needed
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	        System.out.println("Running testLensTypeFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver); // Get products after setup
	        googleShoppingMainPage.applyingLensTypeFilterAllOptions(driver, products);
	    }

	    @Test
	    public void testWeightFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	         System.out.println("Running testWeightFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	        googleShoppingMainPage.applyingWeightFilterAllOptions(driver, products);
	    }

	    @Test
	    public void testScreenResolutionFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	        System.out.println("Running testScreenResolutionFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	        googleShoppingMainPage.applyingScreenResolutionFilterAllOptions(driver, products);
	    }

	    @Test
	    public void testStorageCapacityFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	        System.out.println("Running testStorageCapacityFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	        googleShoppingMainPage.applyingStorageCapacityFilterAllOptions(driver, products);
	    }

	     @Test
	    public void testPriceFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	         System.out.println("Running testPriceFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	        googleShoppingMainPage.applyingPriceFilterAllOptions(driver, products);
	    }

	    @Test
	    public void testBrandFilter() throws InterruptedException {
	        WebDriver driver = DriverManager.getDriver();
	        GlobalUtility globalUtility = new GlobalUtility(driver);
	        GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	        System.out.println("Running testBrandFilter on thread: " + Thread.currentThread().getId());
	        List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	        googleShoppingMainPage.applyingBrandFilterAllOptions(driver, products);
	    }

	    // ... Add similar @Test methods for ALL other filters from POMTesting ...
	    // (Broadband Generation, Operating System, Colour, Screen Size, SIM Slots,
	    // Cellular Network, Security Features, Front Camera Resolution, Connector Type,
	    // RAM, Delivery)

	    // Example for Delivery:
	     @Test
	     public void testDeliveryFilter() throws InterruptedException {
	         WebDriver driver = DriverManager.getDriver();
	         GlobalUtility globalUtility = new GlobalUtility(driver);
	         GoogleShoppingMainPage googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
	         System.out.println("Running testDeliveryFilter on thread: " + Thread.currentThread().getId());
	         List<WebElement> products = googleShoppingMainPage.gettingAllProducts(driver);
	         googleShoppingMainPage.applyingDeliveryFilterAllOptions(driver, products);
	     }


	    // Teardown runs after EACH @Test method
	    @AfterMethod
	    public void tearDown() {
	         System.out.println("Tearing down driver for thread: " + Thread.currentThread().getId());
	        DriverManager.quitDriver(); // Quit the driver for the current thread
	    }
	}
	
	
