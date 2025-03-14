package googleaggregatorPOM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		
		String productName = "";
	     try {
	         productName = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
	     } catch (Exception e) {
	         System.out.println("Product name not found on Amazon.");
	         // You can leave productName empty or handle it differently
	     }
	     
	     System.out.println(productName+"             printing product name hereeeeeeeeeeee");
	     return productName;
	}
	
	public String getAmazonProductPrice() {
		
		 String amazonPriceFromAmzon="";
	       
	       //Extracting the price from amazon here while handling the case where by any chance the price is not visible on Ui 
	       try {
	           WebDriverWait checkOne = new WebDriverWait(driver, Duration.ofSeconds(10));
	           WebElement amazonPrice = checkOne.until(ExpectedConditions.visibilityOf(amazonProductPrice));
	           
	           amazonPriceFromAmzon= amazonPrice.getText();
	           
	           if(!amazonPrice.isDisplayed()) {
	               System.out.println("The price is not visible on the UI.");
	           }

	       } catch (Exception e) {
	           System.out.println("product price not found or not visible within the time limit.");
	           // Optionally, add code to skip this step or proceed with the next actions
	       }
		      
             return amazonPriceFromAmzon;
	}
	
	
	  public String getAmazonWindowHandle() {
	    	return driver.getWindowHandle();
	    }
	
}
