package orangehr;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {
    private WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        // Only initialize if not already initialized
        if (DriverManager.getDriver() == null) {
            driver = DriverManager.initDriver();
        } else {
            driver = DriverManager.getDriver();
        }
    }

    @AfterMethod
    public void tearDown() {
        // Only quit if this is the last test in this thread
        DriverManager.quitDriver();
    }
    
    @Test
    public void fillDemoFormTest() {
        try {
            // Navigate to landing page and ensure it's fully loaded
            LandingPage landingPage = new LandingPage(driver);
            landingPage.navigateToLandingPage();
            
            // Click the Free Demo button and wait for the next page
            landingPage.clickingOnFreeDemoButtonLandingPage();
            
            // Fill the demo form
            DemoPage demoPage = new DemoPage(driver);
            demoPage.enterFullName("Test Full Name");
            demoPage.enterPhoneNumber("1919191919");
            demoPage.enterBusinessEmail("test@gmail.com");
            demoPage.enterCompanyName("Test Company");
            demoPage.selectCountry();
            demoPage.selectNumberOfEmployees();
            demoPage.clickSubmitButton();
            
            // Verify the expected message
            String actualErrorMessage = demoPage.getErrorText();
            String expectedErrorMessage = "We Just Need a Few Details.";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch after form submission");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test
    public void fillSalesFormTest() {
        try {
            // Navigate to landing page and ensure it's fully loaded
            LandingPage landingPage = new LandingPage(driver);
            landingPage.navigateToLandingPage();
            
            // Click the Sales button and wait for the next page
            landingPage.clickingOnSalesButtonLandingPage();
            
            // Fill the sales form
            ContactSales contactsales = new ContactSales(driver);
            contactsales.enterFullName("Test contact sales");
            contactsales.enterPhoneNumber("987654321");
            contactsales.enterBusinessEmail("jane.smith@example.com");
            contactsales.selectCountry();
            contactsales.selectNumberOfEmployees();
            contactsales.enterJobTitle("Test Job");
            contactsales.enterYourMessage("Test");
            
            // Scroll down to ensure the submit button is visible
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,200);");
            
            // Submit the form and wait for completion
            contactsales.submitFormContactSales();
            
            // Verify the expected message
            String actualErrorMessage = contactsales.getErrorText();
            String expectedErrorMessage = "Unlock the Full Potential of OrangeHRM!";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch after submitting sales form");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}