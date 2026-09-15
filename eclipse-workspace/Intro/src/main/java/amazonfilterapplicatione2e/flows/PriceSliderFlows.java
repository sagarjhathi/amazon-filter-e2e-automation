package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebElement;

import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtilUpdated;

public class PriceSliderFlows extends BasePage{

	
	private  final Logger log = LoggerUtility.getLogger(PriceSliderFlows.class);
	
	
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


		for (int i = 0; i < minValues.size(); i++) {
			int min = minValues.get(i);
			int max = maxValues.get(i);
		

				// Capture the URL before applying, so we can detect the round-trip below. Amazon's
				// slider snaps to its own price brackets rather than the exact value we set via JS,
				// so we can't reliably wait for an exact "low-price=61" match - we only know the URL
				// will change once the (possibly snapped) filter is actually applied.
				String urlBeforeApply = driver.getCurrentUrl();

				// Apply slider
				genericUtility.setSliderValue(safeAct.safeFindElement(productPage.priceMinSliderButton), min);
				genericUtility.setSliderValue(safeAct.safeFindElement(productPage.priceMaxSliderButton), max);

				// Wait for the URL to actually change and carry a price filter - this is what
				// distinguishes "this iteration's filter applied" from stale state left over from
				// the previous iteration (unlike waiting on resetPriceRangeProductPage, which stays
				// visible across iterations and so never really waits after the first one). The
				// exact applied values are read back from the labels below, not assumed here.
				wait.until(d -> !d.getCurrentUrl().equals(urlBeforeApply)
						&& d.getCurrentUrl().contains("low-price")
						&& d.getCurrentUrl().contains("high-price"));

				String maxPriceApplied = safeAct.safeFindElement(productPage.maxPriceFilterApplied).getText();
				String minPriceApplied = safeAct.safeFindElement(productPage.minPriceFilterApplied).getText();

				// Now safely fetch new prices
				List<String> prices = new ArrayList<>();
				List<WebElement> priceElements = safeAct.safeFindElements(productPage.productPriceFromProductCards);
				if (priceElements == null) {
					log.warn("[{}] No product price elements found for min={} max={}", ThreadContext.get("testName"), min, max);
					priceElements = new ArrayList<>();
				}
				for (WebElement priceElement : priceElements) {
					prices.add(priceElement.getText());
				}


			
			for(int k=0;k<prices.size();k++) {
				log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(k)+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));
			}
			
			String appliedFilter="Max Price="+maxPriceApplied+"  "+"Min price="+minPriceApplied;

			List<String> mismatches = new ArrayList<>();
			boolean isValid = true;

			String testName = ThreadContext.get("logFileName");
			genericUtility.smoothScrollToElement(productPage.priceMinSliderButton);
			ScreenshotUtilUpdated.capture(testName,appliedFilter);

			for (int j = 0; j < prices.size(); j++) {
			
			
				String productPrice = prices.get(j);
				productPrice= GenericUtility.extractIntOrFail(productPrice);
				maxPriceApplied=GenericUtility.extractIntOrFail(maxPriceApplied);
				minPriceApplied=GenericUtility.extractIntOrFail(minPriceApplied);

				int productPriceInt = Integer.parseInt(productPrice);
				int maxPriceFilterAppliedInt = Integer.parseInt(maxPriceApplied);
				int minPriceFilterAppliedInt = Integer.parseInt(minPriceApplied);


				if (productPriceInt <= maxPriceFilterAppliedInt &&  productPriceInt>=minPriceFilterAppliedInt) {
					log.info("[{}] Price Check <= Max & >= Min  Product Price is ->"+productPriceInt, ThreadContext.get("testName"));
					log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(j)+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));

				} else {
					log.info("[{}] Product Price Not Under the Range of Min & Max", ThreadContext.get("testName"));
					log.info("[{}] Min Price Applied is  "+minPriceApplied+"   Product Price is "+prices.get(j)+"  Max Price Applied is "+maxPriceApplied, ThreadContext.get("testName"));

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


		
		}
		log.info("[{}] Returning the data here in the end", ThreadContext.get("testName"));

		return results;
	}
		
}