package amazonfilterapplicatione2e;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import amazonfilterapplicatione2e.reporting.ExtentTestManager;

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
	
	By maxPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']");
	
	By minPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_lower-bound-slider']");
	
	By productPriceFromProductCards=By.xpath("//span[@class='a-price-whole']");
	
    By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
    
    By productNameIndividualPage=By.xpath("//span[@id='productTitle']");

	By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");

	By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");

	By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");

	By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");

	By clearButtonBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");
	
	By seeMoreButtonIndividualPage=By.xpath("//span[@class='a-expander-prompt' and text()='Show More']");
	
	private  final Logger log = LoggerUtility.getLogger(ProductListingPage.class);
//	private final Logger log = LoggerUtility.getLogger(getClass());

	
	    public By getfilterByTypeAndName(String filterName, String filterOption) {
	        //return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterName + "']");
			log.info("[{}] Within getfilterByTypeAndName method", ThreadContext.get("testName"));

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
    

	 
	 public By getProductByIndex(int index) {
			log.info("[{}] Within getProductByIndex method", ThreadContext.get("testName"));
	        return By.xpath("(//div[@data-cy='title-recipe'])[" + index + "]");
	    }
	 
	 
	 public void switchToNewWindow(String currentWindowHandle) {
			log.info("[{}] Within switchToNewWindow method", ThreadContext.get("testName"));

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
        	
        log.info("[{}] Within applyFilterAndValidateProducts method", ThreadContext.get("testName"));

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging

		for (int i = 0; i < filterOptions.size(); i++) {
			log.info("[{}] Within filterOptions loop within applyFilterAndValidateProducts", ThreadContext.get("testName"));

		List<WebElement> inloopParent=safeAct.safeFindElements(filterOptionsBy);
		if(i>inloopParent.size()-1) {
			log.info("[{}] Avoiding out of bounds issue by traversing only upto the inloop size within applyFilterAndValidateProducts", ThreadContext.get("testName"));

			System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
			return;
		}
		
		System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
		String str = inloopParent.get(i).getText().trim();			
		
	//	safeAct.safeClick(productPage.getfilterByTypeAndName(filterName,str));
		if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
		    System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
		    continue; // ⛔ Skip the rest of the current loop iteration
		}

		
		Thread.sleep(1000);
		String currentWindow=driver.getWindowHandle();
		System.out.println("Printing current window  "+ currentWindow);
		
		List<WebElement> productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
		System.out.println(productNameListingPage.get(0).getText()+"  printing the 1st product here before the loop");
		
		for(int p=1;p<=productNameListingPage.size()-1;p++) {	
			log.info("[{}] Within productNameListingPage loop within applyFilterAndValidateProducts", ThreadContext.get("testName"));

			Thread.sleep(3000);
			System.out.println("inside the loop and product name is  and index is "+p+"  "+productNameListingPage.get(p).getText());				
		//	safeAct.safeClick(productPage.getProductByIndex(p));
			
			try {
			    WebElement productElement = driver.findElement(productPage.getProductByIndex(p));
				log.info("[{}] Getting product by index  within productNameListingPage loop", ThreadContext.get("testName"));

			    // Simulate Ctrl+Click (Cmd+Click on Mac)
			    Actions actions = new Actions(driver);
			    actions
		            .keyDown(Keys.CONTROL) // Use Keys.COMMAND on Mac
			        .click(productElement)
			        .keyUp(Keys.CONTROL)
			        .build()
			        .perform();

			    System.out.println("Product clicked with Ctrl+Click to open in new tab.");
				log.info("[{}] Product clicked with Ctrl+Click to open in new tab  within productNameListingPage loop", ThreadContext.get("testName"));

			    Thread.sleep(2000); // Allow time for tab to open

			} catch (Exception e) {
				log.info("[{}] Failed to Ctrl+Click product index " + p+"  within productNameListingPage loop", ThreadContext.get("testName"));
			    System.out.println("Failed to Ctrl+Click product index " + p);
			    continue;
			}

			
			System.out.println("Clicked on the producct name new pop-up should open");
			log.info("[{}] Clicked on the producct name new pop-up should open  via productNameListingPage loop", ThreadContext.get("testName"));

			Thread.sleep(2000);		
			productPage.switchToNewWindow(currentWindow);
			log.info("[{}] Swithcing to the new window  within productNameListingPage loop", ThreadContext.get("testName"));

			safeAct.safeFindElement(productPage.productNameIndividualPage);
			
			safeAct.safeFindElement(productPage.productKeyFeatureBlock);
			
			safeAct.safeFindElement(productPage.aboutThisItemBulletPoint);
			
			safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage);
			
	        genericUtility.scrollByPixel(0, 700);
	    	
			try {					
		        genericUtility.smoothScrollToElement(seeMoreProductDetailsButtonIndividualPageBy);
		        Thread.sleep(500);
		        safeAct.safeClick(productPage.seeMoreProductDetailsButtonIndividualPageBy);
		        System.out.println("'See More Details' clicked.");
		    } catch (Exception e1) {
		    //System.out.println("Unable to click the show more details button and the filter and product is --?"+str+"   "+productNameListingPage.get(p).getText());
		    	driver.close();
		    	Thread.sleep(2000);
		    	genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
		    	continue; // ✅ move on to the next product
		    }
		    
			Thread.sleep(2000);				
			genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
		}
		safeAct.safeClick(productPage.clearButtonBy);
	 }
	}






