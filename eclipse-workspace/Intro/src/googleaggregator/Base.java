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
        Thread.sleep(2000);
        
        //Using keys down to select the option from the suggestions given related to the inputs
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN.ENTER);
        Thread.sleep(3000);
        
        //Creating javascript executor here for scrolling
        JavascriptExecutor js=(JavascriptExecutor)driver;
        
        //Locating the seller menu within the web where user can select Amazon in the sellers options
        WebElement seller=driver.findElement(By.xpath("//div[@class='lg3aE' and text()='Seller']"));
        Thread.sleep(3000);
        
        //Scrolling to the point where the seller menu field is within the view port
        js.executeScript("arguments[0].scrollIntoView(true);",seller);
        Thread.sleep(6000);
        
        //clicking upon the "more" button with in seller menu in order to make all the options visible hence to select amazon
       
        driver.findElement(By.xpath("//div[@aria-label='More Seller']")).click();
        Thread.sleep(5000);
        
        //Locating  the amazon option within the sellers menu field
        WebElement amazonInSeller=driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']"));
        //js.executeScript("arguments[0].scrollIntoView",amazonInSeller);
        
        //Scrolling a bit more up to see the amazon option with in the menu
        js.executeScript("window.scrollBy(0,-300)");
        
        
        //Clicking upon the amazon option with in the seller menu after scrolling a bit
        driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']")).click();
        
        
        //Clicking upon the  1st product after clicking the amazon  
       //driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[1]")).click();
        
       //Clicking upon view more details after clicking the 1st product  in the above click
      // driver.findElement(By.xpath("//a[text()='View product details']")).click();
       
       
       //Creating arraylist to store all the web elements of the below address to locate and store all the prices of the product.
       List<WebElement> list=new ArrayList<>();
       list=driver.findElements(By.xpath("//span[@class='g9WBQb']"));
       
       
       //Creating an arraylist to store the exact number after parsing and triming down using the extract function called within. 
//       List<String> gPrices=new ArrayList<>();
//       for(int i=0;i<list.size();i++) {
//    	   gPrices.add(extractPrice(list.get(i).getText()));
//       }
       
       
       //Printing the numbers here again to check the trimming
//       for(int i=0;i<gPrices.size();i++) {
//    	   System.out.println(gPrices.get(i));
//       } 
       
       
       
       //Getting the current window  which currently is the google shopping page .
       String cWindow=driver.getWindowHandle();
       
       //Extracting the product name here of the product before going to the next window.
     //  String gProductName = driver.findElement(By.cssSelector("span.BvQan.sh-t__title-pdp.sh-t__title.translate-content")).getText();

       
          
       //Here at this click another window opens which takes the user to the amazon showing the details of the same product.
     //  driver.findElement(By.xpath("(//div[@class='Kl9jM UKKY9'])[1]")).click();
       
       
       
       //Creating a set to store all the windows available here
       Set<String> windowHandles = driver.getWindowHandles();
       
       // Create an iterator to loop through the window handles and once found the window which was not matching the current window
       //meaning we went to the next window here because at  this point we only have 2 windows 
//       for(String str:windowHandles) {
//    	   if(str!=cWindow) {
//    		   driver.switchTo().window(str);
//    	   }
//       }
       
       
       //Storing the window of the amazon here
       String amazonWindow=driver.getWindowHandle();
       
       //Extracting the amazon title and printing here to verify if the driver did switch by printing the title.
       String amazonWindowTitle=driver.getTitle();
       System.out.println(driver.getTitle());
       
       
       
       //Extracting the price of the product on the amazon here 
      // String amazon=driver.findElement(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']")).getText();

      
       //trimming and parsing the extracted amazon price in the above step
//       amazon= extractPrice(amazon);
//       System.out.println(amazon);
       
       
       //Verifying the extract function to check how does it trim and process the numbers here
       String str="588,99,000.001";
       System.out.println(extractPrice(str));
       
       //Extracting the product name here within the amazon
    //   String productName=driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
       
       
       //extracting the product names here in order to assert them in future
    //   System.out.println(productName +"                "+gProductName);
       
       
       //closing the window here trying to switch the window back to google shopping
     //  driver.close();  // Close the second window
       
       

       // After closing the second window, WebDriver will automatically switch to the first window
       // You can now switch to the first window using the updated window handles
       Set<String> windowHandlesLatest = driver.getWindowHandles();
       
       windowHandlesLatest.remove(amazonWindow);
       
       //Switching back to the google shopping window here by comparing the amazon window with google shopping .
//       for (String windowHandle : windowHandlesLatest) {
//    	    if (windowHandle!=amazonWindow) { // Switch to the window that isn't the current one
//    	        driver.switchTo().window(windowHandle);
//    	        break; // No need to continue looping once we've switched
//    	    }
//    	}
        
       //Verifying if the driver did switch in the above step by printing it
       System.out.println(driver.getTitle());
      
