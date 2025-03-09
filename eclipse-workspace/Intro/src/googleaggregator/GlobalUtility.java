package googleaggregator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
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
    
	public GlobalUtility(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	

	public  WebDriver initDriver(String userAgent) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + userAgent);
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


public  boolean isElementInViewport(WebDriver driver, WebElement element) {
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



public   void captureScreenshot(ITestResult result) {
    // Check if driver is valid and instance of TakesScreenshot
    if (driver instanceof TakesScreenshot) {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

        try {
            // Get current date-time to make the screenshot name unique
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            
            // Get the test method name to include in the screenshot filename
            String testName = result.getMethod().getMethodName();  // Get the test method name
            
            // Create a unique filename using the test name and timestamp
            String screenshotName = testName + "_" + timestamp + ".png";
            
            // Define the directory where screenshots will be stored
            String screenshotDir = "screenshots/";

            // Ensure the directory exists
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Define the destination file path for the screenshot
            File destinationFile = new File(screenshotDir + screenshotName);

            // Copy the screenshot to the destination
            FileUtils.copyFile(screenshot, destinationFile);

            System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


     public Set<String> getAllWindowHandles() {
    	 
    	 Set<String> windowHandles = driver.getWindowHandles();
    	 return windowHandles;
     }


}
