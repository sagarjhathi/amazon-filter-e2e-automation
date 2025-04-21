package orangehr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import googleaggregatorpomupdated.DriverManager;

public class LandingPage {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
        this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	String url="https://www.orangehrm.com/";
	
	@FindBy(xpath="(//button[@class='btn btn-ohrm btn-free-demo'])[2]")
	WebElement contactSalesButtonLandingPage;
	
	@FindBy(xpath="(//button[@class='btn btn-ohrm btn-contact-sales'])[2]")
	WebElement freeDemoButtonLandingPage;
	
	
	
	public void clickingOnSalesButtonLandingPage() {
		contactSalesButtonLandingPage.click();
	}
	
	
	public void clickingOnFreeDemoButtonLandingPage() {
		freeDemoButtonLandingPage.click();
	}
	
	
	
	
	
	
}
