package googleaggregatorpomupdated;

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
	     // Click the element
	    viewMoreDetails.click();
	}
}
