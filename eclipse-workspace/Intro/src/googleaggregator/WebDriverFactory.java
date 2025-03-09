package googleaggregator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

		WebDriver driver;
		
	    public WebDriver initDriver(String userAgent) {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("user-agent=" + userAgent);
	        driver = new ChromeDriver(options);
	        return driver;
	    }
	    
}
