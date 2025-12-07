package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pages.AmazonLandingPage;
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;



public class BrandFilterFlows extends BasePage{

	private  final Logger log = LoggerUtility.getLogger(BrandFilterFlows.class);
	
	    private ProductListingPage productPage;
	    private SafeActions safeAct;
	    private GenericUtility genericUtility;
	
	public BrandFilterFlows() {
        this.productPage = new ProductListingPage();
        this.safeAct = new SafeActions();
        this.genericUtility = new GenericUtility();
    }
	
	
	

	
	
	public List<Map<Object, Object>> applyFilterAndValidateBrandsFilterWithResult(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {
		log.info("[{}] Within applyFilterAndValidateBrandsFilterWithResult method", ThreadContext.get("testName"));

//		SafeActions safeAct = new SafeActions();
//		ProductListingPage productPage = new ProductListingPage();
//		GenericUtility genericUtility = new GenericUtility();

		log.info("[{}] Scrolling to the 'More' Button under brands filter section", ThreadContext.get("testName"));
		
		genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderBrandFilter);


		log.info("[{}] Clicked the 'More' Button under brands filter section", ThreadContext.get("testName"));


	List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);



		int filterOptionSize=filterOptions.size();

		boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
		if(runAll==false) {
			filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 3);
		}

		List<Map<Object, Object>> allResults = new ArrayList<>();

		for (int i = 1; i <=filterOptionSize-1; i++) {
			log.info("[{}] Within the filterOptions loop ", ThreadContext.get("testName"));
			
			List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);

				
			
			
			if (i > inloopParent.size() - 1) {
				log.info("[{}] Avoiding out of bouns by only iterating over the innerLoopParent ", ThreadContext.get("testName"));
				continue;
			}


			
			genericUtility.clickMoreButtonIfPresent(safeAct, genericUtility, productPage.seeMoreButtonUnderBrandFilter);
			
			inloopParent = safeAct.safeFindElements(filterOptionsBy);
			
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
}
