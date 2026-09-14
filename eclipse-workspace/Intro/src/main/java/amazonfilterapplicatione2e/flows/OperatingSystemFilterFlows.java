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
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtilUpdated;

public class OperatingSystemFilterFlows extends BasePage{

	
	
	private  final Logger log = LoggerUtility.getLogger(OperatingSystemFilterFlows.class);
	
	
	
	
	
    private ProductListingPage productPage;
    private SafeActions safeAct;
    private GenericUtility genericUtility;

public OperatingSystemFilterFlows() {
    this.productPage = new ProductListingPage();
    this.safeAct = new SafeActions();
    this.genericUtility = new GenericUtility();
}




	
	public List<Map<String, Object>> applyOperatingSystemFilterAndValidateProductsWithResults(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {
		log.info("[{}] Within applyOperatingSystemFilterAndValidateProductsWithResults method", ThreadContext.get("testName"));


		SafeActions safeAct = new SafeActions();
		ProductListingPage productPage = new ProductListingPage();
		GenericUtility genericUtility = new GenericUtility();

		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		
		
		List<Map<String, Object>> allResults = new ArrayList<>();
		log.info("[{}] Within OS fucntion , this is the filterOptions size ->"+filterOptions.size(), ThreadContext.get("testName"));


		// Nightly/CRON runs use separate *CI config keys so they can scale up independently
		// of push runs (matches SharedFilterFlows) — without this, OS filter tests always ran
		// at push-run depth even on the nightly full regression.
		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));

		int filterOptionSize=filterOptions.size();
		if(isCron) {
			boolean runAll= ConfigManager.getBoolean("runForAllFilterOptionsCI", false);
			if(runAll==false) {
				int overideFilteOptionsCountDefault=ConfigManager.getInt("overideFilteOptionsCountDefault");
				filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", overideFilteOptionsCountDefault);
			}
		} else {
			boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
			if(runAll==false) {
				int overideFilteOptionsCountDefault=ConfigManager.getInt("overideFilteOptionsCountDefault");
				filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", overideFilteOptionsCountDefault);
			}
		}


		for (int i = 0; i <filterOptionSize; i++) {
			log.info("[{}] Within the FilterOptions loop", ThreadContext.get("testName"));

			List<WebElement> inloopParent = safeAct.safeFindElements(filterOptionsBy);
			if (i > inloopParent.size() - 1) {
				return allResults;
			}


			String str = safeAct.safeGetFilterOptionText(filterOptionsBy, i);
			
			
			if (!safeAct.safeClickBooleanWithScreenShot(productPage.getfilterByTypeAndName(filterName, str),filterName,str)) {
				System.out.println("Filter click failed for: " + str);
				log.info("[{}] Checking if The Filter is being applied else continuing to next filter , filter option ->"+str+"  ", ThreadContext.get("testName"));
				continue;
			}

			
			String currentWindow = driver.getWindowHandle();
			List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);


			// Default to the real fetched list size (matches SharedFilterFlows) so the
			// "run all products" case has a real count instead of staying at 0.
			int productNameListingPageSize=productNameListingPage.size();
			if(isCron) {
				boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListingCI", false);
				if(runAllProducts==false) {
					int overideProductsListingCountDefault=ConfigManager.getInt("overideProductsListingCountDefault");
					productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", overideProductsListingCountDefault);
					System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
				}
			} else {
				boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListing", false);
				if(runAllProducts==false) {
					int overideProductsListingCountDefault=ConfigManager.getInt("overideProductsListingCountDefault");
					productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", overideProductsListingCountDefault);
					System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
				}
			}

			// One result per product, added inside the loop (matches SharedFilterFlows) — the
			// old code kept only the last product's result per filter option, discarding the rest.
			for (int productListIndex = 1; productListIndex <=productNameListingPageSize-1; productListIndex++) {
				Map<String, Object> result = applyFilterOptionsAndFetchProductDetailsForOS(productListIndex, str, currentWindow, safeAct);
				// The helper can return null on an internal exception (see its catch block) —
				// skip adding it rather than passing a null Map downstream to AmazonTests.
				if (result != null) {
					allResults.add(result);
				}
			}

