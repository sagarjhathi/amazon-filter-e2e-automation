package googleaggregatorPOM;

import org.openqa.selenium.WebDriver;

public class FactoryPattern {

	
	public WebDriver driver;
	
	public AmazonProductMainPage createAmazonProductMainPage() {
        return new AmazonProductMainPage(driver);
    }

    public FactoryPattern(WebDriver driver) {
        this.driver = driver;
    }

//    public GlobalUtility createGlobalUtility() {
//        return new GlobalUtility(driver);
//    }
//    
    public GoogleShoppingLandingPage createGoogleShoppingLandingPage() {
    	return new GoogleShoppingLandingPage(driver);
    }
    
//    
//    public GoogleShoppingMainPage createGoogleShoppingMainPage() {
//    	return new GoogleShoppingMainPage(driver);
//    }
    
    public GoogleShoppingProductPopup createGoogleShoppingProductPopup() {
        return new GoogleShoppingProductPopup(driver);
    }

    public ViewMoreDetailsPage createViewMoreDetailsPage() {
        return new ViewMoreDetailsPage(driver);
    }

   

    

    
	
}
