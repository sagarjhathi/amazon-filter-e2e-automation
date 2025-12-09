package main.java.amazonfilterapplicatione2e.flows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.GenericUtility;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtil;

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
	
	
}
