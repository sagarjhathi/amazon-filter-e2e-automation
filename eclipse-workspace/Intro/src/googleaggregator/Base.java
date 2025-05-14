package googleaggregator;




import org.openqa.selenium.chrome.ChromeDriver;    
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class Base {
    
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		//Using chrome options for handling the bot checkers or captcha's
	    ChromeOptions options = new ChromeOptions();

        // Replace with your actual User-Agent string with the below user agent
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        options.addArguments("user-agent=" + userAgent);

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);
        
        
        //Hitting the url
        driver.get("https://shopping.google.com/");
        //Maximize the window here
        driver.manage().window().maximize();
        //Adding implicit wait here for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
		        //Clicking on the search bar
		        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).click();
		        
        //Giving inputs within the search bar using send keys
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys("Mobile");
        Thread.sleep(1000);
        
        //Using keys down to select the option from the suggestions given related to the inputs
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN.ENTER);
        Thread.sleep(1000);
        
        //Creating javascript executor here for scrolling
        JavascriptExecutor js=(JavascriptExecutor)driver;
        
        //Locating the seller menu within the web where user can select Amazon in the sellers options
        WebElement seller=driver.findElement(By.xpath("//div[@class='lg3aE' and text()='Seller']"));
        Thread.sleep(1000);
        
        //Scrolling to the point where the seller menu field is within the view port
        js.executeScript("arguments[0].scrollIntoView(true);",seller);
        Thread.sleep(2000);
        
        //clicking upon the "more" button with in seller menu in order to make all the options visible hence to select amazon
       
        driver.findElement(By.xpath("//div[@aria-label='More Seller']")).click();
        Thread.sleep(2000);
        
        //Locating  the amazon option within the sellers menu field
        WebElement amazonInSeller=driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']"));
        //js.executeScript("arguments[0].scrollIntoView",amazonInSeller);
        
        //Scrolling a bit more up to see the amazon option with in the menu
        js.executeScript("window.scrollBy(0,-300)");
        
        
        //Clicking upon the amazon option with in the seller menu after scrolling a bit
        amazonInSeller.click();        
    
        
        Thread.sleep(2000);
        
        WebElement locatingFilterBlock=driver.findElement(By.xpath("(//div[@class='sh-dr__short']//div[@jsname='meClP'])[1]"));
        List<WebElement> childOfFilterBlock= locatingFilterBlock.findElements(By.xpath("./*"));
        
        
        
        for(int i=0;i<childOfFilterBlock.size();i++) {
        	System.out.println(childOfFilterBlock.get(i).getText()+"     "+"childOfFilterBlock size here is     "+childOfFilterBlock.size());
        }
        
        
        	
        
      WebElement locatingProducts = driver.findElement(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]"));
      //Storing all the products here within the list here
      List<WebElement> childElementsAfterPriceFilter = locatingProducts.findElements(By.xpath("./*"));
       
      
      
      
      System.out.println("Applying the price filter then re-running the function");
      WebElement priceFilter=driver.findElement(By.xpath("(//span[@class='lg3aE']/span[contains(text(),'₹')])[2]"));
      AllFunctions.applyFilterAndTraverse(driver, priceFilter, childElementsAfterPriceFilter);
      
      System.out.println("Applying the ram filter then re-running the function");
      WebElement ram=driver.findElement(By.xpath("(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'GB')])[5]"));
      AllFunctions.applyFilterAndTraverse(driver, ram, childElementsAfterPriceFilter);
     
     
     
      System.out.println("Applying the rating  filter then re-running the function");
      WebElement moreFromProductRating=driver.findElement(By.xpath("(//div[text()='More'])[1]"));
      
      js.executeScript("arguments[0].scrollIntoView(true)", moreFromProductRating);
      js.executeScript("window.scrollBy(0,-300);");
      Thread.sleep(5000);
      moreFromProductRating.click();
      
      
      WebElement fourAndUp=driver.findElement(By.xpath("(//div[@class='vq3ore' and @aria-label='4 out of 5 stars'])[2]"));
      AllFunctions.applyFilterAndTraverse(driver, fourAndUp, childElementsAfterPriceFilter);
      
      
      
      
      System.out.println("Applying the weight filter then re-running the function");
      WebElement weight=driver.findElement(By.xpath("(//*[contains(text(), 'grams')])[1]"));
      AllFunctions.applyFilterAndTraverse(driver, weight, childElementsAfterPriceFilter);
      
      
      
      
      System.out.println("Applying the delivery filter then re-running the function");
      WebElement delivery=driver.findElement(By.xpath("//span[@title='1–3 day delivery']"));
      AllFunctions.applyFilterAndTraverse(driver, delivery, childElementsAfterPriceFilter);
      
         
            
      System.out.println("Applying the mobile brand  filter then re-running the function");
      WebElement mobileBrand=driver.findElement(By.xpath("//span[@title='Realme']"));
      AllFunctions.applyFilterAndTraverse(driver, mobileBrand, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the 5G filter then re-running the function");
      WebElement selecting5G =driver.findElement(By.xpath("//span[@title='5G']"));
      AllFunctions.applyFilterAndTraverse(driver, selecting5G, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the Dual Sim filter then re-running the function");
      WebElement dualSim=  driver.findElement(By.xpath("//span[@title='Dual SIM']"));
      AllFunctions.applyFilterAndTraverse(driver, dualSim, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the color filter then re-running the function");
      WebElement color=driver.findElement(By.xpath("//span[text()='Black']"));
      AllFunctions.applyFilterAndTraverse(driver, color, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the storage filter then re-running the function");
      WebElement storageCapacity =driver.findElement(By.xpath("//span[text()='64 GB']"));
      AllFunctions.applyFilterAndTraverse(driver, storageCapacity, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the os filter then re-running the function");      
      WebElement os=driver.findElement(By.xpath("//span[text()='Android']"));
      AllFunctions.applyFilterAndTraverse(driver, os, childElementsAfterPriceFilter);
      

      
      System.out.println("Applying the cellular Network filter then re-running the function");
      WebElement cellularNetwork=driver.findElement(By.xpath("//span[@title='GSM Network']"));
      AllFunctions.applyFilterAndTraverse(driver, cellularNetwork, childElementsAfterPriceFilter);
      
      
      
      System.out.println("Applying the security features filter then re-running the function");
      WebElement securityFeatures=driver.findElement(By.xpath("//span[@title='Mobile Phones With Fingerprint Scanners']"));
      AllFunctions.applyFilterAndTraverse(driver, securityFeatures, childElementsAfterPriceFilter);
      
      
      System.out.println("Applying the sreen resolution filter then re-running the function");      
      WebElement screenResolution = driver.findElement(By.xpath("//span[@title='2408 x 1080']"));
      AllFunctions.applyFilterAndTraverse(driver, screenResolution, childElementsAfterPriceFilter);
      
      
      System.out.println("Applying the camera filter then re-running the function");
      WebElement camera= driver.findElement(By.xpath("//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and @title='< 8 MP']"));
      AllFunctions.applyFilterAndTraverse(driver, camera, childElementsAfterPriceFilter);
      
      
      System.out.println("Applying the headphone filter then re-running the function");
      WebElement headphoneConnector=driver.findElement(By.xpath("//span[@title='Headphone Jack']"));
      AllFunctions.applyFilterAndTraverse(driver, headphoneConnector, childElementsAfterPriceFilter);
      
      
      System.out.println("Applying the lens filter then re-running the function");
      WebElement lensType=driver.findElement(By.xpath("//span[@title='Ultra Wide Angle']"));
      AllFunctions.applyFilterAndTraverse(driver, lensType, childElementsAfterPriceFilter);
        
      
	} 
	
	public static String extractPrice(String str) {
        // Step 1: Remove currency symbol (₹, $, etc.)
        str = str.replaceAll("₹|\\$|€|£", "").trim(); // You can add more currency symbols if needed
        
        // Step 2: Remove decimal part, if present (i.e., remove anything after the dot)
        if (str.contains(".")) {
            str = str.substring(0, str.indexOf("."));
        }
        
        // Step 3: Remove commas (thousands separators)
        str = str.replace(",", "");
            
        // Return the cleaned up price
        return str;
    }
	
	

	
	public static void switchWindowAndCompare(WebDriver driver, int productIndex) throws InterruptedException {
		 
			driver.navigate().refresh();
				Thread.sleep(1000);
			WebElement productLocating=driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]"));

			//clicking upon the products card from the google shopping landing page
		    WebElement wt=new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(productLocating));
		   wt.click();

		    
		   
		    WebElement viewMoreDetails=driver.findElement(By.xpath("//a[text()='View product details']"));
		    JavascriptExecutor jss=(JavascriptExecutor)driver;
			jss.executeScript("arguments[0].scrollIntoView(true)", viewMoreDetails);
			Thread.sleep(1000);
		    jss.executeScript("window.scrollBy(0,-200);");
			Thread.sleep(1000);
			viewMoreDetails.click();
		
			String str1=driver.findElement(By.xpath("//span[@role='heading']")).getText();
			
//			String googleViewMoreDetailsPrice=driver.findElement(By.xpath("(//span[@class='g9WBQb'])[1]")).getText();
			
			String googleViewMoreDetailsPrice="";
			try{
			googleViewMoreDetailsPrice=driver.findElement(By.xpath("(//span[@class='g9WBQb'])[1]")).getText();
				
			}catch(Exception e) {
				System.out.println("The google  price is not available in Ui");
				driver.quit();
			}
			
			 System.out.println(googleViewMoreDetailsPrice+"     printing the google Price before processing ");
			Thread.sleep(1000);
			
			String cWindow=driver.getWindowHandle();
		     driver.findElement(By.xpath("(//div[@class='Kl9jM UKKY9'])[1]")).click();
	       
	       //storing the current window which is google shopping individual product details page
		   
		   
		   Thread.sleep(1000);
		  
	           
	       //creating set to store all the windows here in order to switch to the amazon site 
	       Set<String> windowHandles = driver.getWindowHandles();
	       
	       // Create an iterator to loop through the window handles and switch to the amazon site
	       for(String str:windowHandles) {
	    	   if(str!=cWindow) {
	    		  Thread.sleep(2000);
	    		   driver.switchTo().window(str);
	    	   }
	       }
	       
	       
	       
	       //Verifying how many windows here to check if we are accessing the right window here
	       for(String str:windowHandles) {
	    	   	System.out.println(str +"here here");
	       }
	      
	       
	       
	       String str2=driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
	       String amazonPriceFromAmzon="";
	       
	       
	           try{
	    	   amazonPriceFromAmzon=driver.findElement(By.xpath("//span[@class='a-price aok-align-ce"
	   	       		+ "nter reinventPricePriceToPayMargin priceToPay']")).getText();
				}catch(Exception e) {
					System.out.println("The amazon price is not available in Ui");
					driver.quit();
				}
	       
	       System.out.println(amazonPriceFromAmzon+"     printing the amazonPrice before processing ");
	       
	       //Storing the amazon window here as the driver should be at the amazon site
	      String amazonWindow=driver.getWindowHandle();
	      Thread.sleep(2000);
	      
	      
	     // AllFunctions.compareName(str1,str2);
	      
	      AllFunctions.compareNameViaHashMapMethod(str1, str2);
	      
	      AllFunctions.compareprice(googleViewMoreDetailsPrice, amazonPriceFromAmzon);
	      
	      
	      
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
	       
	       jss.executeScript("window.scrollBy(0,-200);");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//a[@aria-label='Close']")).click();
	       

	}
	
	
	 public static boolean isElementInViewport(WebDriver driver, WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        // JavaScript to check if the element is in the viewport
	        String script = "var elem = arguments[0], " +
	                        "bounding = elem.getBoundingClientRect(), " +
	                        "vPwidth = window.innerWidth || document.documentElement.clientWidth, " +
	                        "vPheight = window.innerHeight || document.documentElement.clientHeight, " +
	                        "vTop = bounding.top >= 0 && bounding.top < vPheight, " +
	                        "vLeft = bounding.left >= 0 && bounding.left < vPwidth; " +
	                        "return vTop && vLeft;";
	        return (Boolean) js.executeScript(script, element);
	    }
	 
	 public static void applyFilterAndTraverse(WebDriver driver, WebElement element, List<WebElement> list) throws InterruptedException {
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

}
