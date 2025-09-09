package amazonfilterapplicatione2e;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.opentelemetry.api.logs.Logger;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	WaitUtility waitUtil;
	public BasePage() {
		
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtil = new WaitUtility(this.driver);
        
        
    }	
}
