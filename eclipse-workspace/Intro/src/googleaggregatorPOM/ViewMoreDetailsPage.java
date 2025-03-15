package googleaggregatorPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewMoreDetailsPage {

    
	WebDriver driver;
	
	public ViewMoreDetailsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(xpath="//span[@role='heading']")
    WebElement googleProductName;
    
    @FindBy(xpath="(//span[@class='g9WBQb'])[1]")
    WebElement googleProductPrice;
    
    @FindBy(xpath="(//div[@class='Kl9jM UKKY9'])[1]")
    WebElement clickOnAmazonFromViewMoreDetails;
    
    public String  getGoogleProductName() {
    	return googleProductName.getText();
    }
    
    public String getGoogleProductPrice() {
    	
    	String googleViewMoreDetailsPrice="";
		try{
		googleViewMoreDetailsPrice=googleProductPrice.getText();
			
		}catch(Exception e) {
			System.out.println("The google  price is not available in Ui");
			//driver.quit();
		}
		
		System.out.println(googleViewMoreDetailsPrice+"     printing the google Price before processing ");
		return googleViewMoreDetailsPrice;
    }
    
    
    public void clickingOnAmazonFromViewMoreDetailsPage() {
    	
    	clickOnAmazonFromViewMoreDetails.click();
    	
    }
    
  
    
    
    
    
    
}
