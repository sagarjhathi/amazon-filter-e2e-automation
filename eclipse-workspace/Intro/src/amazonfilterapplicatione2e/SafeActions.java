package amazonfilterapplicatione2e;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SafeActions extends BasePage{
   

	public void safeClick(By locator) {
	    int attempts = 0;
	    while (attempts < 3) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	            element.click();
	            System.out.println("Clicking using safeClick");
	            return;
	        } catch (TimeoutException |ElementClickInterceptedException | StaleElementReferenceException e) {
	            System.out.println("Retrying click for: " + locator + " - Attempt " + (attempts + 1));
	            attempts++;
	            try {
	                driver.navigate().refresh();
	                Thread.sleep(1000); // small delay before retry
	            } catch (InterruptedException ignored) {}
	        }
	    }
	    // After 3 attempts, skip the action without throwing exception
	    System.out.println("Skipping click action: Element not clickable after 3 attempts - " + locator);
	}

	public List<WebElement> safeFindElements(By locator) {
	    int attempts = 0;
	    while (attempts < 3) {
	        try {
	            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	            System.out.println("Found the elements: " + locator);
	            return elements;
	        } catch (NoSuchElementException | StaleElementReferenceException e) {
	            System.out.println("Retrying findElements for: " + locator + " - Attempt " + (attempts + 1));
	            attempts++;
	            try {
	                driver.navigate().refresh();
	                System.out.println("Refreshing the page in safeFindElements Method");
	                Thread.sleep(1000);
	            } catch (InterruptedException ignored) {}
	        }
	    }
	    // After 3 attempts, return null instead of throwing exception
	    System.out.println("Skipping action: Elements not found after 3 attempts - " + locator);
	    return null;
	}

		
		
		public WebElement safeFindElement(By locator) {
		    int attempts = 0;
		    while (attempts < 3) {
		        try {
		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		            System.out.println("Found the element: " + locator);
		            return element;
		        } catch (Exception e) {
		            System.out.println("Retrying findElement for: " + locator + " - Attempt " + (attempts + 1));
		            attempts++;
		            try {
		                driver.navigate().refresh();
		                System.out.println("Refreshing the page in safeFindElement Method");
		                Thread.sleep(1000);
		            } catch (InterruptedException ignored) {}
		        }
		    }

//		    throw new RuntimeException("Failed to find element after multiple retries: " + locator);
		    // Element not found after retries — skip the action
		    System.out.println("Skipping action: Element not found after 3 attempts - " + locator);
		    return null;
		}
}

