package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pages.AmazonLandingPage;
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtil;

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
			
			log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

			System.out.println("Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData");
			
			boolean runAll= ConfigManager.getBoolean("runForAllFilterOptionsCI", false);
			System.out.println("runAll is"+runAll);
			if(runAll==false) {
				filterOptionSize=ConfigManager.getInt("overideFilteOptionsCount", 3);
				System.out.println(filterOptionSize +" is the  filterOptionSize");
			}
			
			
		}else {
		
			log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

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
			int productNameListingPageSize=productNameListingPage.size();
			
			
			if(isCron) {
				log.info("[{}] Execution is scheduled type /CRON Job on CI Hence refering to CI keys from UtilData", ThreadContext.get("testName"));

				boolean runAllProducts= ConfigManager.getBoolean("runForAllProductsUnderListingCI", false);
				if(runAllProducts==false) {
					productNameListingPageSize=ConfigManager.getInt("overideProductsListingCount", 3);
					System.out.println(productNameListingPageSize +" is the  overideProductsListingCount");
					log.info("[{}] Limiting traversal to in-loop size to prevent IndexOutOfBoundsException in applyFilterAndValidateProductsWithResult", ThreadContext.get("testName"));

				}
			}else {
				
				log.info("[{}] Execution is Not CRON Job Hence refering to normal keys from UtilData", ThreadContext.get("testName"));

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

				Map<String, Object> productResult = applyFilterOptionsAndFetchProductDetailsGlobal(productIndex, filterValue, currentWindow, safeAct);
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

	
	
	
	public Map<String, Object>applyFilterOptionsAndFetchProductDetailsGlobal(
			 
		    int productIndex,
		    String filterValue,
	        String currentWindow,
	        SafeActions safeAct
	        
		 ) throws InterruptedException{
 

	 String testName = ThreadContext.get("logFileName");
	 int beforeProductClick = driver.getWindowHandles().size();
	
	 
	 Map<String, Object> productResult = new HashMap<>();
	 productResult.put("filter", filterValue);
	 
	try {
		
		WebElement productElement = safeAct.safeFindElement(productPage.getProductByIndex(productIndex));
		genericUtility.smoothScrollToElement(productPage.getProductByIndex(productIndex));
		log.info("[{}] Getting product by index in productNameListingPage loop", testName);
		
		Thread.sleep(1000);
		boolean isWindowOpened=genericUtility.openClickOnNewPage(productElement,beforeProductClick);

		System.out.println("Product clicked with Ctrl+Click to open in new tab.");
		log.info("[{}] Opened product in new tab via Ctrl+Click inside productNameListingPage loop", testName);

		if(isWindowOpened) {
			genericUtility.waitForNewWindowAndSwitch(currentWindow,beforeProductClick);
			log.info("[{}] Swithcing to the new window  within productNameListingPage loop", testName);
		}else {
			 log.warn("[{}] Could not open product in a new tab", testName);
		}
} catch (Exception e) {
	log.info("[{}] Failed to Ctrl+Click product index " + productIndex+"  for filter value->"+filterValue, testName);
    System.out.println("Failed to Ctrl+Click product index " + productIndex);
}

System.out.println("Clicked on the producct name new pop-up should open");
log.info("[{}] Clicked product name to open in new tab from productNameListingPage loop", testName);

String name="";
try {
name = genericUtility.fetchTextWithRetries(productPage.productNameIndividualPage, safeAct);
String keyFeatures = genericUtility.fetchTextWithRetries(productPage.productKeyFeatureBlock, safeAct);
String about = genericUtility.fetchTextWithRetries(productPage.aboutThisItemBulletPoint, safeAct);
String techDetails = genericUtility.fetchTextWithRetries(productPage.technicalDetailsBlockIndividualPage, safeAct);

genericUtility.addFieldIfPresent("title",name ,filterValue,productIndex,productResult);
genericUtility.addFieldIfPresent("keyFeatures",keyFeatures ,filterValue,productIndex,productResult);
genericUtility.addFieldIfPresent("about",about ,filterValue,productIndex,productResult);
genericUtility.addFieldIfPresent("techDetails",techDetails ,filterValue,productIndex,productResult);
log.info("[{}] Extracting 'name' , 'keyFeatures', 'about' , 'techDetails' within productNameListingPage loop", testName);

}catch(Exception e) {
	log.info("[{}] Something went wrong while switching or extracting the details", testName);

}


try {
	 if(genericUtility.isElementInViewport(productPage.showMoreOnlyIndividualPage)) {
     	String productNamePlusIndex="Product Index="+productIndex;
     	genericUtility.smoothScrollToElement(productPage.reportAnIssue);
     	Thread.sleep(1000);
     	ScreenshotUtil.capture(testName, filterValue, productNamePlusIndex);
			log.info("[{}] Within Try block  clicking 'show more' hence Taking screen shot available button on ui", testName);
     }else {
    	 genericUtility.smoothScrollToElement(genericUtility.seeMoreProductDetailsButtonIndividualPageBy);
         Thread.sleep(1000);
         safeAct.safeClick(genericUtility.seeMoreProductDetailsButtonIndividualPageBy);
			 log.info("[{}] Within try block for clicking see more deatils within productNameListingPage loop", testName);
         System.out.println("'See More Details' clicked.");
     }
   
} catch (Exception e1) {
    
	genericUtility.smoothScrollToElement(productPage.showMoreOnlyIndividualPage);
	Thread.sleep(1000);
	
	String productNamePlusIndex="Product Name="+name+"  "+"Product Index="+productIndex;
	ScreenshotUtil.capture(testName, filterValue, productNamePlusIndex);
	log.info("[{}] Within catch block Cannot click 'see more details' hence Taking screen shot available button on ui", testName);

    Thread.sleep(1000);
	log.info("[{}] Within catch block for clicking 'see more deatils' within productNameListingPage loop", testName);
 
}finally {
	Thread.sleep(1000);
	genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);
	log.info("[{}]  going back to product listing via closeCurrentWindowAndSwitchBack ", testName);
}


 return  productResult;
 
 
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
					genericUtility.smoothScrollToElement(productPage.seeMoreProductDetailsButtonIndividualPageBy);
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
	
	
	
	
	
}
