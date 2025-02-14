package Objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePageIMDb {

	WebDriver driver;
	
	@FindBy(xpath="//label[@id='imdbHeader-navDrawerOpen']")
	WebElement menuButton;
	
	@FindBy(xpath="//span[@class='ipc-list-item__text' and text()='Most Popular Movies']")
	WebElement mostPopularMoviesButton;
	
	@FindBy(xpath="//a[@id='home_img_holder']")
	WebElement imdbLogo;
	
	
	
	
	
	
	
	public homePageIMDb(WebDriver driver){
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);		
	}
	
	public void goToHomePage() {
		driver.get("https://www.imdb.com/?ref_=nv_home");
	}
	
	
	public void clickOnTheMenuButton() {
		menuButton.click();
	}
	
	
	public void clickOnTheMostPopularMovieButton() throws InterruptedException {
		Thread.sleep(2000);
		mostPopularMoviesButton.click();
	}
	
	
	public boolean isHomePageLoaded() {
		return imdbLogo.isDisplayed();
	}
	
	
	
	
}
