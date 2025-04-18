package googleaggregatorpomupdated;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import googleaggregator.GenericHelper;

public class GoogleShoppingMainPage {


	
	private WebDriver driver;


    public GoogleShoppingMainPage(WebDriver driver) {
    	this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    

	
	
	
	// Page Factory - Web Elements
    @FindBy(xpath = "//div[@class='lg3aE' and text()='Seller']")
    private WebElement seller;

    @FindBy(xpath = "//div[@aria-label='More Seller']")
    private WebElement moreSellerButton;

    @FindBy(xpath = "(//span[@class='lg3aE']/span[contains(text(),'₹')])[2]")
    private WebElement priceFilter;

    @FindBy(xpath = "(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'GB')])[5]")
    private WebElement ramFilter;

    @FindBy(xpath = "(//div[text()='More'])[1]")
    private WebElement moreFromProductRating;

    @FindBy(xpath = "(//div[@class='vq3ore' and @aria-label='4 out of 5 stars'])[2]")
    private WebElement fourAndUpRating;

    @FindBy(xpath = "(//*[contains(text(), 'grams')])[1]")
    private WebElement weightFilter;

    @FindBy(xpath = "//span[@title='1–3 day delivery']")
    private WebElement deliveryFilter;

    @FindBy(xpath = "//span[@title='Realme']")
    private WebElement mobileBrand;

    @FindBy(xpath = "//span[@title='5G']")
    private WebElement selecting5G;

    @FindBy(xpath = "//span[@title='Dual SIM']")
    private WebElement dualSim;

    @FindBy(xpath = "//span[text()='Black']")
    private WebElement colorFilter;

    @FindBy(xpath = "//span[text()='64 GB']")
    private WebElement storageCapacityFilter;

    @FindBy(xpath = "//span[text()='Android']")
    private WebElement osFilter;

    @FindBy(xpath = "//span[@title='GSM Network']")
    private WebElement cellularNetworkFilter;

    @FindBy(xpath = "//span[@title='Mobile Phones With Fingerprint Scanners']")
    private WebElement securityFeaturesFilter;

    @FindBy(xpath = "//span[@title='2408 x 1080']")
    private WebElement screenResolutionFilter;

    @FindBy(xpath = "(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'MP')])[1]")
    private WebElement cameraFilter;

    @FindBy(xpath = "//span[@title='Headphone Jack']")
    private WebElement headphoneConnectorFilter;

    @FindBy(xpath = "//span[@title='Ultra Wide Angle']")
    private WebElement lensTypeFilter;
    
    @FindBy(xpath="//a[@aria-label='Close']")
    private WebElement closeButtonGooglePopup;
    
    @FindBy(css="span.lg3aE[title='Amazon.in']")
    private WebElement amazonFromSellerMenu;
    
    @FindBy(xpath="//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]")
    private WebElement getAllProducts;
    
    @FindBy(xpath="//ul[@style='margin-left:1.3em;margin-bottom:2em']")
    private WebElement checkNoElementState;
    
    
    @FindBy(xpath="(//a[@class='vjtvke' and text()='Clear'])[1]")
    private WebElement clearButtonFromFilter;
    
    @FindBy(xpath="//div[text()='Price']/following-sibling::div")
    private WebElement clearButtonFromPriceFilter;
    
    
    By screenSizeFilterContainer = By.xpath("//div[text()='Screen Size']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By weightFilterContainer = By.xpath("//div[text()='Weight']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By cellularNetworkFilterContainer = By.xpath("//div[text()='Cellular Network']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    
    By simSlotFilterContainer = By.xpath("//div[text()='SIM Slots']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By storageFilterContainer = By.xpath("//div[text()='Storage Capacity']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By colorFilterContainer = By.xpath("//div[text()='Colour']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");
    
    By oSFilterContainer = By.xpath("//div[text()='Operating System']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By broadBandFilterContainer = By.xpath("//div[text()='Broadband Generation']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By brandFilterContainer = By.xpath("//div[text()='Brand']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By priceFilterContainer = By.xpath("//div[text()='Price']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");
    
    By securityFilterContainer = By.xpath("//div[text()='Security Features']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By connectorTypeFilterContainer = By.xpath("//div[text()='Connector Type']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");


    By frontCameraResolutionFilterContainer = By.xpath("//div[text()='Front Camera Resolution']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By screenResolutionFilterContainer = By.xpath("//div[text()='Screen Resolution']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By formFactorFilterContainer = By.xpath("//div[text()='Form Factor']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By RAMFilterContainer = By.xpath("//div[text()='RAM']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By lensFilterContainer = By.xpath("//div[text()='Lens Type']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By deliveryFilterContainer = By.xpath("//div[text()='Delivery']/parent::div/following-sibling::div//div[contains(@class,'sh-dr__short')]");

    By childElementsFromEachFilterParent =By.xpath(".//span[contains(@class,'DON5yf')]");
    
    
    
    public List<WebElement> gettingAllProducts(){
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement products = wait.until(ExpectedConditions.visibilityOf(getAllProducts));
         return products.findElements(By.xpath("./*"));
    }
    
    
    
    public void selectingAmazonFromSellerMenu() {
    	amazonFromSellerMenu.click();
    }
    
    public String getGoogleWindowHandle() {
    	String cWindow=driver.getWindowHandle();
    	return cWindow;
    }

  

    public void clickOnMoreInSellerMenuLandingPage() {
        moreSellerButton.click();
    }

            
    //Done
    public void applyingPriceFilterAllOptions(List<WebElement> list) throws InterruptedException {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = driver.findElement(priceFilterContainer);
        
        
        // Fetch children properly, relative to the parent element
        List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
        
        for(int i=0;i<totalOptions.size();i++) {
        	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
        }
        
        System.out.println("Found filter options: " + totalOptions.size());
        
        for (int i = 0; i < totalOptions.size(); i++) {
            // Refetch the parent and children on each loop to avoid stale element issues
            parent = driver.findElement(priceFilterContainer);
            List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
            
            System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
            if (i >= filters.size()) break; // Safety net

            WebElement currentFilter = filters.get(i);
            String text = currentFilter.getText().trim();

            if (text.contains("Min") || text.contains("Max")) {
                System.out.println("Skipping Min/Max filter: " + text);
                continue;
            }

            
            System.out.println(text+" Filter name being applied ");

            
       	    applyFilterAndTraverse(currentFilter, list);
       	    
            // Optionally wait for UI update
            wait.until(ExpectedConditions.stalenessOf(currentFilter));
            
            
        } 
    }
    
    
    
    
    
    
    
    
    
   //Done 
 public void applyingBrandFilterAllOptions(List<WebElement> list) throws InterruptedException {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement parent = driver.findElement(brandFilterContainer);
        
        
        // Fetch children properly, relative to the parent element
        List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
        
        for(int i=0;i<totalOptions.size();i++) {
        	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
        }
        
        System.out.println("Found filter options: " + totalOptions.size());
        
        for (int i = 0; i < totalOptions.size(); i++) {
            // Refetch the parent and children on each loop to avoid stale element issues
            parent = driver.findElement(brandFilterContainer);
            List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
            
            System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
            if (i >= filters.size()) break; // Safety net

            WebElement currentFilter = filters.get(i);
            String text = currentFilter.getText().trim();

            if (text.contains("Min") || text.contains("Max")) {
                System.out.println("Skipping Min/Max filter: " + text);
                continue;
            }
            
            System.out.println(text+" Filter name being applied ");

       	    applyFilterAndTraverse(currentFilter, list);
       	    
            // Optionally wait for UI update
            wait.until(ExpectedConditions.stalenessOf(currentFilter));
             
        } 
    }
    
    
    
    
    
    
    //Done
 public void applyingBroadbandGenerationFilterAllOptions(List<WebElement> list) throws InterruptedException {
 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(broadBandFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(broadBandFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
    
    //Done
 public void applyingOperatingSystemFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(oSFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(oSFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }


 
 
 //Done
 public void applyingColourFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(colorFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(colorFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 
 
 
 
//Done
 public void applyingStorageCapacityFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(storageFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(storageFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse( currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 
 
 
 //Done
 public void applyingScreenSizeFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(screenSizeFilterContainer);
     		
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(screenSizeFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
    
 
 
 
 
 //Done
 public void applyingWeightFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(weightFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(weightFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    driver.navigate().refresh();
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 //Done
 public void applyingSIMSlotsFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(simSlotFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(simSlotFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 
 //Done
 public void applyingCellularNetworkFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(cellularNetworkFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(cellularNetworkFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 
 //Done
 public void applyingSecurityFeaturesFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(securityFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(securityFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingScreenResolutionFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(screenResolutionFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(screenResolutionFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    driver.navigate().refresh();
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingFrontCameraResolutionFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(frontCameraResolutionFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(frontCameraResolutionFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse( currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingConnectorTypeFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(connectorTypeFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(connectorTypeFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse( currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingFormFactorFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(formFactorFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(formFactorFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }

 
 
 //Done
 public void applyingRAMFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(RAMFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(RAMFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse(currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingLensTypeFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement parent = driver.findElement(lensFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(lensFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse( currentFilter, list);
    	    
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Done
 public void applyingDeliveryFilterAllOptions(List<WebElement> list) throws InterruptedException {
	 	
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
     WebElement parent = driver.findElement(deliveryFilterContainer);
     
     
     // Fetch children properly, relative to the parent element
     List<WebElement> totalOptions = parent.findElements(childElementsFromEachFilterParent);
     
     for(int i=0;i<totalOptions.size();i++) {
     	System.out.println(totalOptions.get(i).getText()+"   "+ "Printing all the Options here from price filter");
     }
     
     System.out.println("Found filter options: " + totalOptions.size());
     
     for (int i = 0; i < totalOptions.size(); i++) {
    	 driver.navigate().refresh();
         // Refetch the parent and children on each loop to avoid stale element issues
         parent = driver.findElement(deliveryFilterContainer);
         List<WebElement> filters = parent.findElements(childElementsFromEachFilterParent);
         
         System.out.println("Re-checking the size of the filter options list within the loop"   +filters.size());
         if (i >= filters.size()) break; // Safety net

         WebElement currentFilter = filters.get(i);
         String text = currentFilter.getText().trim();

         if (text.contains("Min") || text.contains("Max")) {
             System.out.println("Skipping Min/Max filter: " + text);
             continue;
         }
         
         System.out.println(text+" Filter name being applied ");

    	    applyFilterAndTraverse( currentFilter, list);
    	    driver.navigate().refresh();
         // Optionally wait for UI update
         wait.until(ExpectedConditions.stalenessOf(currentFilter));
          
     } 
 }
 
 
 
 //Product rating and seller filters will have to be built different hence did not write the code for that
 
   
    public boolean checkNoResultsAndNavigateBack() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement noResults = wait.until(ExpectedConditions.visibilityOf(checkNoElementState));

            if (noResults.isDisplayed()) {
                System.out.println("No results found, navigating back...");
                driver.navigate().back();
                return true;
            }
        } catch (TimeoutException e) {
            System.out.println("Results found, proceeding with further actions.");
        }
        return false;
    }
       
    
    public void  clickClearButton() throws InterruptedException {
    	Thread.sleep(1000);
        clearButtonFromFilter.click();
        Thread.sleep(1000);
    }
    
    
	 public  void applyFilterAndTraverse( WebElement element, List<WebElement> list) throws InterruptedException {
		      globalUtility.scrollAndClick( element);
		      if(checkNoResultsAndNavigateBack()) {
		    	  return;
		      }
		      
		    int countCheck = 0;
  	        for (int i = 1; i < list.size(); i++) {
  	            switchWindowAndCompare(i);
  	            countCheck++;
  	            // Break after the first iteration so that we are not iterating all the products 
  	            if (countCheck == 1) {
  	                break;
  	            }
  	        }   
		    	
  	        
  	    JavascriptExecutor js = (JavascriptExecutor) driver;
  	    js.executeScript("window.scrollBy(0, -500);");
  	    
  	      
  	    Thread.sleep(3000);
  	        clickClearButton(); 
		 }
	    
	 
	 
	 
	 public void productLocatingAndClicking( int productIndex) {
		 
		 WebElement productLocating=driver.findElement(By.xpath("(//a[@data-what='1'])[" + productIndex + "]"));

		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		 
		 WebElement clickOnProduct=wait.until(ExpectedConditions.elementToBeClickable(productLocating));
		 clickOnProduct.click();
		 
	 }
	 
	 
	 
	 
	 
	    public  void switchWindowAndCompare(int productIndex) throws InterruptedException {
			 
			driver.navigate().refresh();
			Thread.sleep(1000);
			productLocatingAndClicking(productIndex);	  
		    googleShoppingProductPopup.scrollAndClickViewMoreDetails();
		    viewMoreDetailsPage.getGoogleProductName();
			viewMoreDetailsPage.getGoogleProductPrice();
			Thread.sleep(1000);
			String cWindow=getGoogleWindowHandle();
			viewMoreDetailsPage.clickingOnAmazonFromViewMoreDetailsPage();
	       
		    Thread.sleep(1000);
		  
	       //creating set to store all the windows here in order to switch to the amazon site 
		    Set<String> windowHandles=driver.getWindowHandles();
	       
	       
		    for (String window : windowHandles) {
			    if (window!=cWindow) {  // Use .equals() for proper string comparison
			        Thread.sleep(2000);
			        driver.switchTo().window(window);
			        
			         // Exit loop after switching to the first found window
			    }
			 }

	       
	       //Verifying how many windows here to check if we are accessing the right window here
	       int i=1;
	       for(String str:windowHandles) { 
	    	   	System.out.println(str+"   "+"window-No-->"+i);
	    	   	i++;
	       }
	      
	       amazonProductMainPage.getAmazonProductName();
	       amazonProductMainPage.getAmazonProductPrice();
	       String amazonWindow=amazonProductMainPage.getAmazonWindowHandle();
	       Thread.sleep(2000);
	      
	   
	     // AllFunctions.compareName(str1,str2);
	      
	      globalUtility.compareNameViaHashMapMethod( viewMoreDetailsPage.getGoogleProductName(), amazonProductMainPage.getAmazonProductName());
	      
	      globalUtility.compareprice(viewMoreDetailsPage.getGoogleProductPrice(), amazonProductMainPage.getAmazonProductPrice());
	      
	      
	      
	       Set<String> windowHandlesLatest = driver.getWindowHandles();
	       
	       driver.close();
	       
	       for (String windowHandle : windowHandlesLatest) {
	    	    if (windowHandle!=amazonWindow) { // Switch to the window that isn't the current one
	    	        driver.switchTo().window(windowHandle);
	    	        break; // No need to continue looping once we've switched
	    	    }
	    	}
	       
	       
	       driver.navigate().back();
	       globalUtility.scrollByPixels(0, -200);
	       Thread.sleep(3000);
	       System.out.println("pop-up closing button here ");
	       closeButtonGooglePopup.click();
	       

	}
	    
}
