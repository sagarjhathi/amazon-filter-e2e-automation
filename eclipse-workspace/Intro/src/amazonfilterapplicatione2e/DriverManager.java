package amazonfilterapplicatione2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver() {
		if(driver.get()==null) {
			driver.set(new ChromeDriver());
		}
	}
	
	public static void quitDriver() {
		if(driver.get()!=null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
