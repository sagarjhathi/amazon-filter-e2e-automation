//package googleaggregatorpomupdated;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class AmazonProductMainPage  {
//
//	WebDriver driver;
//	
//	@FindBy(xpath="//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']")
//	WebElement amazonProductPrice;
//	
//	@FindBy(xpath="//span[@id='productTitle']")
//	WebElement amazonProductName;
//	
//	
//	public AmazonProductMainPage(WebDriver driver) {
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//	}
//	 
//	
//
//	public String getAmazonProductName() {
//		
//		String productName = "";
//	     try {
//	         productName = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
//	     } catch (Exception e) {
//	         System.out.println("Product name not found on Amazon.");
//	         // You can leave productName empty or handle it differently
//	     }
//	     
//	     if(productName.trim().length()>0) {
//	    	 System.out.println(productName+"       Amazon Product name extracted from amazon main page");
//	     }
//	     else{
//	    	 System.out.println(productName+"       Unable to extract Product name from amazon main page");
//	     }
//	     
//	     return productName;
//	}
//	
//	public String getAmazonProductPrice() {
//		
//		 String amazonPriceFromAmzon="";
//	       
//	       //Extracting the price from amazon here while handling the case where by any chance the price is not visible on Ui 
//	       try {
//	           WebDriverWait checkOne = new WebDriverWait(driver, Duration.ofSeconds(10));
//	           WebElement amazonPrice = checkOne.until(ExpectedConditions.visibilityOf(amazonProductPrice));
//	           
//	           amazonPriceFromAmzon= amazonPrice.getText();
//	           
//	           if(!amazonPrice.isDisplayed()) {
//	               System.out.println("The price is not visible on the UI.");
//	           }
//
//	       } catch (Exception e) {
//	           System.out.println("product price not found or not visible within the time limit.");
//	           // Optionally, add code to skip this step or proceed with the next actions
//	       }
//		      
//             return amazonPriceFromAmzon;
//	}
//	
//	
//	  public String getAmazonWindowHandle() {
//	    	return driver.getWindowHandle();
//	    }
//	
//}


//Remove the commented code


package googleaggregatorpomupdated;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductMainPage {

    WebDriver driver;

    @FindBy(id = "productTitle")
    WebElement amazonProductName;

    @FindBy(xpath = "//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']")
    WebElement amazonProductPrice;

    public AmazonProductMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAmazonProductName() {
        try {	
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(amazonProductName));
            String name = amazonProductName.getText().trim();

            if (!name.isEmpty()) {
                System.out.println("✅ Amazon Product Name extracted: " + name);
                return name;
            } else {
                System.out.println("⚠️ Product name element found but text is empty.");
            }

        } catch (Exception e) {
            System.out.println("❌ Product name not found or not visible: " + e.getMessage());
        }

        return "";
    }

    public String getAmazonProductPrice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(amazonProductPrice));

            String price = amazonProductPrice.getText().trim();

            if (!price.isEmpty()) {
                System.out.println("✅ Amazon Product Price extracted: " + price);
                return price;
            } else {
                System.out.println("⚠️ Price element found but text is empty.");
            }

        } catch (Exception e) {
            System.out.println("❌ Product price not found or not visible: " + e.getMessage());
        }
        
        
        return "";
    }

    public String getAmazonWindowHandle() {
        return driver.getWindowHandle();
    }
}

