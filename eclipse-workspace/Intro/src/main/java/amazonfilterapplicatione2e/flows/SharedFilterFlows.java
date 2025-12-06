package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList;
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

public class SharedFilterFlows extends BasePage {

	
	
	private  final Logger log = LoggerUtility.getLogger(SharedFilterFlows.class);

	SafeActions safeAct;
	GenericUtility genericUtility;
	ProductListingPage productPage;
	
	public SharedFilterFlows() {
		
		this.productPage= new ProductListingPage();
		this.safeAct = new SafeActions();
		this.genericUtility = new GenericUtility();
	}
	
	
	public List<Map<String, Object>> applyFilterAndValidateProductsWithResult(By filterOptionsBy, String filterName) throws InterruptedException, TimeoutException {

		log.info("[{}] Within applyFilterAndValidateProductsWithResult method", ThreadContext.get("testName"));



		List<WebElement> filterOptions = safeAct.safeFindElements(filterOptionsBy);
		List<Map<String, Object>> results = new ArrayList<>();

		log.info("[{}] Within applyFilterAndValidateProductsWithResult filterOptions size is -> "+filterOptions.size(), ThreadContext.get("testName"));

		
		boolean isCron = Boolean.parseBoolean(System.getenv("IS_CRON"));
		
		int filterOptionSize=filterOptions.size();
		if(isCron) {
			
			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");
			
			boolean runAll= ConfigManager.getBoolean("runForAllFilterOptionsCI", false);
			if(runAll==false) {
				filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 3);
				System.out.println(filterOptionSize +" is the  filterOptionSize");
			}
			
			
		}else {
		
		System.out.println("Execution is Not CRON Job Hence refering to normal keys from UtilData");
			
		boolean runAll= ConfigManager.getBoolean("runForAllFilterOptions", false);
		if(runAll==false) {
			filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 3);
			System.out.println(filterOptionSize +" is the  filterOptionSize");
		   }
		
		
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

			if (!safeAct.safeClickBooleanWithScreenShot(productPage.getfilterByTypeAndName(filterName, filterValue),filterName,filterValue)) {
				System.out.println("Filter click failed for: " + filterValue + ". Skipping this filter option.");
				continue; 
			}


			
			Thread.sleep(1000);
			String currentWindow = driver.getWindowHandle();

			List<WebElement> productNameListingPage = safeAct.safeFindElements(productPage.productNameListingPageBy);
			int productNameListingPageSize=0;
			
			
			if(isCron) {
				
				boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListingCI", false);
				if(runAllProducts==false) {
					productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", 3);
					System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
				}
			}else {
				
				boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListing", false);
				if(runAllProducts==false) {
					productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", 3);
					System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
				}
			}
			
			
			
			
	
			for (int productIndex = 1; productIndex <=productNameListingPageSize-1; productIndex++) {

			
				productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
				log.info("[{}] Within productNameListingPage loop in applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));
				System.out.println("inside the loop and product name is " + productNameListingPage.get(productIndex).getText());

				Map<String, Object> productResult = genericUtility.applyFilterOptionsAndFetchProductDetailsGlobal(productIndex, filterValue, currentWindow, safeAct);
				log.info("[{}]  Added 'filter','title','keyFeatures', 'about', 'techDetails' into the Map -> productResult", ThreadContext.get("testName"));

				results.add(productResult);
				log.info("[{}]  Added 'productResult' Map into the 'results' i.e list of maps ", ThreadContext.get("testName"));
				log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));

			}

			safeAct.safeClick(productPage.clearButtonBy);
			log.info("[{}] Clearing the filter value ->"+filterValue+"within productNameListingPage loop", ThreadContext.get("testName"));

			log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));
			log.info("[{}]==========================================================================================================", ThreadContext.get("testName"));

		}
		log.info("[{}] returning the 'results' i.e list of Maps", ThreadContext.get("testName"));
		return results;
}

}
