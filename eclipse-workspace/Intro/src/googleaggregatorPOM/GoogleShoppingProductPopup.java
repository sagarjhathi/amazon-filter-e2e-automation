package googleaggregatorPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleShoppingProductPopup {

	 WebDriver driver;
	
	 
	 @FindBy(xpath="//a[text()='View product details']")
	 WebElement viewMoreDetails;
	 
	 public GoogleShoppingProductPopup(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	 
	 
	public void scrollAndClickViewMoreDetails() throws InterruptedException {
	   
	    
	    // Scroll the element into view
	    JavascriptExecutor jss = (JavascriptExecutor) driver;
	    jss.executeScript("arguments[0].scrollIntoView(true)", viewMoreDetails);
	    Thread.sleep(1000);  // Wait for scroll effect
	    
	    // Additional scroll to make sure the element is properly in view
	    jss.executeScript("window.scrollBy(0,-200);");
	    Thread.sleep(1000);  // Wait for the adjustment
	    
	    // Click the element
	    viewMoreDetails.click();
	}
}
