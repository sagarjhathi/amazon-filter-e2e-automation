package main.java.amazonfilterapplicatione2e.pages;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtil;


public class ProductListingPage extends  BasePage{

	private  final Logger log = LoggerUtility.getLogger(ProductListingPage.class);
	
	@FindBy(xpath="//div[@data-cy='title-recipe']")
	WebElement productNameListingPage;

	@FindBy(xpath="//span[@class='a-price-whole']")
	WebElement productPriceListingPage;

	@FindBy(xpath="//div[@data-cy='delivery-recipe']")
	WebElement productDeliveryDayListingPage;
	
	@FindBy(xpath="//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']")
	public List<WebElement> listOfFilterNameInLeftNav;


	public By listProcessorSpeedOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Processor Speed']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listBrandsOptionsByNew = By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']/parent::div/following-sibling::div[@class='a-section a-spacing-medium sf-navigation-searchable-content']//span[@class='a-size-base a-color-base']");

	
	
	public By listBrandsOptionsByOld = By.xpath(
			"//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']"
			);
	
	public By listDisplaySizeOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Display Size']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listDisplayTypeOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Display Type']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	
	public By listDiscountOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Discount']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listBatteryCapacityOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Battery Capacity']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listStorageCapacityOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Internal Storage']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listDeliveryDayOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Delivery Day']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listMobilePhonePrimaryCameraResolutionOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Mobile Phone Primary Camera Resolution']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By listOperatingSystemVersionOptionsBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base']"
			);

	public By getItByTomorrowUnderDeliveryDayFilterBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Delivery Day']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='Get It by Tomorrow']"
			);

	public By getItInTwoDaysUnderDeliveryDayFilterBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Delivery Day']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='Get It in 2 Days']"
			);

	public By getItTodayUnderDeliveryDayFilterBy = By.xpath(
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Delivery Day']" +
					"/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='Get It Today']"
			);



	public By seeMoreButtonUnderOperatingSystemFilter=	By.xpath("//a[@aria-label='See more, Operating System']");

	public By seeMoreButtonUnderBrandFilter=By.xpath("//a[@aria-label='See more, Brands']");

	public By listProductCardsBy=By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");

	public By priceMinSliderButton=By.id("p_36/range-slider_slider-item_lower-bound-slider");

	public By priceMaxSliderButton=By.id("p_36/range-slider_slider-item_upper-bound-slider");
	
	public By priceSliderSubmitButton=By.xpath("//div[@class='a-section sf-submit-range-button']");
	
	public By maxPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']");
	
	public By minPriceFilterApplied=By.xpath("//label[@for='p_36/range-slider_slider-item_lower-bound-slider']");
	
	public By productPriceFromProductCards=By.xpath("//span[@class='a-price-whole']");
	
	public  By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
	
	public By productNameIndividualPage=By.xpath("//span[@id='productTitle']");
	
	public By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
	
	public By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");
	
	public By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");
	
	public 	By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");
	
	public By clearButtonBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");

	public By seeMoreButtonIndividualPage=By.xpath("//span[@class='a-expander-prompt' and text()='Show More']");
	
	public By reportAnIssue=By.xpath("//div[@id='CardInstanceCnrYlcUauBaBV5oi0X1UVw']");

	public By showMoreOnlyIndividualPage=By.xpath("//span[@class='a-expander-prompt' and text()='Show More']");

	

	

	
	public By getfilterByTypeAndName(String filterName, String filterOption) {
		log.info("[{}] Within getfilterByTypeAndName method", ThreadContext.get("testName"));

		switch (filterName.toLowerCase()) {
		case "processorspeed":
			return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + filterOption + "']");
		case "storagecapacity":
			return By.xpath("//ul[@id='filter-p_n_g-1003492455111']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "brands":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "batterycapacity":
			return By.xpath("//ul[@id='filter-p_n_g-101015098008111']//span[@class='a-size-base a-color-base' and text()='" + filterOption + "']");
		case "displaysize" :
			return By.xpath("//ul[@id='filter-p_n_g-1004194492091']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "displaytype":
			return By.xpath("//ul[@id='filter-p_n_g-101013595158111']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "operatingsystem":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");			
		case "mobilephoneprimarycameraresolution":
			return By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "discount":
			return By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "Operating System Version":
			return By.xpath("//ul[@id='filter-p_n_g-1003517064111']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "brandsOld":
			return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']");
			
		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
	}
	    
	
	
	
	    
	public By getAppliedfilterByTypeAndName(String filterName, String filterOption) {
		log.info("[{}] Within getAppliedfilterByTypeAndName method", ThreadContext.get("testName"));

		switch (filterName.toLowerCase()) {
		case "processorspeed":
			return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base a-text-bold' and text()='" + filterOption + "']");
		case "storagecapacity":
			return By.xpath("//ul[@id='filter-p_n_g-1003492455111']//span[@class='a-size-base a-color-base a-text-bold' and text()='"+ filterOption + "']");
		case "brands":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base'and text()='"+ filterOption + "']");
		case "batterycapacity":
			return By.xpath("//ul[@id='filter-p_n_g-101015098008111']//span[@class='a-size-base a-color-base a-text-bold' and text()='" + filterOption + "']");
		case "displaysize" :
			return By.xpath("//ul[@id='filter-p_n_g-1004194492091']//span[@class='a-size-base a-color-base a-text-bold' and text()='"+ filterOption + "']");
		case "displaytype":
			return By.xpath("//ul[@id='filter-p_n_g-101013595158111']//span[@class='a-size-base a-color-base a-text-bold' and text()='"+ filterOption + "']");
		case "operatingsystem":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
		case "mobilephoneprimarycameraresolution":
			return By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base a-text-bold' and text()='"+ filterOption + "']");
		case "discount":
			return By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base a-text-bold' and text()='"+ filterOption + "']");
		case "brandsold":
			return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']");
		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
	}
	    
	    
	    
	    
	public By getMoreButtonByFilterTypeAndName(String filterName) {
		log.info("[{}] Within getMoreButtonByFilterTypeAndName method", ThreadContext.get("testName"));

		switch (filterName.toLowerCase()) {
		case "brands":
			return By.xpath("//a[@aria-label='See more, Brands']");
		case "operatingsystem":
			return  By.xpath("//a[@aria-label='See more, Operating System']");

		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
	}
	    
	
	
	    
	public String getDeliveryDayFilterByName(String filterName) {
		log.info("[{}] Within getMoreButtonByFilterTypeAndName method", ThreadContext.get("testName"));

		switch (filterName) {
		case "verifyingGetItByTomorrowFilterFunctionality":
			return "Get It by Tomorrow";
		case "verifyingGetItIn2DaysFilterFunctionality":
			return  "Get It in 2 Days";
		case "verifyingGetItByTodayFilterFunctionality":
			return  "Get It By Today";
		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
	}
	    
	    
	
	
	
	
	public By getfilterHeaderByTypeAndName(String filterName) {
		log.info("[{}] Within getAppliedfilterByTypeAndName method", ThreadContext.get("testName"));

		switch (filterName.toLowerCase()) {
		case "processorspeed":
			return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']");
		case "storagecapacity":
			return By.xpath("//ul[@id='filter-p_n_g-1003492455111']");
		case "brands":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']");
		case "batterycapacity":
			return By.xpath("//ul[@id='filter-p_n_g-101015098008111']");
		case "displaysize" :
			return By.xpath("//div[@id='p_n_g-1004194492091-title']");
		case "displaytype":
			return By.xpath("//ul[@id='filter-p_n_g-101013595158111']");
		case "operatingsystem":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']");	
		case "mobilephoneprimarycameraresolution":
			return By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']");
		case "discount":
			return By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']");
		case "brandsold":
			return By.xpath("//ul[@id='filter-p_123']");
		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
	}
    

	 
	    
	    
	public By getProductByIndex(int index) {
		log.info("[{}] Within getProductByIndex method", ThreadContext.get("testName"));
		return By.xpath("(//div[@data-cy='title-recipe'])[" + index + "]");
	}
	 
	 
	
			
	public void applyFilterAndValidateProducts(By filterOptionsBy, String filterName) throws InterruptedException {

		log.info("[{}] Within applyFilterAndValidateProducts method", ThreadContext.get("testName"));

		SafeActions safeAct = new SafeActions();
		ProductListingPage productPage = new ProductListingPage();
		GenericUtility genericUtility = new GenericUtility();

		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		genericUtility.printFilterNamesOnly(filterOptionsBy); 

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

			if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
				System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
				continue; 
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

				try {
					waitUtil.waitUntilClickable(productPage.getProductByIndex(p), 10);		
					WebElement productElement = driver.findElement(productPage.getProductByIndex(p));
					log.info("[{}] Getting product by index  within productNameListingPage loop", ThreadContext.get("testName"));


					Actions actions = new Actions(driver);
					actions
					.keyDown(Keys.CONTROL) 
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
				genericUtility.switchToNewWindow(currentWindow);
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
					driver.close();
					Thread.sleep(2000);
					genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
					continue; 
				}

				Thread.sleep(2000);				
				genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
			}
			safeAct.safeClick(productPage.clearButtonBy);
		}
	}





	public List<Map<String, Object>> applyFilterAndValidateProductsWithResult(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {

		log.info("[{}] Within applyFilterAndValidateProductsWithResult method", ThreadContext.get("testName"));

		SafeActions safeAct = new SafeActions();
		GenericUtility genericUtility = new GenericUtility();

		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		genericUtility.printFilterNamesOnly(filterOptionsBy); 
		List<Map<String, Object>> results = new ArrayList<>();

		log.info("[{}] Within applyFilterAndValidateProductsWithResult filterOptions size is -> "+filterOptions.size(), ThreadContext.get("testName"));

		
		int filterOptionSize=filterOptions.size();
		boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
		if(runAll==false) {
			filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 4);
			System.out.println(filterOptionSize +" is the  filterOptionSize");
		}



		for (int filterOption = 0; filterOption <filterOptionSize; filterOption++) {

			log.info("[{}] Within filterOptions loop in applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));

			List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
			if (filterOption > inloopParent.size() - 1) {
				log.info("[{}] Limiting traversal to in-loop size to prevent IndexOutOfBoundsException in applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return results;
			}

			String filterValue = safeAct.safeGetFilterOptionText(filterOptionsBy, filterOption);

			if (!safeAct.safeClickBooleanWithScreenShot(getfilterByTypeAndName(filterName, filterValue),filterName,filterValue)) {
				System.out.println("Filter click failed for: " + filterValue + ". Skipping this filter option.");
				continue; 
			}


			
			Thread.sleep(1000);
			String currentWindow = driver.getWindowHandle();

			List<WebElement> productNameListingPage = safeAct.safeFindElements(productNameListingPageBy);
			int productNameListingPageSize=0;
			boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListing", false);
			if(runAllProducts==false) {
				productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", 5);
				System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
			}
			
			
	
			for (int productIndex = 1; productIndex <=productNameListingPageSize-1; productIndex++) {

			
				productNameListingPage=safeAct.safeFindElements(productNameListingPageBy);
				log.info("[{}] Within productNameListingPage loop in applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));
				System.out.println("inside the loop and product name is " + productNameListingPage.get(productIndex).getText());

				Map<String, Object> productResult = genericUtility.applyFilterOptionsAndFetchProductDetailsGlobal(productIndex, filterValue, currentWindow, safeAct);
				log.info("[{}]  Added 'filter','title','keyFeatures', 'about', 'techDetails' into the Map -> productResult", ThreadContext.get("testName"));

				results.add(productResult);
				log.info("[{}]  Added 'productResult' Map into the 'results' i.e list of maps ", ThreadContext.get("testName"));
				log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));

			}

			safeAct.safeClick(clearButtonBy);
			log.info("[{}] Clearing the filter value ->"+filterValue+"within productNameListingPage loop", ThreadContext.get("testName"));

			log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));
			log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));

		}
		log.info("[{}] returning the 'results' i.e list of Maps", ThreadContext.get("testName"));
		return results;
}


	
	
	
	public void validateDeliveryFilterOptions(By filterOptions) throws InterruptedException {
		log.info("[{}] Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));

		System.out.println("Within the Function validateDeliveryFilterOptions ");

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

			Assert.assertTrue(found, "â�Œ None of the allowed date parts are present, printing the element text " + text+" index no is " + i);
			System.out.println("âœ” Valid delivery date found in: " + text+" index no is " + i);
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


		String testName = ThreadContext.get("logFileName");
		Thread.sleep(2000);
		String filterOption=getDeliveryDayFilterByName(testName);
		ScreenshotUtil.capture(testName,filterOption);

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

				log.info("[{}] Assert text not found wihtin AllowedDateParts", ThreadContext.get("testName"));
				return Arrays.asList(false, text, i);
			}
		}

		log.info("[{}] Returning the list with boolean values   Within validateDeliveryFilterOptions method", ThreadContext.get("testName"));


		return Arrays.asList(true, "All the things are valid no errors", -1);
	}

	
			
	
	
	public List<Map<Object, Object>> applyFilterAndValidateBrandsFilterWithResult(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {
		log.info("[{}] Within applyFilterAndValidateBrandsFilterWithResult method", ThreadContext.get("testName"));

		SafeActions safeAct = new SafeActions();
		ProductListingPage productPage = new ProductListingPage();
		GenericUtility genericUtility = new GenericUtility();

		log.info("[{}] Scrolling to the 'More' Button under brands filter section", ThreadContext.get("testName"));
		
		genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderBrandFilter);


		log.info("[{}] Clicked the 'More' Button under brands filter section", ThreadContext.get("testName"));


		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		
		if(filterOptions==null) {
			filterOptions = safeAct.safeFindElements(listBrandsOptionsByOld);
			filterOptionsBy=listBrandsOptionsByOld;
			filterName="brandsold";
		}
	


		int filterOptionSize=filterOptions.size();

		boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
		if(runAll==false) {
			filterOptionSize=2;
		}

		List<Map<Object, Object>> allResults = new ArrayList<>();

		for (int i = 1; i <=filterOptionSize-1; i++) {
			log.info("[{}] Within the filterOptions loop ", ThreadContext.get("testName"));
			
			List<WebElement> inloopParent;
			if(filterOptions==null) {
				inloopParent = safeAct.safeFindElements(listBrandsOptionsByOld);
				}else {
				 inloopParent = safeAct.safeFindElements(listBrandsOptionsByNew);
				}
			
			if (i > inloopParent.size() - 1) {
				log.info("[{}] Avoiding out of bouns by only iterating over the innerLoopParent ", ThreadContext.get("testName"));
				continue;
			}


			
			genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderBrandFilter);


			if(filterOptions==null) {
				inloopParent = safeAct.safeFindElements(listBrandsOptionsByOld);
				}else {
				 inloopParent = safeAct.safeFindElements(listBrandsOptionsByNew);
				}
			
			String str = inloopParent.get(i).getText().trim();      
			if (!safeAct.safeClickBooleanWithScreenShot(productPage.getfilterByTypeAndName(filterName, str),filterName,str)) {
				System.out.println("Filter click failed for: " + str);
				log.info("[{}] Checking if The Filter is being applied else continuing to next filter , filter option ->"+str+"  ", ThreadContext.get("testName"));
				continue;
			}

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
					mismatchDetails.add("â�Œ Index: " + k + ", Brand: " + str + ", Title: '" + title + "'");
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

	
	
	
	
	public List<Map<String, Object>> applyOperatingSystemFilterAndValidateProductsWithResults(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {
		log.info("[{}] Within applyOperatingSystemFilterAndValidateProductsWithResults method", ThreadContext.get("testName"));


		SafeActions safeAct = new SafeActions();
		ProductListingPage productPage = new ProductListingPage();
		GenericUtility genericUtility = new GenericUtility();

		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
	//	genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderOperatingSystemFilter);
		
		
		List<Map<String, Object>> allResults = new ArrayList<>();
		log.info("[{}] Within OS fucntion , this is the filterOptions size ->"+filterOptions.size(), ThreadContext.get("testName"));


		int filterOptionSize=filterOptions.size();
		boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
		if(runAll==false) {
			filterOptionSize=2;
		}
		

		for (int i = 0; i <filterOptionSize; i++) {
			log.info("[{}] Within the FilterOptions loop", ThreadContext.get("testName"));

			List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
			if (i > inloopParent.size() - 1) {
				return allResults;
			}

		//	genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderOperatingSystemFilter);

			String str = safeAct.safeGetFilterOptionText(filterOptionsBy, i);
			
			Thread.sleep(1000);
			if (!safeAct.safeClickBooleanWithScreenShot(productPage.getfilterByTypeAndName(filterName, str),filterName,str)) {
				System.out.println("Filter click failed for: " + str);
				log.info("[{}] Checking if The Filter is being applied else continuing to next filter , filter option ->"+str+"  ", ThreadContext.get("testName"));
				continue;
			}

			Thread.sleep(1000);
			String currentWindow = driver.getWindowHandle();
			List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);
			Map<String, Object> result = new HashMap<>();
			
			for (int productListIndex = 1; productListIndex <productNameListingPage.size(); productListIndex++) {
				result = genericUtility.applyFilterOptionsAndFetchProductDetailsForOS(productListIndex, str, currentWindow, safeAct);		
			}

			allResults.add(result);
			safeAct.safeClick(productPage.clearButtonBy);
			if (i % 10 == 0 && i != 0) {
				driver.navigate().refresh();
				log.info("[{}] Refreshing the page to avoid storage issue ->"+str, ThreadContext.get("testName"));
			}
		}

		log.info("[{}] Returning the Master data 'allResults'  ->", ThreadContext.get("testName"));
		return allResults;
	}

	
	
	
	
	public void applyOperatingSystemFilterAndValidateProducts(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {

		SafeActions safeAct = new SafeActions();
		ProductListingPage productPage = new ProductListingPage();
		GenericUtility genericUtility = new GenericUtility();

		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		genericUtility.printFilterNamesOnly(filterOptionsBy); 
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

			if (!safeAct.safeClickBoolean(productPage.getfilterByTypeAndName(filterName, str))) {
				System.out.println("Filter click failed for: " + str + ". Skipping this filter option.");
				continue; // â›” Skip the rest of the current loop iteration
			}


			Thread.sleep(1000);
			String currentWindow=driver.getWindowHandle();
			System.out.println("Printing current window  "+ currentWindow);

			List<WebElement> productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
			for(int p=1;p<productNameListingPage.size();p++) {

				System.out.println("inside the loop and product name is "+productNameListingPage.get(p).getText());				
				try {
					WebElement productElement = driver.findElement(productPage.getProductByIndex(p));

//					Actions actions = new Actions(driver);
//					actions
//					.keyDown(Keys.CONTROL)
//					.click(productElement)
//					.keyUp(Keys.CONTROL)
//					.build()
//					.perform();
					genericUtility.smoothScrollToElement(productPage.getProductByIndex(p));
					Thread.sleep(1000);
					safeAct.safeClick(productPage.getProductByIndex(p));

					System.out.println("Product clicked with Ctrl+Click to open in new tab.");
					Thread.sleep(2000); 

				} catch (Exception e) {
					System.out.println("Failed to Ctrl+Click product index " + p);
					continue;
				}


				System.out.println("Clicked on the producct name new pop-up should open");
				Thread.sleep(2000);		
				genericUtility.switchToNewWindow(currentWindow);

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
					Thread.sleep(2000);
					driver.close();
					driver.switchTo().window(currentWindow);
					continue; 
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
		GenericUtility genericUtility=new GenericUtility();
		SafeActions safeAct = new SafeActions();
		
		
		List<Map<String, Object>> results = new ArrayList<>();

		// Scroll to make slider visible
		genericUtility.smoothScrollToElement(productPage.priceMinSliderButton);

		Thread.sleep(2000);

		// Locate sliders	    
		WebElement minSlider = safeAct.safeFindElement(productPage.priceMinSliderButton);
		WebElement maxSlider = safeAct.safeFindElement(productPage.priceMaxSliderButton);

		for (int i = 0; i < minValues.size(); i++) {
			int min = minValues.get(i);
			int max = maxValues.get(i);
			
			genericUtility.smoothScrollToElement(productPage.priceMinSliderButton);
			//Applying Min slider
			genericUtility.setSliderValue(minSlider, min);

			//Applying Max slider
			genericUtility.setSliderValue(maxSlider, max);

			Thread.sleep(1000);
			
			// Click 'Go' / Apply
			safeAct.safeClick(productPage.priceSliderSubmitButton);
			log.info("[{}] Hitting the submit button", ThreadContext.get("testName"));

			Thread.sleep(2000);

			// Extract the applied max filter text and product prices
			String maxPriceApplied = safeAct.safeFindElement(productPage.maxPriceFilterApplied).getText();
			String minPriceApplied = safeAct.safeFindElement(productPage.minPriceFilterApplied).getText();
			List<WebElement> prices = safeAct.safeFindElements(productPage.productPriceFromProductCards);



			for(int k=0;k<prices.size();k++) {
				
				log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(k).getText()+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));
				
			}
			
			String appliedFilter="Max Price="+maxPriceApplied+"  "+"Min price="+minPriceApplied;

			List<String> mismatches = new ArrayList<>();
			boolean isValid = true;

			String testName = ThreadContext.get("logFileName");
			ScreenshotUtil.capture(testName,appliedFilter);

			for (int j = 0; j < prices.size(); j++) {
				
				String productPrice = prices.get(j).getText();
				productPrice= GenericUtility.extractIntOrFail(productPrice);
				maxPriceApplied=GenericUtility.extractIntOrFail(maxPriceApplied);
				minPriceApplied=GenericUtility.extractIntOrFail(minPriceApplied);

				int productPriceInt = Integer.parseInt(productPrice);
				int maxPriceFilterAppliedInt = Integer.parseInt(maxPriceApplied);
				int minPriceFilterAppliedInt = Integer.parseInt(minPriceApplied);


				if (productPriceInt <= maxPriceFilterAppliedInt &&  productPriceInt>=minPriceFilterAppliedInt) {
					log.info("[{}] Price Check <= Max & >= Min  Product Price is ->"+productPriceInt, ThreadContext.get("testName"));
					log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(j).getText()+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));

				} else {
					log.info("[{}] Product Price Not Under the Range of Min & Max", ThreadContext.get("testName"));
					log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(j).getText()+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));

					String errorMessage =
						    "Price out of range: " +
						    "ProductIndex=" + j +
						    ", AppliedMin=" + minPriceApplied +
						    ", AppliedMax=" + maxPriceApplied +
						    ", ActualPrice=" + productPrice;
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

	
}