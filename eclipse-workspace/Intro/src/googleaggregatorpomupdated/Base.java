package googleaggregatorpomupdated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {

	WebDriver driver;
	 // Before each test, initialize the WebDriver
    @BeforeMethod
    public WebDriver setUp() {
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + GenericHelper.userAgent);
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

    // After each test, quit the WebDriver
    @AfterMethod
    public void tearDown() {
       driver.quit();
    }

	
}
