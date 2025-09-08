package amazonfilterapplicatione2e;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver() {
		if(driver.get()==null) {

			//String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.6422.112 Safari/537.36";
			ChromeOptions options = new ChromeOptions();
			
		//	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
		//		    + "AppleWebKit/537.36 (KHTML, like Gecko) "
		//		    + "Chrome/119.0.6045.200 Safari/537.36";
			
			
	            String ua = ConfigManager.get("userAgent");
	            if (ua != null && !ua.isBlank()) {
	                options.addArguments("user-agent=" + ua);
	            }

	            // headless toggle
	            if (ConfigManager.getBoolean("headless", false)) {
	                options.addArguments("--headless");
	            }

	            // language
	            String lang = ConfigManager.get("lang", "en");
	            options.addArguments("--lang=" + lang);

	            // start maximized
	            if (ConfigManager.getBoolean("startMaximized", true)) {
	                options.addArguments("--start-maximized");
	            }

	            // disable GPU
	            if (ConfigManager.getBoolean("disableGpu", false)) {
	                options.addArguments("--disable-gpu");
	            }
	            
			
		//	options.addArguments("user-agent=" + userAgent);
		//	options.addArguments("--disable-gpu");
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			//options.addArguments("--lang=en");
			options.addArguments("--disable-blink-features=AutomationControlled");
		//	options.addArguments("--start-maximized");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-extensions");
			//options.addArguments("--headless");
			
			driver.set(new ChromeDriver(options));
		}
	}
	

	
	public static void quitDriver() {
		if(driver.get()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
