package amazonfilterapplicatione2e;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SafeActions extends BasePage{
   
	private  final Logger log = LoggerUtility.getLogger(SafeActions.class);
//	private final Logger log = LoggerUtility.getLogger(getClass());


	public void safeClick(By locator) {
		log.info("[{}] Within safeClick method", ThreadContext.get("testName"));

	    int attempts = 0;
	    while (attempts < 3) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	            element.click();
	    		log.info("[{}] Clicked the  "+element+"   using safeClick", ThreadContext.get("testName"));
	            System.out.println("Clicked using safeClick");
	            return;
	        } catch (TimeoutException |ElementClickInterceptedException | StaleElementReferenceException e) {
	            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
	    		log.info("[{}] Cannot click the butto using safeClick, trying again", ThreadContext.get("testName"));
	            attempts++;
	            try {
	             //   driver.navigate().refresh();
	                Thread.sleep(1000); // small delay before retry
	            } catch (InterruptedException ignored) {}
	        }
	    }
	    // After 3 attempts, skip the action without throwing exception
		log.info("[{}] Skipping click action: Element not clickable after- "+attempts, ThreadContext.get("testName"));
	    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
	}

	
	public List<WebElement> safeFindElements(By locator) {
		log.info("[{}] Within safeFindElements ", ThreadContext.get("testName"));

	    int attempts = 0;
	    while (attempts < 3) {
	        try {
	            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	    		log.info("[{}] Returning the  "+elements+" from the safeFindElements method", ThreadContext.get("testName"));

	            System.out.println("Found the elements: " + locator);
	            return elements;
	        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
	    		log.info("[{}] Retrying findElements for:"+locator, ThreadContext.get("testName"));
	            System.out.println("Retrying findElements for: " + locator + " - Attempt " + (attempts + 1));
	            attempts++;
	            try {
	                driver.navigate().refresh();
		    		log.info("[{}] Refreshing the page while trying to find :"+locator, ThreadContext.get("testName"));

	                System.out.println("Refreshing the page in safeFindElements Method");
	                Thread.sleep(1000);
	            } catch (InterruptedException ignored) {}
	        }
	    }
	    // After 3 attempts, return null instead of throwing exception
	    System.out.println("Skipping action: Elements not found after 3 attempts - " + locator);
		log.info("[{}] Skipping action Elements not found after"+attempts+"  Attempts", ThreadContext.get("testName"));

	    return null;
	}

		
		
		public WebElement safeFindElement(By locator) {
			log.info("[{}] Within safeFindElement method", ThreadContext.get("testName"));

		    int attempts = 0;
		    while (attempts < 3) {
		        try {
		        	
		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					log.info("[{}]Found the element returning it ,element ->"+element, ThreadContext.get("testName"));
		            System.out.println("Found the element: " + locator);
		            return element;
		        } catch (Exception e) {
					log.info("[{}]Retrying findElement for: ->"+locator, ThreadContext.get("testName"));

		            System.out.println("Retrying findElement for: " + locator + " - Attempt " + (attempts + 1));
		            attempts++;
		            try {
		                driver.navigate().refresh();
						log.info("[{}]Refrehsing the page , while trying to safely find : ->"+locator, ThreadContext.get("testName"));
		                System.out.println("Refreshing the page in safeFindElement Method");
		                Thread.sleep(1000);
		            } catch (InterruptedException ignored) {}
		        }
		    }

//		    throw new RuntimeException("Failed to find element after multiple retries: " + locator);
		    // Element not found after retries — skip the action
			log.info("[{}] Skipping action: Element not found after "+attempts+"   attemps", ThreadContext.get("testName"));

		    System.out.println("Skipping action: Element not found after 3 attempts - " + locator);
		    return null;
		}
		
		
		
		public boolean safeClickBoolean(By locator) {
			log.info("[{}] Within safeClickBoolean method", ThreadContext.get("testName"));

		    int attempts = 0;
		    while (attempts < 3) {
		        try {
		            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		            System.out.println(element+"  printing the element address from the safeBooleanClick from safeActions");
		            element.click();
					log.info("[{}] Clicked the element using safe click ,element is "+element ,ThreadContext.get("testName"));
		            System.out.println("Clicking using safeClick");
		            return true; // success
		        } catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
		            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
					log.info("[{}] Cannot click the button, element ->"+locator ,ThreadContext.get("testName"));

		            attempts++;
		            try {
		                driver.navigate().refresh();
						log.info("[{}] Refrshing the page , while trying to click ->"+locator ,ThreadContext.get("testName"));

		                Thread.sleep(1000);
		            } catch (InterruptedException ignored) {}
		        }
		    }
			log.info("[{}]Skipping click action: Element not clickable after"+attempts+"   attempts" ,ThreadContext.get("testName"));

		    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
		    return false; // failure
		}

}

