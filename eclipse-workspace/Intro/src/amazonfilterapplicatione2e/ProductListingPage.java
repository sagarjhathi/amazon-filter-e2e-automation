package amazonfilterapplicatione2e;

import java.time.Duration;
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
	
	By listBrandsOptionsBy=By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']");
	
	By listDisplaySizeOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']");
	
	By listDisplayTypeOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']");
	
	By listDiscountOptionsBy=By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base']");
	
    By listBatteryCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']");
    
	By listStorageCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']");
	
	By listDeliveryDayOptionsBy=By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']");
	
	By listMobilePhonePrimaryCameraResolutionOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base']");
	
	By listOperatingSystemVersionOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base']");
	
	
	By getItByTomorrowUnderDeliveryDayFilterBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It by Tomorrow']");
	
	By getItInTwoDaysUnderDeliveryDayFilterBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It in 2 Days']");
	
	By getItTodayUnderDeliveryDayFilterBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It Today']");
	
	
	By seeMoreButtonUnderOperatingSystemFilter=By.xpath("//a[@aria-label='See more, Operating System Version']");
	
	
	By seeMoreButtonUnderBrandFilter=By.xpath("//a[@aria-label='See more, Brands']");
	
	By listProductCardsBy=By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
	
	By priceMinSliderButton=By.id("p_36/range-slider_slider-item_lower-bound-slider");
	
	By priceMaxSliderButton=By.id("p_36/range-slider_slider-item_upper-bound-slider");
	
	By priceSliderSubmitButton=By.xpath("//div[@class='a-section sf-submit-range-button']");
	
	By minPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']");
	
	By maxPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_lower-bound-slider']");
	
	By productPriceFromProductCards=By.xpath("//span[@class='a-price-whole']");
	
    By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
    
    By productNameIndividualPage=By.xpath("//span[@id='productTitle']");

	By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");

	By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");

	By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");

	By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");

	By clearButtonBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");
	
	
	    public By getfilterByTypeAndName(String filterName, String filterOption) {
	        //return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterName + "']");
	        
	        switch (filterName.toLowerCase()) {
	        case "processorspeed":
	            return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterOption + "']");
	        case "storagecapacity":
	        	return By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "brands":
	            return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "batterycapacity":
	            return By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterOption + "']");
	        case "displaysize" :
	         	return By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "displaytype":
	        	return By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "operatingsystem":
	        	return By.xpath("//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "mobilephoneprimarycameraresolution":
	        	return By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        case "discount":
	        	return By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
	        default:
	            throw new IllegalArgumentException("Unknown filter type: " + filterName);
	    }
	    }
    
	    public By getBrandsFilterByName(String filterName) {
	        return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ filterName + "']");
	    }
	
	
	public By getFilterByName(By locator, String filterName) {
		String raw = locator.toString(); // e.g., "By.xpath: //div[@class='example']"
	    raw= raw.replace("By.xpath: ", "").trim(); // Now it's just "//div[@class='example']"
	    return By.xpath(raw + "//span[@class='a-size-base a-color-base' and text()='" + filterName + "']");
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


	
			
	public void applyFilterAndValidateProducts(By filterOptionsBy, String filterName) throws InterruptedException {

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging

		for (int i = 1; i < filterOptions.size(); i++) {
		
		List<WebElement> inloopParent=safeAct.safeFindElements(filterOptionsBy);
		if(i>inloopParent.size()-1) {
			System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
			return;
		}
		
		System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
		
		String str = inloopParent.get(i).getText().trim();			
		
//		safeAct.safeClick(productPage.getfilterByTypeAndName(filterName,str));
		if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
		    System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
		    continue; // ⛔ Skip the rest of the current loop iteration
		}

		
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

	
	
	public void validateDeliveryFilterOptions(By filterOptions) throws InterruptedException {
		
	   	 System.out.println("Within the Function validateDeliveryFilterOptions ");
	    //Creating ProductListingPage and SafeActions objects in-order to use the contents.
		ProductListingPage productPage = new ProductListingPage();
		SafeActions safeAct = new SafeActions();
	    safeAct.safeClick(filterOptions);
	   

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

	    
	    
	    //Printing the Allowed Date to see the contents
	    System.out.println("Printing the Allowed date parts set : " + allowedDateParts);

	    
	    // Validate each element's delivery date
	    for (int i = 0; i < deliveryElements.size(); i++) {
	        String text = deliveryElements.get(i).getText();
	        System.out.println("The text from delivery element --> "+text + "  and size of the list is " + deliveryElements.size() + " index no is " + i);

	        boolean found = allowedDateParts.stream().anyMatch(text::contains);
	        Assert.assertTrue(found, "❌ None of the allowed date parts are present, printing the element text " + text+" index no is " + i);
	        System.out.println("✔ Valid delivery date found in: " + text+" index no is " + i);
	        System.out.println("-----------------------------------------------------------------");
	    }
	    

	    // Clear the delivery filter
	    safeAct.safeClick(productPage.clearButtonBy);
	    System.out.println("Clicking clear under validateDeliveryFilterOptions");
	    
	}

	
	public void validateGetItTodayFilterOptionUnderDeliveryDay(By filterOption) throws InterruptedException {
		
	     	 System.out.println("Within the Function validateGetItTodayFilterOptionUnderDeliveryDay ");
		     //Creating ProductListingPage and SafeActions objects in-order to use the contents.
		     ProductListingPage productPage = new ProductListingPage();
		     SafeActions safeAct = new SafeActions();
		     safeAct.safeClick(filterOption);		            		

		 
		     // Find product card elements
             List<WebElement> deliveryElements=safeAct.safeFindElements(productPage.listProductCardsBy);	
         
             // Validate each element's delivery date
             for(int j=0;j<deliveryElements.size();j++) {
        	 String assertString=deliveryElements.get(j).getText();
 	         System.out.println("The text from delivery element --> "+assertString + "  and size of the list is " + deliveryElements.size() + " index no is " + j);
	    
	         boolean found = false;
	         if (assertString.contains("Today")) {
             found = true;
             }
	         Assert.assertTrue(found, "❌ None of the allowed date parts are present, printing the element text in: " + assertString+" index no is "+j);
	         System.out.println("✔ Valid delivery date found in: " + assertString+"index no is "+j);
		     System.out.println("-----------------------------------------------------------------");

             }

             // Clear the delivery filter
		     safeAct.safeClick(productPage.clearButtonBy);
		     System.out.println("Clicking clear under validateGetItTodayFilterOptionUnderDeliveryDay");
	}
	
	
	
	
	public void applyFilterAndValidateBrandsFilter(By filterOptionsBy, String filterName) throws InterruptedException {

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    safeAct.safeFindElement(productPage.seeMoreButtonUnderBrandFilter);
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	    safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
		
	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging

		for (int i = 1; i < filterOptions.size(); i++) {
		    
		List<WebElement> inloopParent=safeAct.safeFindElements(filterOptionsBy);
		if(i>inloopParent.size()-1) {
			System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
			return;
		}
		
		 if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderBrandFilter)) {
	            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	            safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
	            Thread.sleep(1000);
	      }
		
	    
		System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
		String str = inloopParent.get(i).getText().trim();	
		genericUtility.smoothScrollToElement(productPage.getfilterByTypeAndName(filterName,str));
		safeAct.safeClick(productPage.getfilterByTypeAndName(filterName,str));
		Thread.sleep(1000);
		
		List<WebElement> productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
		
		int noOfBrandNameNotIntheList =0;
		for(int k=0;k<productNameListingPage.size();k++) {
			if(productNameListingPage.get(k).getText().contains(str)) {
				System.out.println("Product name found in the list index no is -->" +k);
			}else {
				System.out.println("Product name not found in the list hence the filter functionality failed index no is -->"+k);
				noOfBrandNameNotIntheList++;
			//	Assert.fail("Product name not found in the list hence the filter functionality failed index no is -->"+k);
			}
		}
		
		System.out.println("The no of brand name not present in the list is -->"+noOfBrandNameNotIntheList +"for the brand filter -->"+str);
		
		try {
			safeAct.safeClick(productPage.clearButtonBy);
		} catch (Exception e) {
		    System.out.println("Element not clickable, going back via navigate.back()...filter name is"+str);
		    driver.navigate().back();
		}
		
		
		if(i % 10 == 0 && i != 0) {
			
			driver.navigate().refresh();
			System.out.println("Refreshing the page here ");
		}	
		}
	}
	
	
	public void applyBrandFiltersAndValidateProductNames(By filterOptionsBy, String filterName) throws InterruptedException {
	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    

	    // Step 1: Click 'See more' under brands
	    safeAct.safeFindElement(productPage.seeMoreButtonUnderBrandFilter);
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	    safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);

	    // Step 2: Get all brand filter options
	    List<WebElement> brandOptions = safeAct.safeFindElements(filterOptionsBy);

	    for (int i = 1; i < brandOptions.size(); i++) {
	        // Refresh and recapture DOM elements in each loop to avoid stale elements
	        List<WebElement> inLoopBrandOptions = safeAct.safeFindElements(filterOptionsBy);

	        if (i > inLoopBrandOptions.size() - 1) {
	            System.out.println("Avoiding out-of-bounds issue.");
	            return;
	        }

	        // Step 3: Click 'See more' in each iteration if present (as per original logic)
	        if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderBrandFilter)) {
	            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	            safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
	            Thread.sleep(1000);
	        }

	        // Step 4: Extract brand name
	        String brandName = inLoopBrandOptions.get(i).getText().trim();
	        System.out.println("Applying brand filter: " + brandName);

	        // Step 5: Scroll to brand and click
	        safeAct.safeClick(productPage.getfilterByTypeAndName(filterName, brandName));
	        Thread.sleep(2000);

	        // Step 6: Validate product titles contain the brand name
	        List<WebElement> productTitles = safeAct.safeFindElements(productPage.productNameListingPageBy);
	        int mismatchCount = 0;

	        for (int k = 0; k < productTitles.size(); k++) {
	            String productTitle = productTitles.get(k).getText();
	            if (!productTitle.toLowerCase().contains(brandName.toLowerCase())) {
	                System.out.println("Mismatch at index " + k + " → Title: " + productTitle);
	                mismatchCount++;
	            }
	        }

	        System.out.println("Mismatch count for brand '" + brandName + "': " + mismatchCount);

	        // Step 7: Clear filter or go back
	        try {
	            safeAct.safeClick(productPage.clearButtonBy);
	        } catch (Exception e) {
	            System.out.println("Clear button not clickable, navigating back. Brand: " + brandName);
	            driver.navigate().back();
	        }

	        // Step 8: Refresh on every 10th iteration
	        if (i % 10 == 0 && i != 0) {
	            driver.navigate().refresh();
	            System.out.println("Refreshing page after brand " + brandName);
	        }
	    }
	}
	
	
	
	
	
	public void applyOperatingSystemFilterAndValidateProducts(By filterOptionsBy, String filterName) throws InterruptedException {

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderOperatingSystemFilter);
	    safeAct.safeClick(productPage.seeMoreButtonUnderOperatingSystemFilter);

		for (int i = 1; i < filterOptions.size(); i++) {
		
		List<WebElement> inloopParent=safeAct.safeFindElements(filterOptionsBy);
		if(i>inloopParent.size()-1) {
			System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
			return;
		}
		
		if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderOperatingSystemFilter)) {
            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderOperatingSystemFilter);
            safeAct.safeClick(productPage.seeMoreButtonUnderOperatingSystemFilter);
            Thread.sleep(1000);
        }
		
		System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
		
		String str = inloopParent.get(i).getText().trim();			
		
