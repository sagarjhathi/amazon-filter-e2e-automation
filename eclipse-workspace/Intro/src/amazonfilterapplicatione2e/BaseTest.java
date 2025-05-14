package amazonfilterapplicatione2e;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
 
	 protected WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        // Initialize WebDriver instance using DriverManager
	    	 DriverManager.initDriver(); 
	        driver = DriverManager.getDriver();
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Quit WebDriver instance after test
	        DriverManager.quitDriver();
	    }
	
	
	
}
