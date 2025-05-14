package googleaggregator;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GenericHelper {

	public String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36";

	//public String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    public String url = "https://www.google.com/shopping";
	public String searchTerm;
    //public String url = "https://www.google.com/search?q=" + searchTerm + "&tbm=shop";
    public int implicitWait = 30;
    public int pageLoadOutTime = 30;
    public String inputForSearch = "Mobile";
    public List<WebElement> productList;    
    
    public  WebDriver driver;
    
    public  WebDriver initDriver(String userAgent) {
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("user-agent=" + userAgent);
    //    driver = new ChromeDriver(options);
        
        return driver;
    }

    public  void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public  void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public  void setImplicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
       
    }
    
    public  void setPageLoadOutTimeOut(WebDriver driver, int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }
    
    
    public void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollByPixels(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

}
