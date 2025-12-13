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
			"//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Storage Capacity']" +
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
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']/parent::div/following-sibling::div[@class='a-section a-spacing-medium sf-navigation-searchable-content']//span[@class='a-size-base a-color-base' and text()='"+filterOption+"']");
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
		case "brandsold":
			return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ filterOption + "']");
			
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
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']/parent::div/following-sibling::ul//span[@class='a-size-base a-color-base a-text-bold'and text()='"+ filterOption + "']");
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
			return By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base a-text-bold'and text()='"+ filterOption + "']");
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
	    
	    
	
	
	
	
//	public By getfilterHeaderByTypeAndName(String filterName) {
//		log.info("[{}] Within getAppliedfilterByTypeAndName method", ThreadContext.get("testName"));
//
//		switch (filterName.toLowerCase()) {
//		case "processorspeed":
//			return By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']");
//		case "storagecapacity":
//			return By.xpath("//ul[@id='filter-p_n_g-1003492455111']");
//		case "brands":
//			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']");
//		case "batterycapacity":
//			return By.xpath("//ul[@id='filter-p_n_g-101015098008111']");
//		case "displaysize" :
//			return By.xpath("//div[@id='p_n_g-1004194492091-title']");
//		case "displaytype":
//			return By.xpath("//ul[@id='filter-p_n_g-101013595158111']");
//		case "operatingsystem":
//			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']");	
//		case "mobilephoneprimarycameraresolution":
//			return By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']");
//		case "discount":
//			return By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']");
//		case "brandsold":
//			return By.xpath("//div[@id='p_123-title']");
//		default:
//			throw new IllegalArgumentException("Unknown filter type: " + filterName);
//		}
//	}
    

	
	    
	
	
	
	public By getfilterHeaderByTypeAndName(String filterName) {
		log.info("[{}] Within getAppliedfilterByTypeAndName method", ThreadContext.get("testName"));

		switch (filterName.toLowerCase()) {
		case "processorspeed":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Processor Speed']");
		case "storagecapacity":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Storage Capacity']");
		case "brands":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']");
		case "batterycapacity":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Battery Capacity']");
		case "displaysize" :
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Display Size']");
		case "displaytype":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Display Type']");
		case "operatingsystem":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']");	
		case "mobilephoneprimarycameraresolution":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Mobile Phone Primary Camera Resolution']");
		case "discount":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Discount']");
		case "brandsold":
			return By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Brands']");
		default:
			throw new IllegalArgumentException("Unknown filter type: " + filterName);
		}
		

	}
	    
	public By getProductByIndex(int index) {
		log.info("[{}] Within getProductByIndex method", ThreadContext.get("testName"));
		return By.xpath("(//div[@data-cy='title-recipe'])[" + index + "]");
	}
	 
	 

	
	
}