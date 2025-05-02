package googleaggregatorpomupdated;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleShoppingLandingPage {

	WebDriver driver;
	@FindBy(xpath="//textarea[@class='gLFyf']")
	WebElement searchBar;
		
	
	public GoogleShoppingLandingPage(WebDriver driver) {
		this.driver=driver;
        PageFactory.initElements(driver, this);
	}
	
	public  void clickOnSearchBarLandingPage() {
		searchBar.click();
    }

    public void inputWithInTheSearchBar() {
    	driver.findElement(By.xpath("//textarea[@class='gLFyf']")).sendKeys("Mobile");
    }

    public void selectFromRecommendations() {
        searchBar.sendKeys(Keys.DOWN, Keys.ENTER);
    }
    
    
}
