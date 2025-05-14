package orangehr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePageObject {
    
    // Use more robust locators
    private static final By CONTACT_SALES_BUTTON = By.xpath("(//button[@class='btn btn-ohrm btn-free-demo'])[2]");
    private static final By FREE_DEMO_BUTTON = By.xpath("(//button[@class='btn btn-ohrm btn-contact-sales'])[2]");
    
    // Keep the FindBy elements for convenience
    @FindBy(xpath = "(//button[@class='btn btn-ohrm btn-free-demo'])[2]")
    WebElement contactSalesButtonLandingPage;
    
    @FindBy(xpath = "(//button[@class='btn btn-ohrm btn-contact-sales'])[2]")
    WebElement freeDemoButtonLandingPage;
    
    String url = "https://www.orangehrm.com/";

    public LandingPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToLandingPage() {
        driver.get(url);
        waitForPageLoadComplete();
    }
    
    public void clickingOnSalesButtonLandingPage() {
        // Dynamic element finding with retry
        WebElement salesButton = findWithRetry(d -> d.findElement(CONTACT_SALES_BUTTON));
        clickWithRetry(salesButton, 3);
        waitForPageLoadComplete();
    }
    
    public void clickingOnFreeDemoButtonLandingPage() {
        // Dynamic element finding with retry
        WebElement demoButton = findWithRetry(d -> d.findElement(FREE_DEMO_BUTTON));
        clickWithRetry(demoButton, 3);
        waitForPageLoadComplete();
    }
}