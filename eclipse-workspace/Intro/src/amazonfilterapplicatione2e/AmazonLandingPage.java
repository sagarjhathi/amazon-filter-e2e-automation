package amazonfilterapplicatione2e;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonLandingPage extends BasePage{

	private  final Logger log = LoggerUtility.getLogger(AmazonLandingPage.class);

	SafeActions safeAct=new SafeActions();
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBarLandingPage;
	
	By searchBarLandingPageBy=By.xpath("//input[@id='twotabsearchtextbox']");
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement submitSearchButton;
	
	
	By submitSearchButtonBy=By.xpath("//input[@id='nav-search-submit-button']");
	
	public void givingInputWithinSearchBar(String input) throws InterruptedException {
		
		safeAct.safeClick(searchBarLandingPageBy);		
	//	searchBarLandingPage.click();
		safeAct.safeFindElement(searchBarLandingPageBy);
		searchBarLandingPage.sendKeys(input);
		log.info("[{}] Giving input within the search bar", ThreadContext.get("testName"));

	}
	
	
	public void selectingFromRecommendations() {	
		log.info("[{}] Selecting option from recommdendations", ThreadContext.get("testName"));
	}
	
	
	public void clickingOnSubmitSearchButton() {
		safeAct.safeClick(submitSearchButtonBy);
		//submitSearchButton.click();
		log.info("[{}] Clicking on the submit in the search bar", ThreadContext.get("testName"));

	}
	
	public void openingLandingPage() {
		driver.get("https://www.amazon.in/");
		log.info("[{}] Opening amazon Landing page", ThreadContext.get("testName"));

	}
	
	
}
