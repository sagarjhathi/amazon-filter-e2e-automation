package googleaggregator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleShoppingLandingPage {

	WebDriver driver;
	@FindBy(xpath="//input[@placeholder='What are you looking for?']")
	WebElement searchBar;
		
	
	public GoogleShoppingLandingPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	public  void clickOnSearchBarLandingPage() {
		searchBar.click();
    }

    public void inputWithInTheSearchBar(String input) {
    	searchBar.sendKeys(input);
    }

    public void selectFromRecommendations(WebDriver driver) {
        searchBar.sendKeys(Keys.DOWN, Keys.ENTER);
    }
    
    
}
