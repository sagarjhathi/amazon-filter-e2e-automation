package googleaggregator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonProductMainPage  {

	WebDriver driver;
	
	
	@FindBy(xpath="//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']")
	WebElement amazonProductPrice;
	
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement amazonProductName;
	
	
	public AmazonProductMainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	

	public String getAmazonProductName() {
		return amazonProductName.getText();	
	}
	
	public String getAmazonProductPrice() {
		
		      String amazonPriceFromAmzon="";
	    
        try{
 	          amazonPriceFromAmzon=amazonProductPrice.getText();
			}catch(Exception e) {
				System.out.println("The amazon price is not available in Ui");
				driver.quit();
			}
        
             System.out.println(amazonPriceFromAmzon+"     printing the amazonPrice before processing ");
    
             return amazonPriceFromAmzon;
	}
	
	
	  public String getAmazonWindowHandle() {
	    	return driver.getWindowHandle();
	    }
	
}
