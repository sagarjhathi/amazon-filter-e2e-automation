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
        
        
       
       //Creating arraylist to store all the web elements of the below address to locate and store all the prices of the product.
       List<WebElement> list=new ArrayList<>();
       list=driver.findElements(By.xpath("//span[@class='g9WBQb']"));
       
       
       
       //Getting the current window  which currently is the google shopping page .
       String cWindow=driver.getWindowHandle();
       
       
       //Creating a set to store all the windows available here
       Set<String> windowHandles = driver.getWindowHandles();
       
       
       //Storing the window of the amazon here
       String amazonWindow=driver.getWindowHandle();
       
       //Extracting the amazon title and printing here to verify if the driver did switch by printing the title.
       String amazonWindowTitle=driver.getTitle();
       System.out.println(driver.getTitle());
       
 
       //Verifying the extract function to check how does it trim and process the numbers here
       String str="588,99,000.001";
       System.out.println(extractPrice(str));
       
       
       // After closing the second window, WebDriver will automatically switch to the first window
       // You can now switch to the first window using the updated window handles
       Set<String> windowHandlesLatest = driver.getWindowHandles();
       
       windowHandlesLatest.remove(amazonWindow);
       
      
       //Verifying if the driver did switch in the above step by printing it
       System.out.println(driver.getTitle());
      
     
       // Wait for the action to take effect (you may need to adjust this for your specific case)
       Thread.sleep(2000);  // You can also replace Thread.sleep() with WebDriverWait if you need dynamic waits
      
       //locating the element having all the products here to traverse within them
       WebElement products = driver.findElement(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]"));
       
       //Storing all the products here within the list here
       List<WebElement> childElements = products.findElements(By.xpath("./*"));
       
       //Printing the size of the products 
       System.out.println(childElements.size());	
       
      
  
       int c=0;
       for(int i=1;i<childElements.size();i++) {
    	   
    	  switchWindowAndCompare(driver,i);
    	  c++;
    	  if(c==1) {
    		  break;
    	  }
       }
       
                Thread.sleep(1000);
                driver.navigate().refresh();
                
    		   
       
//      WebElement productsAfterPriceFilter = driver.findElement(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]"));
//      
//      //Storing all the products here within the list here
//      List<WebElement> childElementsAfterPriceFilter = productsAfterPriceFilter.findElements(By.xpath("./*"));
//       
//      System.out.println(childElementsAfterPriceFilter.size());
//      
//      int cc=0;
//      for(int i=1;i<childElementsAfterPriceFilter.size();i++) {
//   	   
//   	  switchWindowAndCompare(driver,i);
//   	  cc++;
//   	  if(cc==1) {
//   		  break;
//   	  }
//      }
        
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

				
			driver.navigate().refresh();
		
		    Thread.sleep(5000);
			//clicking upon the products card from the google shopping landing page
		    driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[" + productIndex + "]")).click(); 
		    Thread.sleep(5000);
		    WebElement viewMoreDetails=driver.findElement(By.xpath("(//span[@class='_-p2 _-pY'])[1]"));
		    JavascriptExecutor jss=(JavascriptExecutor)driver;
			jss.executeScript("arguments[0].scrollIntoView(true)", viewMoreDetails);
			Thread.sleep(3000);
		    jss.executeScript("window.scrollBy(0,-200);");
			Thread.sleep(5000);
			viewMoreDetails.click();
		
			
		    		
			WebElement googleShoppingPrice =driver.findElement(By.xpath("(//span[@class='_-p2 _-pY'])[1]"));
			System.out.println(googleShoppingPrice.getText()+"    "+"googleShoppingPrice here");
			
		     
	       
	       //storing the current window which is google shopping individual product details page
		   String cWindow=driver.getWindowHandle();
		   
		   Thread.sleep(5000);
		  
	           
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
	       
	      // driver.navigate().back();
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
