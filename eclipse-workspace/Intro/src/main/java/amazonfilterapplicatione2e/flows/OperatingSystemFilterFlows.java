package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

}