public List<Map<String, Object>> applyFilterAndValidateProductsWithResult(By filterOptionsBy, String filterName) throws InterruptedException {

    log.info("[{}] Within applyFilterAndValidateProductsWithResult method", ThreadContext.get("testName"));

    SafeActions safeAct = new SafeActions();
    ProductListingPage productPage = new ProductListingPage();
    GenericUtility genericUtility = new GenericUtility();

    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
    genericUtility.printFilterNamesOnly(filterOptionsBy); // Optional for debugging

    List<Map<String, Object>> results = new ArrayList<>();

    for (int i = 0; i < filterOptions.size(); i++) {
		log.info("[{}] Within filterOptions loop within applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));

        List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
        if (i > inloopParent.size() - 1) {
			log.info("[{}] Avoiding out of bounds issue by traversing only upto the inloop size within applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));

            System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
            return results;
        }

        System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
        String str = inloopParent.get(i).getText().trim();

        if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
            System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
            continue; // ⛔ Skip the rest of the current loop iteration
        }
        
        
        String testName = ThreadContext.get("logFileName");
        String filterValue = str; // or dynamically picked
        int productIndex;
        
        
        Thread.sleep(1000);
        String currentWindow = driver.getWindowHandle();
        System.out.println("Printing current window  " + currentWindow);

        List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);
        for (int p = 1; p < productNameListingPage.size(); p++) {
        	 productIndex = p-1;
    
        	 ExtentTestManager.getTest().info("🖼 Captured screenshot for filter: " + filterValue + ", index: " + productIndex);

        	 String path = ScreenshotUtil.capture(testName, filterValue, productIndex);
        	 String caption = "📷 " + filterValue + " | Index: " + productIndex;

        	 ExtentTestManager.getTest()
        	     .addScreenCaptureFromPath(path, caption);


			log.info("[{}] Within productNameListingPage loop within applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));

            System.out.println("inside the loop and product name is " + productNameListingPage.get(p-1).getText());
            
            try {
                WebElement productElement = driver.findElement(productPage.getProductByIndex(p));
				log.info("[{}] Getting product by index  within productNameListingPage loop", ThreadContext.get("testName"));

                System.out.println(productElement.getText()+"Printing the name in try catch ");
                Actions actions = new Actions(driver);
                actions
                    .keyDown(Keys.CONTROL)
                    .click(productElement)
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();

                System.out.println("Product clicked with Ctrl+Click to open in new tab.");
				log.info("[{}] Product clicked with Ctrl+Click to open in new tab  within productNameListingPage loop", ThreadContext.get("testName"));

                Thread.sleep(2000);

            } catch (Exception e) {
				log.info("[{}] Failed to Ctrl+Click product index " + p+"  within productNameListingPage loop", ThreadContext.get("testName"));
                System.out.println("Failed to Ctrl+Click product index " + p);
                continue;
            }

            System.out.println("Clicked on the producct name new pop-up should open");
			log.info("[{}] Clicked on the producct name new pop-up should open  via productNameListingPage loop", ThreadContext.get("testName"));

            Thread.sleep(2000);
            productPage.switchToNewWindow(currentWindow);
			log.info("[{}] Swithcing to the new window  within productNameListingPage loop", ThreadContext.get("testName"));


            String name = safeAct.safeFindElement(productPage.productNameIndividualPage).getText();
            String keyFeatures = safeAct.safeFindElement(productPage.productKeyFeatureBlock).getText();
            String about = safeAct.safeFindElement(productPage.aboutThisItemBulletPoint).getText();
            String techDetails = safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage).getText();

			log.info("[{}] Extracting 'name' , 'keyFeatures', 'about' , 'techDetails' within productNameListingPage loop", ThreadContext.get("testName"));

            genericUtility.scrollByPixel(0, 700);

            try {
                genericUtility.smoothScrollToElement(seeMoreProductDetailsButtonIndividualPageBy);
                Thread.sleep(1000);
                safeAct.safeClick(productPage.seeMoreProductDetailsButtonIndividualPageBy);
    			log.info("[{}] Within try block for clicking see more deatils within productNameListingPage loop", ThreadContext.get("testName"));
                System.out.println("'See More Details' clicked.");
            } catch (Exception e1) {
                //driver.close();
              //  Thread.sleep(2000);
    			log.info("[{}] Within catch block for clicking 'see more deatils' within productNameListingPage loop", ThreadContext.get("testName"));
                genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);
    			log.info("[{}] Within catch block Cannot click 'see more details' hence going back to product listing", ThreadContext.get("testName"));
                continue;
            }

            Thread.sleep(2000);
            genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);
			log.info("[{}]  going back to product listing via closeCurrentWindowAndSwitchBack ", ThreadContext.get("testName"));


            // ✅ Only this block is new
            Map<String, Object> productResult = new HashMap<>();
            productResult.put("filter", str);
            productResult.put("title", name);
            productResult.put("keyFeatures", keyFeatures);
            productResult.put("about", about);
            productResult.put("techDetails", techDetails);
			log.info("[{}]  Added 'filter','title','keyFeatures', 'about', 'techDetails' into the Map -> productResult", ThreadContext.get("testName"));

            results.add(productResult);
			log.info("[{}]  Added 'productResult' Map into the 'results' i.e list of maps ", ThreadContext.get("testName"));

        }

        safeAct.safeClick(productPage.clearButtonBy);
		log.info("[{}]  Clickin clear button in the end  within productNameListingPage loop", ThreadContext.get("testName"));

    }
	log.info("[{}] returning the 'results' i.e list of Maps", ThreadContext.get("testName"));

    return results;
}


	
	
	public void validateDeliveryFilterOptions(By filterOptions) throws InterruptedException {
	    log.info("[{}] Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	   	 System.out.println("Within the Function validateDeliveryFilterOptions ");
	    //Creating ProductListingPage and SafeActions objects in-order to use the contents.
		ProductListingPage productPage = new ProductListingPage();
		SafeActions safeAct = new SafeActions();
	    safeAct.safeClick(filterOptions);
	    log.info("[" + ThreadContext.get("testName") + "] Clicked on " + filterOptions);


	   

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
	    log.info("[{}] Created hashset with required data to assert with Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));


	    
	    log.info("[" + ThreadContext.get("testName") + "] this is the set  " + allowedDateParts);

	    //Printing the Allowed Date to see the contents
	    System.out.println("Printing the Allowed date parts set : " + allowedDateParts);

	    
	    // Validate each element's delivery date
	    for (int i = 0; i < deliveryElements.size(); i++) {
		    log.info("[{}] Within deliveryElements loop iterating over the products  Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	        String text = deliveryElements.get(i).getText();
	        System.out.println("The text from delivery element --> "+text + "  and size of the list is " + deliveryElements.size() + " index no is " + i);

	        boolean found = allowedDateParts.stream().anyMatch(text::contains);
		    log.info("[{}] Checking if the allowedDateParts have assert text   Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	        Assert.assertTrue(found, "❌ None of the allowed date parts are present, printing the element text " + text+" index no is " + i);
	        System.out.println("✔ Valid delivery date found in: " + text+" index no is " + i);
	        System.out.println("-----------------------------------------------------------------");
	    }
	    
	    // Clear the delivery filter
	    safeAct.safeClick(productPage.clearButtonBy);
	    System.out.println("Clicking clear under validateDeliveryFilterOptions");
	    
	}
	
	
	
	public List<Object> validateDeliveryFilterOptionsWithResult(By filterOptions) throws InterruptedException {
	    log.info("[{}] Within validateDeliveryFilterOptionsWithResult method", ThreadContext.get("testName"));

	    ProductListingPage productPage = new ProductListingPage();
	    SafeActions safeAct = new SafeActions();
	    safeAct.safeClick(filterOptions);
	    log.info("[" + ThreadContext.get("testName") + "] Clicked on " + filterOptions);

	    Thread.sleep(2000);

	    List<WebElement> deliveryElements = safeAct.safeFindElements(productPage.listProductCardsBy);

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
	    String todayFormatted = LocalDate.now().format(formatter);
	    String tomorrowFormatted = LocalDate.now().plusDays(1).format(formatter);

	    Set<String> allowedDateParts = new HashSet<>();
	    Collections.addAll(allowedDateParts, todayFormatted.replace(",", "").split(" "));
	    Collections.addAll(allowedDateParts, tomorrowFormatted.replace(",", "").split(" "));
	    allowedDateParts.add("Today");
	    allowedDateParts.add("Tomorrow");

	    log.info("[{}] Created hashset with required data to assert with Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	    log.info("[" + ThreadContext.get("testName") + "] this is the set  " + allowedDateParts);

	    for (int i = 0; i < deliveryElements.size(); i++) {
		    log.info("[{}] Within deliveryElements loop iterating over the products  Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	        String text = deliveryElements.get(i).getText();
	        
	        boolean found = false;
	        for (String part : allowedDateParts) {
	            if (text.contains(part)) {
	    		    log.info("[{}] Checking if the allowedDateParts have the assert text   Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));
	            	System.out.println("Found the The text from delivery element --> " + text + 
	                        " | List size: " + deliveryElements.size() + 
	                        " | Index: " + i);
	                found = true;
	                break;
	            }
	        }
	        System.out.println("------------------------------------------------------");
	        if (!found) {
	            // ❌ Return failed: [false, elementText, index]
			    log.info("[{}] Assert text not found wihtin AllowedDateParts", ThreadContext.get("testName"));
	            return Arrays.asList(false, text, i);
	        }
	    }

	    log.info("[{}] Returning the list with boolean values   Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

	    // ✅ Return success: [true, "", -1]
	    return Arrays.asList(true, "All the things are valid no errors", -1);
	}

	
	
	
	public List<Object> validateDeliveryFilterOptionsUpdated(By filterOptions) throws InterruptedException {
		
	   	 System.out.println("Within the Function validateDeliveryFilterOptionsUpdate ");
	    //Creating ProductListingPage and SafeActions objects in-order to use the contents.
		ProductListingPage productPage = new ProductListingPage();
		SafeActions safeAct = new SafeActions();
	    safeAct.safeClick(filterOptions);
	    Thread.sleep(2000);
	   

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
	    
	    List<Object> returnAble=new ArrayList<>();
	    returnAble.add(deliveryElements);
	    returnAble.add(allowedDateParts);
	    return returnAble;
	    
	}
	
	
	
	
	public List<Object> validateGetItTodayFilterOptionUnderDeliveryDay(By filterOption) throws InterruptedException {
	    log.info("[{}] Within validateGetItTodayFilterOptionUnderDeliveryDay method", ThreadContext.get("testName"));

	     	 System.out.println("Within the Function validateGetItTodayFilterOptionUnderDeliveryDay ");
		     //Creating ProductListingPage and SafeActions objects in-order to use the contents.
		     ProductListingPage productPage = new ProductListingPage();
		     SafeActions safeAct = new SafeActions();
		     safeAct.safeClick(filterOption);
			 log.info("[" + ThreadContext.get("testName") + "] Clicked on " + filterOption);


		 
		     // Find product card elements
             List<WebElement> deliveryElements=safeAct.safeFindElements(productPage.listProductCardsBy);	
         
             // Validate each element's delivery date
             for(int j=0;j<deliveryElements.size();j++) {
         	 log.info("[{}] Within  deliveryElements loop within validateGetItTodayFilterOptionUnderDeliveryDay method", ThreadContext.get("testName"));

        	 String assertString=deliveryElements.get(j).getText();
 	         System.out.println("Found the text from delivery element --> " + assertString + 
                    " | List size: " + deliveryElements.size() + 
                    " | Index: " + j);
	    
	         boolean found = false;
	         if (assertString.contains("Today")) {
             found = true;
             }
	     //    Assert.assertTrue(found, "❌ None of the allowed date parts are present, printing the element text in: " + assertString+" index no is "+j);
	         if(!found) {
				 log.info("[{}] Assert text not found wihtin the product info hence returning the list of boolean values", ThreadContext.get("testName"));

	        	  return Arrays.asList(false, assertString, j);
	         }
		     System.out.println("-----------------------------------------------------------------");
             }

             // Clear the delivery filter
		     safeAct.safeClick(productPage.clearButtonBy);
		     System.out.println("Clicking clear under validateGetItTodayFilterOptionUnderDeliveryDay");
			 log.info("[{}] returning the list of boolean values as list", ThreadContext.get("testName"));

			return Arrays.asList(true, "All the things are valid no errors", -1);
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
	
	

	
	
	
	public List<Map<Object, Object>> applyFilterAndValidateBrandsFilterWithResult(By filterOptionsBy, String filterName) throws InterruptedException {
	    log.info("[{}] Within applyFilterAndValidateBrandsFilterWithResult method", ThreadContext.get("testName"));

	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    safeAct.safeFindElement(productPage.seeMoreButtonUnderBrandFilter);
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	    log.info("[{}] Scrolling to the 'More' Button under brands filter section", ThreadContext.get("testName"));

	    safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
	    log.info("[{}] Clicked the 'More' Button under brands filter section", ThreadContext.get("testName"));


	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy);

	    List<Map<Object, Object>> allResults = new ArrayList<>();

	    for (int i = 1; i < filterOptions.size(); i++) {
		    log.info("[{}] Within the filterOptions loop ", ThreadContext.get("testName"));

	        List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
	        if (i > inloopParent.size() - 1) {
			    log.info("[{}] Avoiding out of bouns by only iterating over the innerLoopParent ", ThreadContext.get("testName"));
	        	continue;
	        }

	        if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderBrandFilter)) {
	            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	            safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
			    log.info("[{}] Clicked the 'More' Button under brands filter section within the loop ", ThreadContext.get("testName"));

	            Thread.sleep(1000);
	        }

	        String str = inloopParent.get(i).getText().trim();
	        genericUtility.smoothScrollToElement(productPage.getfilterByTypeAndName(filterName, str));
	        safeAct.safeClick(productPage.getfilterByTypeAndName(filterName, str));
			log.info("[" + ThreadContext.get("testName") + "] Clicked on " + str+"Within the filterOptions loop");

	        Thread.sleep(1000);

	        List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);

	        boolean isValid = true;
	        List<String> mismatchDetails = new ArrayList<>();

	        for (int k = 0; k < productNameListingPage.size(); k++) {

	            String title = productNameListingPage.get(k).getText();
				log.info("[" + ThreadContext.get("testName") + "] Within productNameListingPage loop with product index "+k +"and filter name "+ str);

	            if (!title.toLowerCase().contains(str.toLowerCase())) {
	                isValid = false;
	                mismatchDetails.add("❌ Index: " + k + ", Brand: " + str + ", Title: '" + title + "'");
	            }
	        }

	        Map<Object, Object> result = new HashMap<>();
	        result.put("brand", str);
	        result.put("isValid", isValid);
	        result.put("mismatches", mismatchDetails);
			log.info("[" + ThreadContext.get("testName") + "] Creating 'result' Map for storing ,'brand','isValid','mismatches list' with map size ->"+result.size());

	        allResults.add(result);
			log.info("[" + ThreadContext.get("testName") + "] Adding 'result' with the data into 'allResults' i.e list of Map"+result.size());

	        try {
	        	if (genericUtility.isElementInViewport(productPage.clearButtonBy)) {
	              	 safeAct.safeClick(productPage.clearButtonBy);
	     			log.info("[" + ThreadContext.get("testName") + "] Clicked clear button for the filter ->"+str);

	        }
	        } catch (Exception e) {
	            driver.navigate().back();
     			log.info("[" + ThreadContext.get("testName") + "] Cannot click clear button hence navigating back to fresh page for filter->"+str);

	        }

	        if (i % 10 == 0 && i != 0) {
	            driver.navigate().refresh();
     			log.info("[" + ThreadContext.get("testName") + "] Refreshing the page after 10 products to avoid storage issues for filter->"+str);

	        }
	    }
			log.info("[" + ThreadContext.get("testName") + "] Returning the allResults List of Maps containing the data");

	    return allResults;
	}

	
	
	
	
	
	
	public void applyBrandFiltersAndValidateProductNames(By filterOptionsBy, String filterName) throws InterruptedException {
		
		
		System.out.println("Within the applyBrandFiltersAndValidateProductNames Function ");
	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	   
	    // Step 1: Click 'See more' under brands
	    safeAct.safeFindElement(productPage.seeMoreButtonUnderBrandFilter);
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	    safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
	    System.out.println("Clicking on More button under Brands Filter via applyBrandFiltersAndValidateProductNames function ");

	    
	    // Step 2: Get all brand filter options
	    List<WebElement> brandOptions = safeAct.safeFindElements(filterOptionsBy);
	    System.out.println("Printing the brand filter options ");
	    genericUtility.printFilterNamesOnly(filterOptionsBy);

	    
	    for (int i = 1; i < brandOptions.size(); i++) {
	    	
	    	
	        List<WebElement> inLoopBrandOptions = safeAct.safeFindElements(filterOptionsBy);
	   	        if (i > inLoopBrandOptions.size() - 1) {
	            System.out.println("Avoiding out-of-bounds issue.");
	            return;
	        }

//	        // Step 3: Click 'See more' in each iteration if present (as per original logic)
	        if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderBrandFilter)) {
	            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderBrandFilter);
	            safeAct.safeClick(productPage.seeMoreButtonUnderBrandFilter);
	            Thread.sleep(1000);
	        }
	      
	        
	       
	        // Step 4: Extract brand name
	        String brandName = inLoopBrandOptions.get(i).getText().trim();
	        System.out.println("Applying brand filter: " + brandName+"  index no is "+ i);

	        // Step 5: Scroll to brand and click
	        //     safeAct.safeClick(productPage.getfilterByTypeAndName(filterName, brandName));
	        
	        if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, brandName))) {
			    System.out.println("Filter click failed for: " + brandName + ". Skipping this filter option.");
			    continue; // ⛔ Skip the rest of the current loop iteration
			}
	        Thread.sleep(2000);
	        
	        // Step 6: Validate product titles contain the brand name
	        List<WebElement> productTitles = safeAct.safeFindElements(productPage.productNameListingPageBy);
	        int mismatchCount = 0;

	        for (int k = 0; k < productTitles.size(); k++) {
	            String productTitle = productTitles.get(k).getText();
	            if (!productTitle.toLowerCase().contains(brandName.toLowerCase())) {
	                System.out.println("Mismatch at index " + k + " → Product Title: " + productTitle +" expected keyword within was  =" +brandName);
	                mismatchCount++;
	            }
	        }

	        System.out.println("Mismatch count for brand '" + brandName + "': " + mismatchCount);
	        System.out.println("---------------------------------------------------------------------------");
	        // Step 7: Clear filter or go back
	        
	        try {
	            if (genericUtility.isElementInViewport(productPage.clearButtonBy)) {
		              	 safeAct.safeClick(productPage.clearButtonBy);
		        }
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
	
	
	
	
	
	
	public List<Map<String, Object>> applyOperatingSystemFilterAndValidateProductsWithResults(By filterOptionsBy, String filterName) throws InterruptedException {
	    log.info("[{}] Within applyOperatingSystemFilterAndValidateProductsWithResults method", ThreadContext.get("testName"));

		
	    SafeActions safeAct = new SafeActions();
	    ProductListingPage productPage = new ProductListingPage();
	    GenericUtility genericUtility = new GenericUtility();

	    List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	    genericUtility.printFilterNamesOnly(filterOptionsBy);
	    genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderOperatingSystemFilter);
	    safeAct.safeClick(productPage.seeMoreButtonUnderOperatingSystemFilter);
	    log.info("[{}] Clicked 'More' Button under the OS filter section", ThreadContext.get("testName"));


	    List<Map<String, Object>> allResults = new ArrayList<>();

	    
	    for (int i = 1; i < filterOptions.size(); i++) {
		    log.info("[{}] Within the FilterOptions loop", ThreadContext.get("testName"));

	        List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
	        if (i > inloopParent.size() - 1) continue;

	        if (genericUtility.isElementInViewport(productPage.seeMoreButtonUnderOperatingSystemFilter)) {
	            genericUtility.smoothScrollToElement(productPage.seeMoreButtonUnderOperatingSystemFilter);
	            safeAct.safeClick(productPage.seeMoreButtonUnderOperatingSystemFilter);
	            Thread.sleep(1000);
	        }

	        
	        String str = inloopParent.get(i).getText().trim();
	        if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
	            System.out.println("Filter click failed for: " + str);
	    	    log.info("[{}] Checking if The Filter is being applied else continuing to next filter , filter option ->"+str+"  ", ThreadContext.get("testName"));
	            continue;
	        }

	        Thread.sleep(1000);
	        String currentWindow = driver.getWindowHandle();
	        List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);

	        for (int p = 1; p < productNameListingPage.size(); p++) {
	    	    log.info("[{}] Within the productNameListingPage Loop for Filter Option->"+str, ThreadContext.get("testName"));

	            try {
	                WebElement productElement = driver.findElement(productPage.getProductByIndex(p));
	                new Actions(driver)
	                        .keyDown(Keys.CONTROL)
	                        .click(productElement)
	                        .keyUp(Keys.CONTROL)
	                        .build().perform();
		    	    log.info("[{}] Clicking on the Product , product inedx -> and filter applied->"+p+"  "+str, ThreadContext.get("testName"));

	                Thread.sleep(2000);
	                productPage.switchToNewWindow(currentWindow);

	                // Ensure product page loaded
	                safeAct.safeFindElement(productPage.productNameIndividualPage);
	                safeAct.safeFindElement(productPage.productKeyFeatureBlock);
	                safeAct.safeFindElement(productPage.aboutThisItemBulletPoint);
	                safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage);
	                genericUtility.scrollByPixel(0, 700);

	                try {
	                    WebElement seeMore = safeAct.safeFindElement(productPage.seeMoreProductDetailsButtonIndividualPageBy);
	                    ((JavascriptExecutor) driver).executeScript(
	                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMore);
	                    Thread.sleep(500);
	                    safeAct.safeClick(productPage.seeMoreProductDetailsButtonIndividualPageBy);
	                    System.out.println("'See More Details' clicked.");
	    	    	    log.info("[{}] CLicking on More deatils button , index and applied filter ->"+p+str, ThreadContext.get("testName"));

	                } catch (Exception e1) {
	                    System.out.println("⚠️ Failed to expand details for: " + str + ", index: " + p);
	    	    	    log.info("[{}] Cannot click the More details button , index and applied filter ->"+p+str, ThreadContext.get("testName"));

	                }

	                // ✅ Collect details here before closing the tab
	                String productTitle = safeAct.safeFindElement(productPage.productNameIndividualPage).getText();
	                String keyFeatureBlockText = safeAct.safeFindElement(productPage.productKeyFeatureBlock).getText();
	                String aboutBlockText = safeAct.safeFindElement(productPage.aboutThisItemBulletPoint).getText();
	                String techDetailsBlockText = safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage).getText();

    	    	    log.info("[{}] Extracting 'productTitle','keyFeatureBlockText','aboutBlockText' ,'techDetailsBlockText' , index and applied filter ->"+p+str, ThreadContext.get("testName"));

	                Map<String, Object> result = new HashMap<>();
	                result.put("filter", str);
	                result.put("title", productTitle);
	                result.put("keyFeatures", keyFeatureBlockText);
	                result.put("about", aboutBlockText);
	                result.put("techDetails", techDetailsBlockText);
    	    	    log.info("[{}] Adding the data to the 'result' Map, index and applied filter ->"+p+str, ThreadContext.get("testName"));

	                allResults.add(result);
    	    	    log.info("[{}] Adding the 'result' Map to 'allResults' i.e List of maps index and applied filter ->"+p+str, ThreadContext.get("testName"));


	                // ✅ Now safe to close
	                genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);

	            } catch (Exception e) {
	                System.out.println("❌ Failed to validate product at index " + p + " for filter: " + str);
	                continue;
	            }
	        }

	        
	        // Clear applied filter
	        try {
	            if (genericUtility.isElementInViewport(productPage.clearButtonBy)) {
	                safeAct.safeClick(productPage.clearButtonBy);
    	    	    log.info("[{}] Clearing the applied filter ,and applied filter ->"+str, ThreadContext.get("testName"));

	            }
	        } catch (Exception e) {
	            driver.navigate().back();
	    	    log.info("[{}] Cannot click clear Button,and applied filter ->"+str, ThreadContext.get("testName"));

	        }

	        if (i % 10 == 0 && i != 0) {
	            driver.navigate().refresh();
	    	    log.info("[{}] Refreshing the page to avoid storage issue ->"+str, ThreadContext.get("testName"));

	        }
	    }

	    log.info("[{}] Returning the Master data 'allResults'  ->", ThreadContext.get("testName"));

	    return allResults;
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
//			safeAct.safeClick(productPage.getProductByIndex(p));
			try {
			    WebElement productElement = driver.findElement(productPage.getProductByIndex(p));
			    
			    // Simulate Ctrl+Click (Cmd+Click on Mac)
			    Actions actions = new Actions(driver);
			    actions
			        .keyDown(Keys.CONTROL) // Use Keys.COMMAND on Mac
			        .click(productElement)
			        .keyUp(Keys.CONTROL)
			        .build()
			        .perform();

			    System.out.println("Product clicked with Ctrl+Click to open in new tab.");
			    Thread.sleep(2000); // Allow time for tab to open

			} catch (Exception e) {
			    System.out.println("Failed to Ctrl+Click product index " + p);
			    continue;
			}
			
			
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
		    	Thread.sleep(2000);
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
	

	
	
	public List<Map<String, Object>> applyPriceSliderAndValidateWithResult(List<Integer> minValues, List<Integer> maxValues) throws InterruptedException {

	    log.info("[{}] Within applyPriceSliderAndValidateWithResult method", ThreadContext.get("testName"));

	    if (minValues.size() != maxValues.size()) {
		    log.info("[{}] Checking if the Min value and Max value list size are same", ThreadContext.get("testName"));
	        throw new IllegalArgumentException("minValues and maxValues must be of same size");
	    }

	    
	    ProductListingPage productPage = new ProductListingPage();
	    SafeActions safeAct = new SafeActions();
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    List<Map<String, Object>> results = new ArrayList<>();

	    // Scroll to make slider visible
	    js.executeScript("window.scrollBy(0, 300);");
	    Thread.sleep(2000);

	    // Locate sliders	    
	    WebElement minSlider = safeAct.safeFindElement(productPage.priceMinSliderButton);
	    WebElement maxSlider = safeAct.safeFindElement(productPage.priceMaxSliderButton);

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
		    log.info("[{}] Applying the Min filter , Min filter value->"+min, ThreadContext.get("testName"));


	        // Set max slider
	        js.executeScript(
	            "arguments[0].value = arguments[1];" +
	            "arguments[0].dispatchEvent(new Event('input'));" +
	            "arguments[0].dispatchEvent(new Event('change'));",
	            maxSlider, String.valueOf(max)
	        );
		    log.info("[{}] Applying the Max filter , Max filter value->"+max, ThreadContext.get("testName"));


	        Thread.sleep(1000);
	        // Click 'Go' / Apply
	        safeAct.safeFindElement(productPage.priceSliderSubmitButton);
		    log.info("[{}] Hitting the submit button"+max, ThreadContext.get("testName"));

	        Thread.sleep(2000);

	        // Extract the applied max filter text and product prices
	        String maxPriceApplied = safeAct.safeFindElement(productPage.maxPriceFilterApplied).getText();
	        String minPriceApplied = safeAct.safeFindElement(productPage.minPriceFilterApplied).getText();
	        System.out.println("Max price ="+maxPriceApplied+"  "+"Min price ="+minPriceApplied);
	        List<WebElement> prices = safeAct.safeFindElements(productPage.productPriceFromProductCards);

	        List<String> mismatches = new ArrayList<>();
	        boolean isValid = true;

	        for (int j = 0; j < prices.size(); j++) {
	            String productPrice = prices.get(j).getText();
	            productPrice = productPrice.replaceAll("[^\\d]", "");
	            maxPriceApplied = maxPriceApplied.replaceAll("[^\\d]", "");
	            minPriceApplied = minPriceApplied.replaceAll("[^\\d]", "");

	            int productPriceInt = Integer.parseInt(productPrice);
	            int maxPriceFilterAppliedInt = Integer.parseInt(maxPriceApplied);
	            int minPriceFilterAppliedInt = Integer.parseInt(minPriceApplied);

	            
	            boolean bool = true;
	            if (productPriceInt <= maxPriceFilterAppliedInt) {
	    		    log.info("[{}] Checking if the product price is <= then the max filter applied, Product price and Max applied ->"+productPriceInt+"  "+max, ThreadContext.get("testName"));

	                System.out.println("Product price is within limits --> product index and applied filter and product price is " 
	                    + j + "  " + maxPriceApplied + "  " + productPrice);
	            } else {
	    		    log.info("[{}] Product price is more then the applied filter, Product price and Max applied ->"+productPriceInt+"  "+max, ThreadContext.get("testName"));

	                String errorMessage = "Product price is above limit --> product index->" + j 
	                    + " and applied Max filter->" + maxPriceApplied 
	                    + "  and  applied Min filter->" + minPriceApplied 
	                    + " Actual product price -> " + productPrice;
	                bool = false;
	                System.out.println(errorMessage);
	                mismatches.add(errorMessage);
	                isValid = false;
	            }
	        }

	        Map<String, Object> result = new HashMap<>();
	        result.put("min", min);
	        result.put("max", max);
	        result.put("isValid", isValid);
	        result.put("mismatches", mismatches);
		    log.info("[{}] Adding the data 'min','max','isValid' ,'mismatches' to 'result' Map->"+max, ThreadContext.get("testName"));

	        results.add(result);
		    log.info("[{}] Adding the 'results'  Map to the master 'allResults' i.e is List of maps->"+max, ThreadContext.get("testName"));

	        Thread.sleep(2000); // Small wait after each set
	    }
	    log.info("[{}] Returning the data here in the end", ThreadContext.get("testName"));

	    return results;
	}

	

	
  public void refreshIfServiceUnavailable() {
	    String pageSource = driver.getPageSource().toLowerCase();
	    String title = driver.getTitle().toLowerCase();

	    if (pageSource.contains("service unavailable") || 
	        pageSource.contains("it's rush hour") || 
	        title.contains("service unavailable") || 
	        title.contains("oops")) {
	        
	        System.out.println("Detected error page. Refreshing...");
	        driver.navigate().refresh();
	    }
	}

}