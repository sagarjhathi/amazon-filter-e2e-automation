package googleaggregatorPOM;

import java.time.Duration;
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
    private GlobalUtility globalUtility;
    private ViewMoreDetailsPage viewMoreDetailsPage;
    private GoogleShoppingProductPopup googleShoppingProductPopup;
    private AmazonProductMainPage amazonProductMainPage;
    public GenericHelper genericHelper;

    public GoogleShoppingMainPage(WebDriver driver, GlobalUtility globalUtility) {
    	this.driver=driver;
    	 this.globalUtility = globalUtility;
    	this.genericHelper=new GenericHelper();
        this.viewMoreDetailsPage = new ViewMoreDetailsPage(driver);
        this.googleShoppingProductPopup = new GoogleShoppingProductPopup(driver);
        this.amazonProductMainPage = new AmazonProductMainPage(driver);
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
    
    public List<WebElement> gettingAllProducts(WebDriver driver){
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement products = wait.until(ExpectedConditions.visibilityOf(getAllProducts));
         return products.findElements(By.xpath("./*"));
    }
    
    
    
    public void selectingAmazonFromSellerMenu(WebDriver driver) {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement amazonInSeller = wait.until(ExpectedConditions.visibilityOf(amazonFromSellerMenu));
        globalUtility.scrollByPixels(driver, 0, -300);
        amazonInSeller.click();
        
    }
    
    public String getGoogleWindowHandle() {
    	String cWindow=driver.getWindowHandle();
    	return cWindow;
    }

   

    public void scrollToSellerLandingPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(seller));
        js.executeScript("arguments[0].scrollIntoView(true);", seller);
    }

    public void clickOnMoreInSellerMenuLandingPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(moreSellerButton)).click();
    }

    public void applyingPriceFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the price filter then re-running the function");
        applyFilterAndTraverse(driver, priceFilter, list);
    }

    public void applyingRamFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the ram filter then re-running the function");
        applyFilterAndTraverse(driver, ramFilter, list);
    }

    public void applyingRatingFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the rating filter then re-running the function");
        globalUtility.scrollToElement(driver, moreFromProductRating);
        globalUtility.scrollByPixels(driver, 0, -300);
        Thread.sleep(3000);
        moreFromProductRating.click();
        applyFilterAndTraverse(driver, fourAndUpRating, list);
    }

    public void applyingWeightFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the weight filter then re-running the function");
        applyFilterAndTraverse(driver, weightFilter, list);
    }

    public void applyingDeliveryFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the delivery filter then re-running the function");
        applyFilterAndTraverse(driver, deliveryFilter, list);
    }

    public void applyingBrandFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the mobile brand filter then re-running the function");
        applyFilterAndTraverse(driver, mobileBrand, list);
    }

    public void applying5GFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the 5G filter then re-running the function");
        applyFilterAndTraverse(driver, selecting5G, list);
    }

    public void applyingDualSimFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the Dual Sim filter then re-running the function");
        applyFilterAndTraverse(driver, dualSim, list);
    }

    public void applyingColorFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the color filter then re-running the function");
        applyFilterAndTraverse(driver, colorFilter, list);
    }

    public void applyingStorageFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the storage filter then re-running the function");
        applyFilterAndTraverse(driver, storageCapacityFilter, list);
    }

    public void applyingOsFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the os filter then re-running the function");
        applyFilterAndTraverse(driver, osFilter, list);
    }

    public void applyingCellularNetworkFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the cellular Network filter then re-running the function");
        applyFilterAndTraverse(driver, cellularNetworkFilter, list);
    }

    public void applyingSecurityFeaturesFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the security features filter then re-running the function");
        applyFilterAndTraverse(driver, securityFeaturesFilter, list);
    }

    public void applyingScreenResolutionFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the screen resolution filter then re-running the function");
        applyFilterAndTraverse(driver, screenResolutionFilter, list);
    }

    public void applyingCameraFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the camera filter then re-running the function");
        applyFilterAndTraverse(driver, cameraFilter, list);
    }

    public void applyingHeadphoneFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the headphone filter then re-running the function");
        applyFilterAndTraverse(driver, headphoneConnectorFilter, list);
    }

    public void applyingLensFilter(WebDriver driver, List<WebElement> list) throws InterruptedException {
        System.out.println("Applying the lens filter then re-running the function");
        applyFilterAndTraverse(driver, lensTypeFilter, list);
    }
    
    

   
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
    
    
	 public  void applyFilterAndTraverse(WebDriver driver, WebElement element, List<WebElement> list) throws InterruptedException {
		      globalUtility.scrollAndClick(driver, element);
		      if(checkNoResultsAndNavigateBack()) {
		    	  return;
		      }
		      
		    int countCheck = 0;
  	        for (int i = 1; i < list.size(); i++) {
  	            switchWindowAndCompare(driver, i);
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
	    
	 
	 
	 
	 public void productLocatingAndClicking(WebDriver driver, int productIndex) {
		 
		 WebElement productLocating=driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]"));

		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		 
		 WebElement clickOnProduct=wait.until(ExpectedConditions.elementToBeClickable(productLocating));
		 clickOnProduct.click();
		 
	 }
	 
	 
	 
	 
	 
	    public  void switchWindowAndCompare(WebDriver driver,int productIndex) throws InterruptedException {
			 
			driver.navigate().refresh();
			Thread.sleep(1000);
			productLocatingAndClicking(driver,productIndex);	  
		    googleShoppingProductPopup.scrollAndClickViewMoreDetails();
		    viewMoreDetailsPage.getGoogleProductName();
			viewMoreDetailsPage.getGoogleProductPrice();
			Thread.sleep(1000);
			String cWindow=getGoogleWindowHandle();
			viewMoreDetailsPage.clickingOnAmazonFromViewMoreDetailsPage();
	       
		    Thread.sleep(1000);
		  
	       //creating set to store all the windows here in order to switch to the amazon site 
		    Set<String> windowHandles=globalUtility.getAllWindowHandles();
	       
	       
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
	        globalUtility.scrollByPixels(driver, 0, -200);
	       Thread.sleep(3000);
	       System.out.println("pop-up closing button here ");
	       closeButtonGooglePopup.click();
	       

	}
}
