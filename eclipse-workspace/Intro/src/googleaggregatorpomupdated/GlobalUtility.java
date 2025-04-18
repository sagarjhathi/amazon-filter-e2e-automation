package googleaggregatorpomupdated;

import java.io.File; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

public class GlobalUtility {

	
    public  WebDriver driver;
	String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36";

	public GlobalUtility(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	
	
	public  WebDriver initDriver(String userAgent) {
		
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + userAgent);
       // options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection
        options.addArguments("--no-sandbox"); // Stability in CI environments
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--lang=en");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        return driver;
    }
	
	
	

    public  void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public  void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public void setImplicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
       
    }
    
    public  void setPageLoadOutTimeOut(WebDriver driver, int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }
    
    
    public  void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollByPixels(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }
    
public  void compareNameViaHashMapMethod(String str1,String str2) {
    	
    	str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Set<String> wordsInStr2 = new HashSet<>(Arrays.asList(str2.split("[\\s\\W]+")));
        
        for (String word : str1.split("[\\s\\W]+")) {
            if (wordsInStr2.contains(word)) {
                System.out.println(word + " - Word found in both strings");
            } else {
                System.out.println(word + " - Word NOT found in str2");
            }
        }
    	
    }
    


public  void compareprice(String str1, String str2) {
	
	System.out.println(extractPrice(str1)+  "    "+   "Compare price str1 ");
	
	
	System.out.println(extractPrice(str2)+  "    "+   "Compare price str2 ");

}


public  String extractPrice(String str) {
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





public  void scrollAndClick(WebDriver driver, WebElement element) throws InterruptedException {
    scrollToElement(driver, element);
    scrollByPixels(driver, 0, -300);
    Thread.sleep(2000);
    element.click();
}


 public Set<String> getAllWindowHandles() {
    	 Set<String> windowHandles = driver.getWindowHandles();
    	 return windowHandles;
     }


}
