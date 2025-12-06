package main.java.amazonfilterapplicatione2e.flows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pages.AmazonLandingPage;
import main.java.amazonfilterapplicatione2e.pages.ProductListingPage;
import main.java.amazonfilterapplicatione2e.safeActions.SafeActions;
import main.java.amazonfilterapplicatione2e.utilities.ScreenshotUtil;

public class DeliveryFilterFlows extends BasePage{

	
	ProductListingPage productPage;
	SafeActions safeAct ;
	
	public DeliveryFilterFlows() {
		
		this.productPage = new ProductListingPage();
		this.safeAct = new SafeActions();
	}
	
	private  final Logger log = LoggerUtility.getLogger(DeliveryFilterFlows.class);

	
	public List<Object> validateDeliveryFilterOptionsWithResult(By filterOptions) throws InterruptedException {
		log.info("[{}] Within validateDeliveryFilterOptionsWithResult method", ThreadContext.get("testName"));

		
		safeAct.safeClick(filterOptions);


		String testName = ThreadContext.get("logFileName");
		Thread.sleep(2000);
		String filterOption=productPage.getDeliveryDayFilterByName(testName);
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
	
}
