package Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Objects.homePageIMDb;

public class Tests {

	
	private WebDriver driver;
    private homePageIMDb home;
    
    private ThreadLocal<WebDriver> d = new ThreadLocal<>();
    
    @BeforeMethod
    public void setUp() {
        // Set up WebDriver (ensure you have the correct path to your WebDriver executable)
        driver = new ChromeDriver();
        home = new homePageIMDb(driver);
    }
    
    @Test
	 public void testGoToHomePage() {
	        // Go to home page and assert that it loads correctly
	        home.goToHomePage();
	      Assert.assertEquals(driver.getCurrentUrl(), "https://www.imdb.com/?ref_=nv_home");
	      driver.close();
	    }
	 
    @Test
    public void testClickOnMenuButton() {
        // Go to the home page and click on the menu button
        home.goToHomePage();
        home.clickOnTheMenuButton();
        driver.close();
        
        // Check if the menu is visible or some other indication that the menu is opened
        
    }
    
    @Test
    public void testClickOnMostPopularMovieButton() throws InterruptedException {
        // Go to the home page, click the menu, and then click on the most popular movie button
        home.goToHomePage();
        home.clickOnTheMenuButton();
        home.clickOnTheMostPopularMovieButton();
        driver.close();

        // Check if a specific element related to the popular movie is displayed
        
    }
    
   

}
