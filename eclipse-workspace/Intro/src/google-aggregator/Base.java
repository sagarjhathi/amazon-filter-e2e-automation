package flight_aggregator;

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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class Base {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

	    ChromeOptions options = new ChromeOptions();

        // Replace with your actual User-Agent string
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        options.addArguments("user-agent=" + userAgent);

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);
        
        driver.get("https://shopping.google.com/");
        
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).click();
        
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys("Mobile");
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']")).sendKeys(Keys.DOWN.ENTER);
        Thread.sleep(3000);
        
        JavascriptExecutor js=(JavascriptExecutor)driver;
        WebElement seller=driver.findElement(By.xpath("//div[@class='lg3aE' and text()='Seller']"));
        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView(true);",seller);
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@jsname='bkkWOc'])[2]")).click();
        Thread.sleep(3000);
        WebElement amazonInSeller=driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']"));
        js.executeScript("arguments[0].scrollIntoView",amazonInSeller);
        js.executeScript("window.scrollBy(0,-300)");
        
        driver.findElement(By.cssSelector("span.lg3aE[title='Amazon.in']")).click();
        
        
       driver.findElement(By.xpath("(//span[@class='OA4wid' and text()='Smartphone'])[1]")).click();
        
       driver.findElement(By.xpath("//a[text()='View product details']")).click();
       
       List<WebElement> list=new ArrayList<>();
       list=driver.findElements(By.xpath("//span[@class='g9WBQb']"));
       
       List<String> gPrices=new ArrayList<>();
       
       for(int i=0;i<list.size();i++) {
    	   gPrices.add(extractPrice(list.get(i).getText()));
       }
       
       for(int i=0;i<gPrices.size();i++) {
    	   System.out.println(gPrices.get(i));
       } 
       
       String cWindow=driver.getWindowHandle();
       
       
       driver.findElement(By.xpath("(//div[@class='Kl9jM UKKY9'])[1]")).click();
       
       Set<String> li=new HashSet<>();
       
       li=driver.getWindowHandles();
       
       Set<String> windowHandles = driver.getWindowHandles();
       
       // Create an iterator to loop through the window handles
      
       for(String str:windowHandles) {
    	   if(str!=cWindow) {
    		   driver.switchTo().window(str);
    	   }
       }
       
       
       String amazon=driver.findElement(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']")).getText();

      amazon= extractPrice(amazon);
       System.out.println(amazon);
       
       String str="588,99,000.001";
       System.out.println(extractPrice(str));
       
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

}
