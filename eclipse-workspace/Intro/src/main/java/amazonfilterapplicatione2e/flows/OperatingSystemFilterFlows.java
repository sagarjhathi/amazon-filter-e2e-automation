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
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;

public class OperatingSystemFilterFlows extends BasePage{

	
	
	private  final Logger log = LoggerUtility.getLogger(OperatingSystemFilterFlows.class);

	
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
			filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 3);
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
			
			
			int productNameListingPageSize=0;
			boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListing", false);
			if(runAllProducts==false) {
				productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", 3);
				System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
			}
			
			for (int productListIndex = 1; productListIndex <productNameListingPageSize-1; productListIndex++) {
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

}
