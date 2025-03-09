package googleaggregator;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleShoppingMainPage {

	WebDriver driver;
	GlobalUtility globalUtility;
	ViewMoreDetailsPage viewMoreDetailsPage;
	GoogleShoppingProductPopup googleShoppingProductPopup;
	AmazonProductMainPage amazonProductMainPage;
	
	public GoogleShoppingMainPage(WebDriver driver) {
		this.driver = driver;
		this.globalUtility=new GlobalUtility(driver);
		this.viewMoreDetailsPage=new ViewMoreDetailsPage(driver);
		this.googleShoppingProductPopup=new GoogleShoppingProductPopup(driver);
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

    public void clickOnMoreInSellerMenuLandingPage() {
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
    
       
	 public  void applyFilterAndTraverse(WebDriver driver, WebElement element, List<WebElement> list) throws InterruptedException {
			 JavascriptExecutor js=(JavascriptExecutor)driver;
			 js.executeScript("arguments[0].scrollIntoView(true)", element);
		      js.executeScript("window.scrollBy(0,-300);");
		      Thread.sleep(2000);
		      element.click();
		      
		     
		      
		      try {
		          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		          // Wait for the "No results" state element to be visible, if it appears
		          WebElement noResults = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                  By.xpath("//ul[@style='margin-left:1.3em;margin-bottom:2em']")
		          ));

		          if (noResults.isDisplayed()) {
		              System.out.println("No results found, navigating back...");
		              driver.navigate().back();  // Navigate back before returning
		              return;  // Exit the method if no results are found
		          }
		      } catch (TimeoutException e) {
		          // No "No results" state found, continue with the normal actions
		          System.out.println("Results found, proceeding with further actions.");
		      }
		    	    
		    	        int c = 0;
		    	        for (int i = 1; i < list.size(); i++) {
		    	            switchWindowAndCompare(driver, i);
		    	            c++;
		    	            
		    	            // Break after the first iteration
		    	            if (c == 1) {
		    	                break;
		    	            }
		    	        }
		    	        
		    	        // Perform further actions after comparison
		    	        Thread.sleep(1000);
		    	        driver.findElement(By.xpath("(//a[@class='vjtvke' and text()='Clear'])[1]")).click();
		    	        Thread.sleep(1000);
		         
		 }
	    
	 
	 
	 
	 public void productLocatingAndClicking(WebDriver driver, int productIndex) {
		 
		 WebElement productLocating=driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]"));

		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		 
		 WebElement clickOnProduct=wait.until(ExpectedConditions.elementToBeClickable(productLocating));
		 clickOnProduct.click();
		 
	 }
	 
	 
	 
	 
	 
	    public  void switchWindowAndCompare(WebDriver driver, int productIndex) throws InterruptedException {
			 
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
	       
	       
	       // Create an iterator to loop through the window handles and switch to the amazon site
	       for(String str:windowHandles) {
	    	   if(str!=cWindow) {
	    		  Thread.sleep(2000);
	    		   driver.switchTo().window(str);
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
	      
	      AllFunctions.compareNameViaHashMapMethod( viewMoreDetailsPage.getGoogleProductName(), amazonProductMainPage.getAmazonProductName());
	      
	      AllFunctions.compareprice(viewMoreDetailsPage.getGoogleProductPrice(), amazonProductMainPage.getAmazonProductPrice());
	      
	      
	      
	      // Extracting the price from Amazon
	      try {
	          WebDriverWait checkOne = new WebDriverWait(driver, Duration.ofSeconds(10));
	          WebElement amazonPrice = checkOne.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-price aok-align-center"
	                  + " reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']")));

	          if (amazonPrice != null && amazonPrice.isDisplayed() == false) {
	              System.out.println("The element is not available on the UI.");
	          }

	      } catch (Exception e) {
	          System.out.println("Element not found or not visible within the time limit.");
	          // Optionally, add code to skip this step or proceed with the next actions
	      }

	      // Extracting the product name from Amazon
	      String productName = "";
	      try {
	          productName = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
	      } catch (Exception e) {
	          System.out.println("Product name not found on Amazon.");
	          // You can leave productName empty or handle it differently
	      }
	      
	      if (!productName.isEmpty()) {
	          System.out.println("Product Name: " + productName);
	      }

	       
	       
	       Set<String> windowHandlesLatest = driver.getWindowHandles();
	       
	       driver.close();
	       
	       for (String windowHandle : windowHandlesLatest) {
	    	    if (windowHandle!=amazonWindow) { // Switch to the window that isn't the current one
	    	        driver.switchTo().window(windowHandle);
	    	        break; // No need to continue looping once we've switched
	    	    }
	    	}
	       
	       driver.navigate().back();
	       Thread.sleep(2000);
	       
	       globalUtility.scrollByPixels(driver, 0, -200);
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//a[@aria-label='Close']")).click();
	       

	}
}
