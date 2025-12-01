package main.java.amazonfilterapplicatione2e.pages;
import java.time.Duration;   
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtil;


public class AmazonLandingPage extends BasePage{

	private  final Logger log = LoggerUtility.getLogger(AmazonLandingPage.class);

	SafeActions safeAct=new SafeActions();

	@FindBy(xpath="//input[@placeholder='Search Amazon.in']")
	WebElement searchBarLandingPage;
	
	By searchBarLandingPageBy=By.xpath("//input[@placeholder='Search Amazon.in']");
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement submitSearchButton;
	
	
	By submitSearchButtonBy=By.xpath("//input[@id='nav-search-submit-button']");
	
	public void givingInputWithinSearchBar(String input) throws InterruptedException {
		searchBarLandingPage.click();
		searchBarLandingPage.sendKeys(input);
		log.info("[{}] Giving input within the search bar", ThreadContext.get("testName"));

	}
	
	
	
	public void selectingFromRecommendations() {	
		log.info("[{}] Selecting option from recommdendations", ThreadContext.get("testName"));
	}
	
	
	public void clickingOnSubmitSearchButton() throws InterruptedException{
		safeAct.safeClick(submitSearchButtonBy);
		log.info("[{}] Clicking on the submit in the search bar", ThreadContext.get("testName"));

	}
	
	public void openingLandingPage() {
		String url = ConfigManager.get("Url");
		driver.get(url);
		log.info("[{}] Opening amazon Landing page", ThreadContext.get("testName"));

	}
	
	
}
