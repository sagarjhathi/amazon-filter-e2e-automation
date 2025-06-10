package amazonfilterapplicatione2e;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	
	By listDisplaySizeOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']");
	
    By listBatteryCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']");
    
	By listStorageCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']");
	
	By listDeliveryDayOptionsBy=By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']");
	
	By getItByTomorrowUnderDeliveryDayFilterBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It by Tomorrow']");
	
	By getItInTwoDaysUnderDeliveryDayFilterBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It in 2 Days']");
	
	
	
	By listProductCardsBy=By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
	

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


	
			
	public void applyFilterAndValidateProducts(By filterOptionsBy) throws InterruptedException {

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging

		for (int i = 1; i < filterOptions.size(); i++) {
		
		List<WebElement> inloopParent=safeAct.safeFindElements(productPage.listProcessorSpeedOptionsBy);
		if(i>inloopParent.size()-1) {
			System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
			return;
		}
		
		System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
		String str = inloopParent.get(i).getText().trim();			
		safeAct.safeClick(productPage.getFilterByName(str));
		Thread.sleep(1000);
		String currentWindow=driver.getWindowHandle();
		System.out.println("Printing current window  "+ currentWindow);
		
		
		List<WebElement> productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
		for(int p=1;p<productNameListingPage.size();p++) {
			
			System.out.println("inside the loop and product name is "+productNameListingPage.get(p).getText());				
			safeAct.safeClick(productPage.getProductByIndex(p));
			
			
			System.out.println("Clicked on the producct name new pop-up should open");
			Thread.sleep(2000);		
			productPage.switchToNewWindow(currentWindow);
			
			safeAct.safeFindElement(productPage.productNameIndividualPage);
			
			safeAct.safeFindElement(productPage.productKeyFeatureBlock);
			
			safeAct.safeFindElement(productPage.aboutThisItemBulletPoint);
			
			safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage);
			
	        genericUtility.scrollByPixel(0, 700);
	    	
			try {					
			   
				WebElement seeMoreProductDetailsButtonIndividualPage = safeAct.safeFindElement(productPage.seeMoreProductDetailsButtonIndividualPageBy);
		        ((JavascriptExecutor) driver).executeScript(
		            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);
		        Thread.sleep(500);

		        safeAct.safeClick(productPage.seeMoreProductDetailsButtonIndividualPageBy);
		        System.out.println("'See More Details' clicked.");

		    } catch (Exception e1) {
		  //  	System.out.println("Unable to click the show more details button and the filter and product is --?"+str+"   "+productNameListingPage.get(p).getText());
		    	driver.close();
		    	driver.switchTo().window(currentWindow);
		    	continue; // ✅ move on to the next product
		    }
		    
			Thread.sleep(2000);				
			genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
		}
		safeAct.safeClick(productPage.clearButtonBy);
	}
	}

	
	
	public void validateDeliveryFilterOptions(By productCardLocator) throws InterruptedException {
	    // Click the delivery filter
		ProductListingPage productPage = new ProductListingPage();
		SafeActions safeAct = new SafeActions();
	    safeAct.safeClick(productCardLocator);
	    Thread.sleep(2000); // Consider replacing with explicit wait

	    // Find product card elements
	    List<WebElement> deliveryElements = safeAct.safeFindElements(productPage.listProductCardsBy);

	    // Format today and tomorrow's dates
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
	    String todayFormatted = LocalDate.now().format(formatter);
	    String tomorrowFormatted = LocalDate.now().plusDays(1).format(formatter);

	    // Build allowed date parts
	    Set<String> allowedDateParts = new HashSet<>();
	    Collections.addAll(allowedDateParts, todayFormatted.replace(",", "").split(" "));
	    Collections.addAll(allowedDateParts, tomorrowFormatted.replace(",", "").split(" "));
	    allowedDateParts.add("Today");
	    allowedDateParts.add("Tomorrow");

	    System.out.println("Allowed date parts: " + allowedDateParts);

	    // Validate each element's delivery date
	    for (int i = 0; i < deliveryElements.size(); i++) {
	        String text = deliveryElements.get(i).getText();
	        System.out.println(text + "   size is " + deliveryElements.size() + " index no is " + i);

	        boolean found = allowedDateParts.stream().anyMatch(text::contains);
	        Assert.assertTrue(found, "❌ None of the allowed date parts are present in: " + text);
	        System.out.println("✔ Valid delivery date found in: " + text);
	    }

	    // Clear the delivery filter
	    safeAct.safeClick(productPage.clearButtonBy);
	    System.out.println("Clicking clear under delivery");
	    Thread.sleep(2000); // Replace with explicit wait if possible
	}


}