			safeAct.safeClick(productPage.clearButtonBy);
			if (i % 10 == 0 && i != 0) {
				driver.navigate().refresh();
				log.info("[{}] Refreshing the page to avoid storage issue ->"+str, ThreadContext.get("testName"));
			}
		}

		log.info("[{}] Returning the Master data 'allResults'  ->", ThreadContext.get("testName"));
		return allResults;
	}
	
	
	
	
	
	
	
	
	
	
	
	 public Map<String, Object>applyFilterOptionsAndFetchProductDetailsForOS(
			 
			 
		        int productListIndex,
		        String filterValue,
		        String currentWindow,
		        SafeActions safeAct
		        ){

		    String testName = ThreadContext.get("logFileName");
		    int productIndex = productListIndex - 1;

		    Map<String, Object> result = new HashMap<>();
	        result.put("filter", filterValue);
	        int before = driver.getWindowHandles().size();
		    try {
				

		        WebElement productElement = driver.findElement(productPage.getProductByIndex(productListIndex));
		        genericUtility.smoothScrollToElement(productPage.getProductByIndex(productListIndex));
		       
		  
   
		        genericUtility.openClickOnNewPage(productElement,before);
		        log.info("[{}] Clicking product index={} for filter='{}'",
		                 ThreadContext.get("testName"), productListIndex, filterValue);

		        
		    	int after = driver.getWindowHandles().size();

				if (after == before) {
					System.out.println("Before click and AFTER CLICK count is same , trying again");
					// getProductByIndex builds a 1-based XPath position ([index]) — must reuse
					// productListIndex here, not the 0-based productIndex, or this either clicks
					// nothing ([0]) or the wrong product.
					safeAct.safeClick(productPage.getProductByIndex(productListIndex));
		         }
				
		     
		        genericUtility.switchToNewWindow(currentWindow);

		        String name = genericUtility.fetchTextWithRetries(productPage.productNameIndividualPage, safeAct);
		        String keyFeatures = genericUtility.fetchTextWithRetries(productPage.productKeyFeatureBlock, safeAct);
		        String about = genericUtility.fetchTextWithRetries(productPage.aboutThisItemBulletPoint, safeAct);
		        String techDetails = genericUtility.fetchTextWithRetries(productPage.technicalDetailsBlockIndividualPage, safeAct);

		        genericUtility.addFieldIfPresent("title",name ,filterValue,productIndex,result);
		        genericUtility.addFieldIfPresent("keyFeatures",keyFeatures ,filterValue,productIndex,result);
		        genericUtility.addFieldIfPresent("about",about ,filterValue,productIndex,result);
		        genericUtility.addFieldIfPresent("techDetails",techDetails ,filterValue,productIndex,result);

		        try {
		            if (genericUtility.isElementInViewport(productPage.showMoreOnlyIndividualPage) && techDetails.isEmpty()) {
		                String productNamePlusIndex = "Product Index=" + productIndex;
		                genericUtility.smoothScrollToElement(productPage.reportAnIssue);
		              
		                ScreenshotUtilUpdated.capture(testName, filterValue, productNamePlusIndex);
		                log.info("[{}] Took screenshot for missing tech details; show-more visible",
		                         ThreadContext.get("testName"));
		            }
		        } catch (Exception screenshotEx) {
		            log.info("[{}] Failed screenshot flow for empty tech details",
		                     ThreadContext.get("testName"));
		        }
		        return result;

		    } catch (Exception e) {
		        System.out.println("Failed to validate product at index " + productListIndex + " for filter: " + filterValue);
		        log.warn("[{}] Exception while processing product index={} filter='{}': {}",
		                 ThreadContext.get("testName"), productListIndex, filterValue, e.getMessage());
		        return null;
		    } finally {
		        try {
		        	genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);
		        } catch (Exception ignored) {
		            log.warn("[{}] Failed to close product window or switch back", ThreadContext.get("testName"));
		        }
		    }
		    
		}
	
	
	
	
	
}