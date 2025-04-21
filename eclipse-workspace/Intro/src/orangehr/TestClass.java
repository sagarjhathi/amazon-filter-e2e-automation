package orangehr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass {

		WebDriver driver;
		
		@BeforeClass
		public void setup() {
			this.driver=new ChromeDriver();
			driver.get("https://www.orangehrm.com/");
			driver.manage().window().maximize();
		}
		
		
	    @Test
	    public void fillDemoFormTest() {
	    	
	        // Create an object for the DemoPage (POM class)
	    	LandingPage landingPage=new LandingPage(driver);
	    	landingPage.clickingOnFreeDemoButtonLandingPage();
	    	
	        DemoPage demoPage = new DemoPage(driver);

	        // Interact with the demo page elements using the POM methods
	        demoPage.enterFullName("Test Full Name");
	        demoPage.enterPhoneNumber("1919191919");
	        demoPage.enterBusinessEmail("test@gmail.com");
	        demoPage.enterCompanyName("Test Company");
	        demoPage.selectCountry();
	        demoPage.selectNumberOfEmployees();
	        demoPage.clickSubmitButton();
	        driver.navigate().refresh();

	        // You can add assertions here to verify the success
	        String actualErrorMessage = demoPage.getErrorText();
	        String expectedErrorMessage = "We Just Need a Few Details.";
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch after form submission");
	    }

	   
	    @Test
	    public void fillSalesFormTest() {
	        // Create an object for the ContactSales page (POM class)
	    	
	    	LandingPage landingPage=new LandingPage(driver);
	    	landingPage.clickingOnSalesButtonLandingPage();
	    	
	    	
	        ContactSales contactSales = new ContactSales(driver);

	        // Interact with the sales page elements using the POM methods
	        contactSales.enterFullName("Test contact sales");
	        contactSales.enterPhoneNumber("987654321");
	        contactSales.enterBusinessEmail("jane.smith@example.com");
	        contactSales.enterCompanyName("Sales Inc.");
	        contactSales.selectCountry();
	        contactSales.selectNumberOfEmployees();
	        contactSales.submitFormContactSales();
	        

	        // Add assertions to verify form submission
	        String actualErrorMessage = contactSales.getErrorText();
	        String expectedErrorMessage = "Unlock the Full Potential of OrangeHRM!";
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch after form submission");
	    }
	}

	
	
