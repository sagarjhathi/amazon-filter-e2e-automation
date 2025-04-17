package googleaggregatorpomupdated;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

	
	 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static void initializeDriver() {
	        if (driver.get() == null) {
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--remote-allow-origins=*");
	            options.addArguments("user-agent=" + GenericHelper.userAgent);
	            // options.addArguments("--headless");
	             options.addArguments("--disable-gpu");
	             options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection
	             options.addArguments("--no-sandbox"); // Stability in CI environments
	             options.addArguments("--disable-dev-shm-usage");
	             options.addArguments("--lang=en");
	             options.addArguments("--start-maximized");

	            WebDriver webDriver = new ChromeDriver(options);
	            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	         
	            driver.set(webDriver);
	        }
	    }

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
}
