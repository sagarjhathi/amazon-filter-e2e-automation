package googleaggregator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.Iterator;

public class GoogleAggregatorTests {

    private WebDriver driver;

    // Before each test, initialize WebDriver
    @BeforeMethod
    public void setUp() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        driver = initDriver(userAgent);
    }

    // Helper Method to initialize WebDriver with a specific user-agent (Not a Test)
    private WebDriver initDriver(String userAgent) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + userAgent);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    // Helper Method to navigate to a URL (Not a Test)
    private void navigateToURL(String url) {
        driver.get(url);
    }

    // Helper Method to maximize window (Not a Test)
    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Helper Method to handle multiple windows (Switch between windows/tabs)
 

    // Test: Navigate to the shopping Google URL
    @Test
    public void testNavigateToGoogleShopping() {
        navigateToURL("https://shopping.google.com");

        // If a pop-up or new window is opened, switch and maximize it
     
        
        // You can add some assertions or verifications here to check if the page loaded properly
    }

    // Test: Maximize window
    @Test
    public void testMaximizeWindow() {
        maximizeWindow();
        // Optionally, you could add verifications to check if the window is maximized
    }

    
}