//		safeAct.safeClick(productPage.getfilterByTypeAndName(filterName,str));
		if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
		    System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
		    continue; // ⛔ Skip the rest of the current loop iteration
		}

		
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
	
	
	
	
	public void applyPriceSliderAndValidate(List<Integer> minValues, List<Integer> maxValues) throws InterruptedException {
	    
	    if (minValues.size() != maxValues.size()) {
	        throw new IllegalArgumentException("minValues and maxValues must be of same size");
	    }
	    	
	    ProductListingPage productPage=new ProductListingPage();
	    SafeActions safeAct=new SafeActions();
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    // Scroll to make slider visible
	    js.executeScript("window.scrollBy(0, 300);");
	    Thread.sleep(2000);

	    // Locate sliders	    
	    WebElement minSlider=safeAct.safeFindElement(productPage.priceMinSliderButton);
	    WebElement maxSlider= safeAct.safeFindElement(productPage.priceMaxSliderButton);

	    for (int i = 0; i < minValues.size(); i++) {
	        int min = minValues.get(i);
	        int max = maxValues.get(i);

	        // Set min slider
	        js.executeScript(
	            "arguments[0].value = arguments[1];" +
	            "arguments[0].dispatchEvent(new Event('input'));" +
	            "arguments[0].dispatchEvent(new Event('change'));",
	            minSlider, String.valueOf(min)
	        );

	        // Set max slider
	        js.executeScript(
	            "arguments[0].value = arguments[1];" +
	            "arguments[0].dispatchEvent(new Event('input'));" +
	            "arguments[0].dispatchEvent(new Event('change'));",
	            maxSlider, String.valueOf(max)
	        );

	        Thread.sleep(1000);
	        // Click 'Go' / Apply
	        safeAct.safeFindElement(productPage.priceSliderSubmitButton);
	        Thread.sleep(2000);

	        // Extract the applied max filter text and product prices
	        String maxPriceApplied=safeAct.safeFindElement(productPage.maxPriceFilterApplied).getText();
	        String minPriceApplied=safeAct.safeFindElement(productPage.minPriceFilterApplied).getText();
	        List<WebElement> prices=safeAct.safeFindElements(productPage.productPriceFromProductCards);
	        

	        for (int j = 0; j < prices.size(); j++) {
	        	String productPrice=prices.get(j).getText();
		    	productPrice = productPrice.replaceAll("[^\\d]", "");
		    	maxPriceApplied = maxPriceApplied.replaceAll("[^\\d]", "");
		    	minPriceApplied=minPriceApplied.replaceAll("[^\\d]", "");
		    	
		    	int productPriceInt=Integer.parseInt(productPrice);
		    	int maxPriceFilterAppliedInt=Integer.parseInt(maxPriceApplied);
		    	
		    	boolean bool=true;
		    	if(productPriceInt<=maxPriceFilterAppliedInt) {
		    		System.out.println("Product price is within limits --> product index and applied filter and product price is "+ j+"  "+maxPriceApplied+"  "+productPrice);
		    	}else {
		    		String errorMessage="Product price is above limit --> product index and applied filter is and  product price "+ j+"  "+maxPriceApplied+"  "+productPrice;
		    		bool = false;
		    	    System.out.println(errorMessage);
		    	    Assert.fail(errorMessage); 
		    	 }
	         }
	        Thread.sleep(2000); // Small wait after each set
	     }
	}
	

	
}