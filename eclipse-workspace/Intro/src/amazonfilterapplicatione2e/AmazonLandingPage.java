package amazonfilterapplicatione2e;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonLandingPage extends BasePage{

	private static final Logger log = LoggerUtility.getLogger(AmazonLandingPage.class);
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBarLandingPage;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement submitSearchButton;
	
	
	public void givingInputWithinSearchBar(String input) {
		searchBarLandingPage.click();
		searchBarLandingPage.sendKeys(input);
		log.info("Giving input within the search bar");
	}
	
	
	public void selectingFromRecommendations() {
		searchBarLandingPage.sendKeys(Keys.DOWN);
		searchBarLandingPage.sendKeys(Keys.DOWN);	
		log.info("Selecting option from recommdendations");
	}
	
	
	public void clickingOnSubmitSearchButton() {
		submitSearchButton.click();
		log.info("Clicking on the submit button beside the search bar");
	}
	
	public void openingLandingPage() {
		driver.get("https://www.amazon.in/");
		log.info("Opening amazon Landing page");
	}
	
	
}
