package orangehr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.Collections;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    	public static synchronized WebDriver initDriver() {
    	    // Only create a new driver if one doesn't exist for this thread
    	    if (driverThreadLocal.get() == null) {
    	        ChromeOptions options = new ChromeOptions();
    	        // existing options
    	        
    	        WebDriver driver = new ChromeDriver(options);
    	        driver.manage().window().maximize();
    	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    	        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    	        
    	        driverThreadLocal.set(driver);
    	    }
    	    return getDriver();
    	
    }

    public static synchronized WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static synchronized void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Log but continue - don't let exceptions here stop the test cleanup
                System.err.println("Error during driver quit: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}