//       Thread.sleep(2000);
//       //clicking back until reach the landing page here
//       driver.navigate().back();
//       Thread.sleep(2000);
//       driver.navigate().back();
//       Thread.sleep(2000);
      
      
       //Locating the on sale button in the google shopping page
      // WebElement onSaleElement = driver.findElement(By.xpath("//span[@title='On sale']"));
       
       // Check if the element is selected before clicking
      // System.out.println("Before click - isSelected: " + onSaleElement.isSelected());
       
       // Scroll to the element if needed
      // js.executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'nearest'})", onSaleElement);
       //js.executeScript("window.scrollBy(0,-300);");

       // Click the element (button, checkbox, or radio button)
       //  onSaleElement.click();
       
       // Wait for the action to take effect (you may need to adjust this for your specific case)
       Thread.sleep(2000);  // You can also replace Thread.sleep() with WebDriverWait if you need dynamic waits
      
       //locating the element having all the products here to traverse within them
       WebElement products = driver.findElement(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]"));
       
       //Storing all the products here within the list here
       List<WebElement> childElements = products.findElements(By.xpath("./*"));
       
       //Printing the size of the products 
       System.out.println(childElements.size());	
       
       //clicking upon amazon by scrolling down again 
//       amazonInSeller=driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']"));
//       js.executeScript("arguments[0].scrollIntoView",amazonInSeller);
//       js.executeScript("window.scrollBy(0,-300)");
//        Thread.sleep(5000);
        
        //clicking upon the amazon button in sellers menu
      //  driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']")).click();
       
       
  
       int c=0;
       for(int i=1;i<childElements.size();i++) {
    	   
    	  switchWindowAndCompare(driver,i);
    	  c++;
    	  if(c==4) {
    		  c=0;
    	  }
       }
       
       
       
        
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
		   
		WebElement checkInScroll=driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]"));
//		if(!isElementInViewport(driver,checkInScroll)) {
//			JavascriptExecutor jss=(JavascriptExecutor)driver;
//			jss.executeScript("arguments[0].scrollIntoView(true)", checkInScroll);
//			Thread.sleep(4000);
//	}
		
		
	
		
			driver.navigate().refresh();
		
		    Thread.sleep(5000);
			//clicking upon the products card from the google shopping landing page
		    driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]")).click(); 
		    
		    Thread.sleep(5000);
		    WebElement viewMoreDetails=driver.findElement(By.xpath("(//span[@class='_-p3 _-pZ'])[1]"));
		    JavascriptExecutor jss=(JavascriptExecutor)driver;
			jss.executeScript("arguments[0].scrollIntoView(true)", viewMoreDetails);
			Thread.sleep(3000);
		    jss.executeScript("window.scrollBy(0,-200);");
			//Thread.sleep(5000);
			viewMoreDetails.click();
//			Actions act=new Actions(driver);
//			act.moveToElement(driver.findElement(By.xpath("(//div[@class='_-pA'])[" + productIndex + "]"))).click().build().perform();
//			
			
		    
		   //clicking upon the view more details with in the product details
		//    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='View product details']")))).click();
	      // driver.findElement(By.xpath("//a[text()='View product details']")).click();
		    
//		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("overlay-id")));  // Replace with actual overlay's locator
//		    WebElement element = driver.findElement(By.xpath("//a[text()='View product details' and contains(@class, '_-pz')]"));
//		    element.click();
		    
//		    WebElement otherOptions = driver.findElement(By.xpath("//label[text()='Other options:']"));
//		    WebDriverWait w=(WebDriverWait) new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(otherOptions));
//		    
		   
		   
	//    driver.findElement(By.xpath("//label[text()='Other options:']")).click();
//		    WebElement element = driver.findElement(By.xpath("//a[contains(@class, '_-ol') and contains(@class, '_-oq') and contains(@class, '_-nD')]"));
		   // element.click();

		     
	       
	       //storing the current window which is google shopping individual product details page
		   String cWindow=driver.getWindowHandle();
		   
		   Thread.sleep(5000);
		   //Extracting the product name here from the google shopping product details page
	      // String gProductName = driver.findElement(By.cssSelector("span.BvQan.sh-t__title-pdp.sh-t__title.translate-content")).getText();

	       Thread.sleep(3000);
	       //this is the click which takes the use to the amazon page from google shopping page to amazon page
	      // driver.findElement(By.xpath("(//div[@class='Kl9jM UKKY9'])[1]")).click();
	    //   driver.findElement(By.xpath("(//div[@class='Kl9jM UKKY9' and text()='Visit site'])[1]")).click();
	       
	       
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
	      
	       //Storing the amazon window here as the driver should be at the amazon site
	      String amazonWindow=driver.getWindowHandle();
	      Thread.sleep(5000);
	      
	      //extracting the price here of the product from the amazn=on site
	       String amazon=driver.findElement(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']")).getText();

	       //Extracting the product name here from the amazon site here
	       String productName=driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
	       
	       
	       Set<String> windowHandlesLatest = driver.getWindowHandles();
	       
	       driver.close();
	       
	       for (String windowHandle : windowHandlesLatest) {
	    	    if (windowHandle!=amazonWindow) { // Switch to the window that isn't the current one
	    	        driver.switchTo().window(windowHandle);
	    	        break; // No need to continue looping once we've switched
	    	    }
	    	}
	       
	     //  driver.navigate().back();
	       Thread.sleep(5000);
	       
	       jss.executeScript("window.scrollBy(0,-200);");
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//a[@class=' _-pO']")).click();
	       

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

}
