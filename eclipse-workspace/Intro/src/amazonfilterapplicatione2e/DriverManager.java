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

			String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.6422.112 Safari/537.36";

			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-agent=" + userAgent);
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--lang=en");
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--headless=new");        // use new headless mode
			options.addArguments("--disable-extensions");
			options.addArguments("--window-size=1920,1080"); // important for responsive pages
		 //   options.addArguments("--headless");

			
			driver.set(new ChromeDriver(options));
		}
	}
	
	
//	public static void initDriver() {
//	    if (driver.get() == null) {
//
//	        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.6422.112 Safari/537.36";
//
//	        ChromeOptions options = new ChromeOptions();
//
//	        // clean/essential args
//	        options.addArguments("user-agent=" + userAgent);
//	        options.addArguments("--disable-gpu");
//	        options.addArguments("--no-sandbox");
//	        options.addArguments("--disable-dev-shm-usage");
//	        options.addArguments("--lang=en-US,en");
//	        options.addArguments("--start-maximized");
//	        options.addArguments("--disable-extensions");
//	        options.addArguments("--window-size=1920,1080");
//
//	        // reduce Selenium fingerprint
//	        options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
//	        options.setExperimentalOption("useAutomationExtension", false);
//
//	        // Optional persistent profile (uncomment for self-hosted runner)
//	        // String profileDir = System.getProperty("user.home") + "/.chrome_profile_for_ci";
//	        // options.addArguments("--user-data-dir=" + profileDir);
//
//	        // headless toggle via env var (default true for hosted runners)
//	        boolean headless = true;
//	        String headlessEnv = System.getenv("CI_HEADLESS");
//	        if (headlessEnv != null) {
//	            headless = Boolean.parseBoolean(headlessEnv);
//	        }
//	        if (headless) {
//	            options.addArguments("--headless=new");
//	        }
//
//	        // create driver
//	        ChromeDriver chrome = new ChromeDriver(options);
//
//	        // small, best-effort CDP tweaks to reduce automation detection (won't fail if CDP unsupported)
//	        try {
//	            // run early script to hide navigator.webdriver and provide small shims
//	            String script = ""
//	                + "try{ Object.defineProperty(navigator, 'webdriver', {get: () => undefined}); }catch(e){};"
//	                + "try{ Object.defineProperty(navigator, 'languages', {get: () => ['en-US','en']}); }catch(e){};"
//	                + "try{ Object.defineProperty(navigator, 'plugins', {get: () => [1,2,3]}); }catch(e){};";
//
//	            Map<String,Object> params = new java.util.HashMap<>();
//	            params.put("source", script);
//	            chrome.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
//
//	            // enable network and set Accept-Language header
//	            chrome.executeCdpCommand("Network.enable", java.util.Collections.emptyMap());
//	            Map<String,Object> headers = new java.util.HashMap<>();
//	            headers.put("Accept-Language", "en-US,en;q=0.9");
//	            Map<String,Object> hdrs = new java.util.HashMap<>();
//	            hdrs.put("headers", headers);
//	            chrome.executeCdpCommand("Network.setExtraHTTPHeaders", hdrs);
//
//	        } catch (Exception e) {
//	            // non-fatal — keep going without CDP
//	            System.err.println("CDP tweaks skipped: " + e.getMessage());
//	        }
//
//	        driver.set(chrome);
//	    }
//	}

	
	public static void quitDriver() {
		if(driver.get()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
