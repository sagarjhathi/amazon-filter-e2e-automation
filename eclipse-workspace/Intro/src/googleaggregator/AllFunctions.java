package googleaggregator;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllFunctions {

    public static WebDriver initDriver(String userAgent) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + userAgent);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void setImplicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public static void clickOnSearchBarLandingPage(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.click();
    }

    public static void inputWithInTheSearchBar(WebDriver driver, String input) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.sendKeys(input);
    }

    public static void selectFromRecommendations(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public static void scrollToSellerLandingPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement seller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lg3aE' and text()='Seller']")));
        js.executeScript("arguments[0].scrollIntoView(true);", seller);
    }

    public static void clickOnMoreInSellerMenuLandingPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement moreSellerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='More Seller']")));
        moreSellerButton.click();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollByPixels(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public static void selectAmazonFromSellerMenu(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement amazonInSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.lg3aE[title='Amazon.in']")));
        scrollByPixels(driver, 0, -300);
        amazonInSeller.click();
    }

    public static List<WebElement> getAllProducts(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]")));
        return products.findElements(By.xpath("./*"));
    }
    
    public static void applyingPriceFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the price filter then re-running the function");
        WebElement priceFilter=driver.findElement(By.xpath("(//span[@class='lg3aE']/span[contains(text(),'₹')])[2]"));
        applyFilterAndTraverse(driver, priceFilter, list);
    }
    
    public static void applyingRamFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the ram filter then re-running the function");
         WebElement ram=driver.findElement(By.xpath("(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'GB')])[5]"));
         applyFilterAndTraverse(driver, ram, list);
    }
    
    
    public static void applyingRatingFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the rating  filter then re-running the function");
         WebElement moreFromProductRating=driver.findElement(By.xpath("(//div[text()='More'])[1]"));
         
         scrollToElement(driver,moreFromProductRating);
         scrollByPixels(driver, 0, -300);
         Thread.sleep(3000);
         moreFromProductRating.click();
         
         WebElement fourAndUp=driver.findElement(By.xpath("(//div[@class='vq3ore' and @aria-label='4 out of 5 stars'])[2]"));
         applyFilterAndTraverse(driver, fourAndUp, list);
    }
    
    
    public static void applyingWeightfilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	System.out.println("Applying the weight filter then re-running the function");
        WebElement weight=driver.findElement(By.xpath("(//*[contains(text(), 'grams')])[1]"));
        applyFilterAndTraverse(driver, weight, list);
    }
    
    
   
    
    public static void applyingDeliveryFilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	 System.out.println("Applying the delivery filter then re-running the function");
    	    WebElement delivery=driver.findElement(By.xpath("//span[@title='1–3 day delivery']"));
    	   applyFilterAndTraverse(driver, delivery, list);
    }
    
    
    
    
    public static void applyingBrandFilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	System.out.println("Applying the mobile brand  filter then re-running the function");
        WebElement mobileBrand=driver.findElement(By.xpath("//span[@title='Realme']"));
        applyFilterAndTraverse(driver, mobileBrand, list);
   }
    
    
    public static void applying5gFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the 5G filter then re-running the function");
        WebElement selecting5G =driver.findElement(By.xpath("//span[@title='5G']"));
        applyFilterAndTraverse(driver, selecting5G, list);
        
    }
    
    
    
    
    public static void applyingDualSimFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the Dual Sim filter then re-running the function");
        WebElement dualSim=  driver.findElement(By.xpath("//span[@title='Dual SIM']"));
        applyFilterAndTraverse(driver, dualSim, list);
    }
    
    
    public static void applyingColorFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the color filter then re-running the function");
         WebElement color=driver.findElement(By.xpath("//span[text()='Black']"));
         applyFilterAndTraverse(driver, color, list);
    }
    
    
    public static void applyingStorageFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the storage filter then re-running the function");
         WebElement storageCapacity =driver.findElement(By.xpath("//span[text()='64 GB']"));
        applyFilterAndTraverse(driver, storageCapacity, list);
    }
    
    
    public static void applyingOsFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the os filter then re-running the function");      
         WebElement os=driver.findElement(By.xpath("//span[text()='Android']"));
         applyFilterAndTraverse(driver, os, list);
    }
    
    
    public static void applyingCellularNetworkFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the cellular Network filter then re-running the function");
        WebElement cellularNetwork=driver.findElement(By.xpath("//span[@title='GSM Network']"));
        applyFilterAndTraverse(driver, cellularNetwork, list);
    }
    
    public static void applyingSecurityFeaturesFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the security features filter then re-running the function");
        WebElement securityFeatures=driver.findElement(By.xpath("//span[@title='Mobile Phones With Fingerprint Scanners']"));
        applyFilterAndTraverse(driver, securityFeatures, list);
    }
    
    public static void applyingScreenResolutionFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the sreen resolution filter then re-running the function");      
        WebElement screenResolution = driver.findElement(By.xpath("//span[@title='2408 x 1080']"));
      applyFilterAndTraverse(driver, screenResolution, list);
    }
    
    public static void applyingCameraFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the camera filter then re-running the function");
        WebElement camera= driver.findElement(By.xpath("(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'MP')])[1]"));
        applyFilterAndTraverse(driver, camera, list);
    }
    
    public static void applyingHeadphoneFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the headphone filter then re-running the function");
         WebElement headphoneConnector=driver.findElement(By.xpath("//span[@title='Headphone Jack']"));
        applyFilterAndTraverse(driver, headphoneConnector, list);
    }
    
    public static void applyingLensFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the lens filter then re-running the function");
         WebElement lensType=driver.findElement(By.xpath("//span[@title='Ultra Wide Angle']"));
         applyFilterAndTraverse(driver, lensType, list);
    }
    
    public static void compareName(String str1, String str2) {
    	
    	str1=str1.toLowerCase();
    	str2=str2.toLowerCase();
    	
    	
    	 String googleSplit[]=str1.split("[\\s\\W]+");
    	
    	for(int i=0;i<googleSplit.length;i++) {
    		
    		String compare=googleSplit[i];
    		System.out.println(compare);   
    				
    		if(str2.contains(compare)) {
    			System.out.println("Google Word available in amazon");
    		}else {
    			
    			System.out.println("Google word not available in amazon");
    	
    		}
    	}
    	
    	
    }
    
    public static void compareNameViaHashMapMethod(String str1,String str2) {
    	
    	str1=str1.toLowerCase();
    	str2=str2.toLowerCase();
    	
    	
    	String googleSplit[]=str1.split("[\\s\\W]+");
    	String amazonSplit[]=str2.split("[\\s\\W]+");
    	
    	
    	HashMap<String,Integer> map=new HashMap<>();
    	
    	for(int i=0;i<amazonSplit.length;i++) {
    		map.put(amazonSplit[i],1);
    	}
    	
    	for(int i=0;i<googleSplit.length;i++) {
    		
    		if(map.containsKey(googleSplit[i])) {
    			map.put(googleSplit[i], map.get(googleSplit[i])+1);
    		}
    	}
    	
    	
    	for(int i=0;i<googleSplit.length;i++) {
    		if(map.containsKey(googleSplit[i])) {


    			System.out.println(googleSplit[i]);
    			System.out.println(str1+"    "+"this is str1");
    			System.out.println(str2+"    "+"this is str2");
    			System.out.println("Word found in map");
    			
    		}else {
    			System.out.println("Word not found in map");
    			System.out.println(googleSplit[i]);
    			System.out.println(str1+"    "+"this is str1");
    			System.out.println(str2+"    "+"this is str2");
    			
    		}
    	}	
    	
    }
    
    public static void compareprice(String str1, String str2) {
    	
    	System.out.println(extractPrice(str1)+  "    "+   "Compare price str1 ");
    	
    	
    	System.out.println(extractPrice(str2)+  "    "+   "Compare price str2 ");

    	
    	
    	
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
		
//		String googleViewMoreDetailsPrice=driver.findElement(By.xpath("(//span[@class='g9WBQb'])[1]")).getText();
		
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
    
//    public static void comparePrice(WebDriver driver, String str1, String str2) {
//        // Find all "view more details" links (or elements that might contain the text you're looking for)
//        List<WebElement> viewMoreDetailsPrice = driver.findElements(By.xpath("//a[@class='b5ycib shntl']"));
//
//        // Loop through each element to check if it contains the "Amazon.in" text
//        for (int i = 0; i < viewMoreDetailsPrice.size(); i++) {
//            if (viewMoreDetailsPrice.get(i).getText().equals("Amazon.in")) {
//                
//                // Find the price corresponding to that Amazon link
//            	WebElement priceExtract = viewMoreDetailsPrice.get(i)
//                        .findElement(By.xpath(".//following-sibling::span[@class='g9WBQb']"));
//                    
//                // Print the price or perform comparison
//                System.out.println(priceExtract.getText());
//            }
//        }
//    }

}
