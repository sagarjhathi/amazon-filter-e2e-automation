package amazonfilterapplicatione2e;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonLandingPage extends BasePage{

	
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBarLandingPage;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement submitSearchButton;
	
	
	public void givingInputWithinSearchBar(String input) {
		searchBarLandingPage.click();
		searchBarLandingPage.sendKeys(input);
	}
	
	
	public void selectingFromRecommendations() {
		searchBarLandingPage.sendKeys(Keys.DOWN);
		searchBarLandingPage.sendKeys(Keys.DOWN);	
	}
	
	
	public void clickingOnSubmitSearchButton() {
		submitSearchButton.click();
	}
	
	public void openingLandingPage() {
		driver.get("https://www.amazon.in/");
	}
	
	
}
