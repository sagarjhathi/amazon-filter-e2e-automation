//package googleaggregatorpomupdated;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import googleaggregator.GenericHelper;
//
//public class POMTesting {
//
//	WebDriver driver;
//	GlobalUtility globalUtility;
//	ViewMoreDetailsPage viewMoreDetailsPage;
//	GoogleShoppingProductPopup googleShoppingProductPopup;
//	AmazonProductMainPage amazonProductMainPage;
//	GenericHelper genericHelper;
//	GoogleShoppingMainPage googleShoppingMainPage;
//	GoogleShoppingLandingPage  	googleShoppingLandingPage;
//	
//	
//	@BeforeClass
//    public void setup() {
//		 this.globalUtility = new GlobalUtility(driver); 
//		this.genericHelper=new GenericHelper();
//		this.driver=globalUtility.initDriver(genericHelper.userAgent);
//		this.googleShoppingLandingPage=new GoogleShoppingLandingPage(driver);
//		 this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
//    }
//	
//	
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
//	
//	
//	
//	@Test(priority = 9)
//	public void applyingAllFiltersOptionsForLensType() throws InterruptedException {
//		googleShoppingMainPage.applyingLensTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	
//	
//	
//	@Test(priority = 10)
//	public void applyingAllFiltersOptionsForWeight() throws InterruptedException {
//		googleShoppingMainPage.applyingWeightFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	
//	@Test(priority = 11)
//	public void applyingAllFiltersOptionsForScreenResolution() throws InterruptedException {
//		googleShoppingMainPage.applyingScreenResolutionFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	@Test(priority = 12)
//	public void applyingAllFiltersOptionsForStorageCapacity() throws InterruptedException {
//		googleShoppingMainPage.applyingStorageCapacityFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
//	}
//
//
//
//	@Test(priority = 13)
//	public void applyingAllFilterOptionsForPrice() throws InterruptedException {
//		googleShoppingMainPage.applyingPriceFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}  
////
//	@Test(priority = 14)
//	public void applyingAllFilterOptionsForBrand() throws InterruptedException {
//		googleShoppingMainPage.applyingBrandFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 15)
//	public void applyingAllFiltersOptionsForBroadBandGeneration() throws InterruptedException {
//		googleShoppingMainPage.applyingBroadbandGenerationFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 16)
//	public void applyingAllFiltersOptionsForOperatingSystem() throws InterruptedException {
//		googleShoppingMainPage.applyingOperatingSystemFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 17)
//	public void applyingAllFiltersOptionsForColour() throws InterruptedException {
//		googleShoppingMainPage.applyingColourFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 18)
//	public void applyingAllFiltersOptionsForScreenSize() throws InterruptedException {
//		googleShoppingMainPage.applyingScreenSizeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 19)
//	public void applyingAllFiltersOptionsForSIMSlots() throws InterruptedException {
//		googleShoppingMainPage.applyingSIMSlotsFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 20)
//	public void applyingAllFiltersOptionsForCellularNetwork() throws InterruptedException {
//		googleShoppingMainPage.applyingCellularNetworkFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
////	
//	@Test(priority = 21)
//	public void applyingAllFiltersOptionsForSecurityFeatures() throws InterruptedException {
//		googleShoppingMainPage.applyingSecurityFeaturesFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	@Test(priority = 22)
//	public void applyingAllFiltersOptionsForFrontCameraResolution() throws InterruptedException {
//		googleShoppingMainPage.applyingFrontCameraResolutionFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	@Test(priority = 23)
//	public void applyingAllFiltersOptionsForConnectorType() throws InterruptedException {
//		googleShoppingMainPage.applyingConnectorTypeFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	@Test(priority = 24)
//	public void applyingAllFiltersOptionsForRAM() throws InterruptedException {
//		googleShoppingMainPage.applyingRAMFilterAllOptions(driver, googleShoppingMainPage.gettingAllProducts(driver));
//	}
//	
//	@Test(priority = 25)
//	public void applyingAllFiltersOptionsForDelivery() throws InterruptedException {
//		googleShoppingMainPage.applyingDeliveryFilterAllOptions(driver,googleShoppingMainPage.gettingAllProducts(driver));
//	}
//
//
//	
//
//	
//	
//	
//	
//	
//	
//	
//	
//}




package googleaggregatorpomupdated;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import googleaggregator.GenericHelper;

public class POMTesting{

