package googleaggregatorpomupdated;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    // ThreadLocal to keep WebDriver instance safe for parallel tests
    private  final static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // Method to initialize and set the driver
    public static  WebDriver initDriver() {
        if (driverThread.get() == null) {

            // ✨ You can make userAgent configurable later
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                             + "(KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36";

            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=" + userAgent);
            // options.addArguments("--headless"); // Optional: Uncomment if needed
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--lang=en");
            options.addArguments("--start-maximized");


            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverThread.set(driver);
            return driver;
        }
        return null;
		
    }

    public static  WebDriver getDriver() {
        return driverThread.get();
    }

    public static  void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
