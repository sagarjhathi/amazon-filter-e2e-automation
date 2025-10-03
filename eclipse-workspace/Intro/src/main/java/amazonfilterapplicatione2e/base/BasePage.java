package main.java.amazonfilterapplicatione2e.base;


import java.time.Duration; 
 


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.amazonfilterapplicatione2e.*;
import io.opentelemetry.api.logs.Logger;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WaitUtility waitUtil;
	public BasePage() {
		
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtil = new WaitUtility(this.driver);
        
        
    }	
}