    WebDriver driver;
    GlobalUtility globalUtility;
    ViewMoreDetailsPage viewMoreDetailsPage;
    GoogleShoppingProductPopup googleShoppingProductPopup;
    AmazonProductMainPage amazonProductMainPage;
    GenericHelper genericHelper;
    GoogleShoppingMainPage googleShoppingMainPage;
    GoogleShoppingLandingPage googleShoppingLandingPage;

    @BeforeMethod
    public void  setUp() {
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + genericHelper.userAgent);
       // options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection
        options.addArguments("--no-sandbox"); // Stability in CI environments
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--lang=en");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
     
    }


    
    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
    
    
    @Test
    public void applyingAllFiltersOptionsForLensType() throws InterruptedException {
    	
    	GoogleShoppingMainPage googleShoppingMainPage=new GoogleShoppingMainPage(driver);
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = driver.findElement(googleShoppingMainPage.lensFilterContainer);
        
        
        // Fetch children properly, relative to the parent element
        List<WebElement> totalOptions = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
        
        for(int i=0;i<totalOptions.size();i++) {
        	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
        }
        
        System.out.println("Found filter options: " + totalOptions.size());
        
        for (int i = 0; i < totalOptions.size(); i++) {
            // Refetch the parent and children on each loop to avoid stale element issues
            parent = driver.findElement(googleShoppingMainPage.lensFilterContainer);
            List<WebElement> filters = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
            
            System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
            if (i >= filters.size()) break; // Safety net

            WebElement currentFilter = filters.get(i);
            String text = currentFilter.getText().trim();

            if (text.contains("Min") || text.contains("Max")) {
                System.out.println("Skipping Min/Max filter: " + text);
                continue;
            }
            
            System.out.println(text+" Filter name being applied ");

            googleShoppingMainPage.applyFilterAndTraverse( currentFilter, googleShoppingMainPage.gettingAllProducts());
       	    
            // Optionally wait for UI update
            wait.until(ExpectedConditions.stalenessOf(currentFilter));
             
        } 
    }

    @Test
    public void applyingAllFiltersOptionsForWeight() throws InterruptedException {
    	GoogleShoppingMainPage googleShoppingMainPage=new GoogleShoppingMainPage(driver);
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement parent = driver.findElement(googleShoppingMainPage.weightFilterContainer);
         
         
         // Fetch children properly, relative to the parent element
         List<WebElement> totalOptions = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
         
         for(int i=0;i<totalOptions.size();i++) {
         	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
         }
         
         System.out.println("Found filter options: " + totalOptions.size());
         
         for (int i = 0; i < totalOptions.size(); i++) {
             // Refetch the parent and children on each loop to avoid stale element issues
             parent = driver.findElement(googleShoppingMainPage.weightFilterContainer);
             List<WebElement> filters = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
             
             System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
             if (i >= filters.size()) break; // Safety net

             WebElement currentFilter = filters.get(i);
             String text = currentFilter.getText().trim();

             if (text.contains("Min") || text.contains("Max")) {
                 System.out.println("Skipping Min/Max filter: " + text);
                 continue;
             }
             
             System.out.println(text+" Filter name being applied ");

             googleShoppingMainPage.applyFilterAndTraverse(currentFilter, googleShoppingMainPage.gettingAllProducts());
        	    driver.navigate().refresh();
        	    
             // Optionally wait for UI update
             wait.until(ExpectedConditions.stalenessOf(currentFilter));
              
         } 
    }

    @Test
    public void applyingAllFiltersOptionsForScreenResolution() throws InterruptedException {
    	
    	GoogleShoppingMainPage googleShoppingMainPage=new GoogleShoppingMainPage(driver);

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = driver.findElement(googleShoppingMainPage.screenResolutionFilterContainer);
        
        
        // Fetch children properly, relative to the parent element
        List<WebElement> totalOptions = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
        
        for(int i=0;i<totalOptions.size();i++) {
        	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
        }
        
        System.out.println("Found filter options: " + totalOptions.size());
        
        for (int i = 0; i < totalOptions.size(); i++) {
            // Refetch the parent and children on each loop to avoid stale element issues
            parent = driver.findElement(googleShoppingMainPage.screenResolutionFilterContainer);
            List<WebElement> filters = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
            
            System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
            if (i >= filters.size()) break; // Safety net

            WebElement currentFilter = filters.get(i);
            String text = currentFilter.getText().trim();

            if (text.contains("Min") || text.contains("Max")) {
                System.out.println("Skipping Min/Max filter: " + text);
                continue;
            }
            
            System.out.println(text+" Filter name being applied ");

            googleShoppingMainPage.applyFilterAndTraverse(currentFilter, googleShoppingMainPage.gettingAllProducts());
       	    driver.navigate().refresh();
       	    
            // Optionally wait for UI update
            wait.until(ExpectedConditions.stalenessOf(currentFilter));
             
        } 
    }

    @Test
    public void applyingAllFiltersOptionsForStorageCapacity() throws InterruptedException {
        googleShoppingMainPage.applyingStorageCapacityFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFilterOptionsForPrice() throws InterruptedException {
    	
    	GoogleShoppingMainPage googleShoppingMainPage=new GoogleShoppingMainPage(driver);
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement parent = driver.findElement(googleShoppingMainPage.priceFilterContainer);
          
          
          // Fetch children properly, relative to the parent element
          List<WebElement> totalOptions = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
          
          for(int i=0;i<totalOptions.size();i++) {
          	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
          }
          
          System.out.println("Found filter options: " + totalOptions.size());
          
          for (int i = 0; i < totalOptions.size(); i++) {
              // Refetch the parent and children on each loop to avoid stale element issues
              parent = driver.findElement(googleShoppingMainPage.priceFilterContainer);
              List<WebElement> filters = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
              
              System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
              if (i >= filters.size()) break; // Safety net

              WebElement currentFilter = filters.get(i);
              String text = currentFilter.getText().trim();

              if (text.contains("Min") || text.contains("Max")) {
                  System.out.println("Skipping Min/Max filter: " + text);
                  continue;
              }

              
              System.out.println(text+" Filter name being applied ");

              
              googleShoppingMainPage.applyFilterAndTraverse(currentFilter, googleShoppingMainPage.gettingAllProducts());
         	    
              // Optionally wait for UI update
              wait.until(ExpectedConditions.stalenessOf(currentFilter));
              
              
          }
    }

    @Test
    public void applyingAllFilterOptionsForBrand() throws InterruptedException {
    	GoogleShoppingMainPage googleShoppingMainPage=new GoogleShoppingMainPage(driver);

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = driver.findElement(googleShoppingMainPage.brandFilterContainer);
        
        
        // Fetch children properly, relative to the parent element
        List<WebElement> totalOptions = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
        
        for(int i=0;i<totalOptions.size();i++) {
        	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
        }
        
        System.out.println("Found filter options: " + totalOptions.size());
        
        for (int i = 0; i < totalOptions.size(); i++) {
            // Refetch the parent and children on each loop to avoid stale element issues
            parent = driver.findElement(googleShoppingMainPage.brandFilterContainer);
            List<WebElement> filters = parent.findElements(googleShoppingMainPage.childElementsFromEachFilterParent);
            
            System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
            if (i >= filters.size()) break; // Safety net

            WebElement currentFilter = filters.get(i);
            String text = currentFilter.getText().trim();

            if (text.contains("Min") || text.contains("Max")) {
                System.out.println("Skipping Min/Max filter: " + text);
                continue;
            }
            
            System.out.println(text+" Filter name being applied ");

            googleShoppingMainPage.applyFilterAndTraverse(currentFilter,googleShoppingMainPage.gettingAllProducts());
       	    
            // Optionally wait for UI update
            wait.until(ExpectedConditions.stalenessOf(currentFilter));
             
        } 
    }

 



	@Test
    public void applyingAllFiltersOptionsForBroadBandGeneration() throws InterruptedException {
        googleShoppingMainPage.applyingBroadbandGenerationFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForOperatingSystem() throws InterruptedException {
        googleShoppingMainPage.applyingOperatingSystemFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForColour() throws InterruptedException {
        googleShoppingMainPage.applyingColourFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForScreenSize() throws InterruptedException {
        googleShoppingMainPage.applyingScreenSizeFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForSIMSlots() throws InterruptedException {
        googleShoppingMainPage.applyingSIMSlotsFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForCellularNetwork() throws InterruptedException {
        googleShoppingMainPage.applyingCellularNetworkFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForSecurityFeatures() throws InterruptedException {
        googleShoppingMainPage.applyingSecurityFeaturesFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForFrontCameraResolution() throws InterruptedException {
        googleShoppingMainPage.applyingFrontCameraResolutionFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForConnectorType() throws InterruptedException {
        googleShoppingMainPage.applyingConnectorTypeFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForRAM() throws InterruptedException {
        googleShoppingMainPage.applyingRAMFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

    @Test
    public void applyingAllFiltersOptionsForDelivery() throws InterruptedException {
        googleShoppingMainPage.applyingDeliveryFilterAllOptions(googleShoppingMainPage.gettingAllProducts());
    }

} 

