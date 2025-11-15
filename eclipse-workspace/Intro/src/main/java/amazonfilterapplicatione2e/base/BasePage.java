package main.java.amazonfilterapplicatione2e.base;
import java.time.Duration;     
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.driverManager.DriverManager;
import main.java.amazonfilterapplicatione2e.utilities.WaitUtility;




public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WaitUtility waitUtil;
	public BasePage() {
		
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInt("globalWaitTimeExtendingBasePage", 20)));
        this.waitUtil = new WaitUtility(this.driver);
        
        
    }	
}
