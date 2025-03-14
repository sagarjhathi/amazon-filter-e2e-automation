package googleaggregatorPOM;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import googleaggregator.AllFunctions;
import googleaggregator.GenericHelper;

public class POMTesting {

	WebDriver driver;
	GlobalUtility globalUtility;
	ViewMoreDetailsPage viewMoreDetailsPage;
	GoogleShoppingProductPopup googleShoppingProductPopup;
	AmazonProductMainPage amazonProductMainPage;
	GenericHelper genericHelper;
	GoogleShoppingMainPage googleShoppingMainPage;
	GoogleShoppingLandingPage  	googleShoppingLandingPage;
	FactoryPattern factory;
	
	
	@BeforeClass
    public void setup() {
		this.globalUtility=new GlobalUtility(driver);
		this.genericHelper=new GenericHelper();
		this.driver=globalUtility.initDriver(genericHelper.userAgent);
		this.googleShoppingLandingPage=new GoogleShoppingLandingPage(driver);
		this.googleShoppingMainPage = new GoogleShoppingMainPage(driver, globalUtility);
    }
	
	
	@Test(priority = 1)
	public void navigatingToUrl() {
		globalUtility.navigateToURL(driver, genericHelper.url);
		System.out.println(1);
	}
	
	
	
	@Test(priority = 2)
	public void maximizingTheWindow() {
		globalUtility.maximizeWindow(driver);
		System.out.println(3);
	}
	
	@Test(priority = 3)
	public void applyingAllWaits() {
		globalUtility.setImplicitWait(driver, 10);
		globalUtility.setPageLoadOutTimeOut(driver, 20);
		System.out.println(2);
	}
	
	@Test(priority = 4)
	public void inputWithIntheSearchBar() {
		googleShoppingLandingPage.clickOnSearchBarLandingPage();
	    googleShoppingLandingPage.inputWithInTheSearchBar();
	    System.out.println(4);
	}
	
	@Test(priority = 5)
	public void selectingFromRecommendations() {
		googleShoppingLandingPage.selectFromRecommendations();
		System.out.println(5);
	}
	
	
	@Test(priority=6)
	public void scrollToSellerMenu() {
		googleShoppingMainPage.scrollToSellerLandingPage(driver);
	}
	
	
	@Test(priority=7)
	public void  clickingOnMorInSellerMenuLandingPage() {
		googleShoppingMainPage.clickOnMoreInSellerMenuLandingPage(driver);
	}
	
	
	@Test(priority=8)
	public void clickingOnAmazonFromSellerMenu() {
		googleShoppingMainPage.selectingAmazonFromSellerMenu(driver);
	}
	
	
	
	@Test(priority=9)
	public void applyingPriceFilterInGoogleMainPage() throws InterruptedException {
		googleShoppingMainPage.applyingPriceFilter(driver, googleShoppingMainPage.gettingAllProducts(driver));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
