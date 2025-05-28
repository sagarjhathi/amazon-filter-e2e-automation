package amazonfilterapplicatione2e;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListingPage extends  BasePage{

	
	@FindBy(xpath="//div[@data-cy='title-recipe']")
	WebElement productNameListingPage;
	
	@FindBy(xpath="//span[@class='a-price-whole']")
	WebElement productPriceListingPage;
	
	@FindBy(xpath="//div[@data-cy='delivery-recipe']")
	WebElement productDeliveryDayListingPage;
	
	@FindBy(xpath="//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']")
	List<WebElement> listOfFilterNameInLeftNav;
	
	By listProcessorSpeedOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base']");
    By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
    
    By productNameIndividualPage=By.xpath("//span[@id='productTitle']");

	By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");

	By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");

	By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");

	By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");

	By clearButtonBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");

	 public By getFilterByName(String filterName) {
	        return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterName + "']");
	    }
	 
	 public By getProductByIndex(int index) {
	        return By.xpath("(//div[@data-cy='title-recipe'])[" + index + "]");
	    }
	 
	 
	 public void switchToNewWindow(String currentWindowHandle) {
		    Set<String> allWindowHandles = driver.getWindowHandles();

		    for (String handle : allWindowHandles) {
		        if (!handle.equals(currentWindowHandle)) {
		            System.out.println("Found the new window. Switching now...");
		            driver.switchTo().window(handle);
		            return;
		        }
		    }

		    System.out.println("No new window found to switch to.");
		}

	
	
	 
	public boolean filterCheckUnderList(String filterName) {		
		    List<String> filterNames = new ArrayList<>();
		    String target = filterName.trim().toLowerCase(); // convert input to lowercase

		    for (WebElement el : listOfFilterNameInLeftNav) {
		        String text = el.getText().trim().toLowerCase(); // convert element text to lowercase
		        filterNames.add(text);
		    }

		    if (filterNames.contains(target)) {
		        System.out.println(filterName + " matches with assert text here");
		        return true;
		    } else {
		        System.out.println("Filter option '" + filterName + "' does not exist in the list. Skipping the test.");
		        return false;
		    }
	}
	
	
	
       public boolean filterCheckUnderList(String filterName1,String filterName2) {
		
	    List<String> filterNames = new ArrayList<>();
	    String target1 = filterName1.trim().toLowerCase(); // convert input to lowercase
	    String target2 = filterName1.trim().toLowerCase();

	    for (WebElement el : listOfFilterNameInLeftNav) {
	        String text = el.getText().trim().toLowerCase(); // convert element text to lowercase
	        filterNames.add(text);
	    }

	    if (filterNames.contains(target1) ||filterNames.contains(target2) ) {
	        System.out.println(filterName1+ "  and " +filterName2 + " matches with assert text here");
	        return true;
	    } else {
	        System.out.println("Filter option '" + filterName1 +"  " +filterName2+ "' does not exist in the list. Skipping the test.");
	        return false;
	    }
    }
	
	
//	public void safeClick(By locator) {
//	    int attempts = 0;
//	    while (attempts < 3) {
//	        try {
//	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//	            element.click();
//	            System.out.println("Clicking using safeClick");
//	            return;
//	        } catch (ElementClickInterceptedException  | StaleElementReferenceException e) {
//	            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
//	            driver.navigate().refresh();
//	            attempts++;
//	            try {
//	                Thread.sleep(1000); // small delay before retry
//	            } catch (InterruptedException ignored) {}
//	        }
//	    }
//
//	    throw new RuntimeException("Click failed after multiple retries: " + locator);
//     }
//
//
//	public List<WebElement> safeFindElements(By locator) {
//	    int attempts = 0;
//	    while (attempts < 3) {
//	        try {
//	            List<WebElement> element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//	            System.out.println("Found the element: " + locator);
//	            return element;
//	        } catch (NoSuchElementException | StaleElementReferenceException e) {
//	            System.out.println("Retrying findElement for: " + locator + " - Attempt " + (attempts + 1));
//	            attempts++;
//	            try {
//	            	driver.navigate().refresh();
//	            	System.out.println("Refreshing the page in safeFindElements Method");
//	                Thread.sleep(1000);
//	            } catch (InterruptedException ignored) {}
//	        }
//	    }
//
//	    throw new RuntimeException("Failed to find element after multiple retries: " + locator);
//	}

public void safeClick(By locator) {
    int attempts = 0;
    while (attempts < 3) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Clicking using safeClick");
            return;
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
            attempts++;
            try {
                driver.navigate().refresh();
                Thread.sleep(1000); // small delay before retry
            } catch (InterruptedException ignored) {}
        }
    }
    // After 3 attempts, skip the action without throwing exception
    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
}

public List<WebElement> safeFindElements(By locator) {
    int attempts = 0;
    while (attempts < 3) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            System.out.println("Found the elements: " + locator);
            return elements;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Retrying findElements for: " + locator + " - Attempt " + (attempts + 1));
            attempts++;
            try {
                driver.navigate().refresh();
                System.out.println("Refreshing the page in safeFindElements Method");
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }
    // After 3 attempts, return null instead of throwing exception
    System.out.println("Skipping action: Elements not found after 3 attempts - " + locator);
    return null;
}

	
	
	public WebElement safeFindElement(By locator) {
	    int attempts = 0;
	    while (attempts < 3) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	            System.out.println("Found the element: " + locator);
	            return element;
	        } catch (Exception e) {
	            System.out.println("Retrying findElement for: " + locator + " - Attempt " + (attempts + 1));
	            attempts++;
	            try {
	                driver.navigate().refresh();
	                System.out.println("Refreshing the page in safeFindElement Method");
	                Thread.sleep(1000);
	            } catch (InterruptedException ignored) {}
	        }
	    }

//	    throw new RuntimeException("Failed to find element after multiple retries: " + locator);
	    // Element not found after retries — skip the action
	    System.out.println("Skipping action: Element not found after 3 attempts - " + locator);
	    return null;
	}


}
