package googleaggregatorPOM;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	
	    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static WebDriver setDriver(WebDriver webDriver) {
	      driver.set(webDriver);
		return webDriver;
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
	}


