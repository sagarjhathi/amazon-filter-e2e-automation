package amazonfilterapplicatione2e;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class GenericUtility extends ProductListingPage{
	
	private  final Logger log = LoggerUtility.getLogger(GenericUtility.class);
	//private final Logger log = LoggerUtility.getLogger(getClass());

	public boolean filterCheckUnderList(String filterName) {
		log.info("[{}] Within filterCheckUnderList method", ThreadContext.get("testName"));

	    String target = filterName.trim().toLowerCase();

	    for (WebElement el : listOfFilterNameInLeftNav) {
	    
	        if (el.getText().trim().toLowerCase().equals(target)) {
	            System.out.println(filterName + " matches with assert text here");
	    		log.info("[{}] Found the Filter within the filter check List", ThreadContext.get("testName"));
	            return true;
	        }
	    }


	    System.out.println("Filter option '" + filterName + "' does not exist in the list. Skipping the test.");
		log.info("[{}] Filter option does not exist within the filter check List", ThreadContext.get("testName"));
	    return false;
	}
	

	
	public boolean isElementInViewport(By locator) {
	    try {
    		log.info("[{}] Checking if element within view port via isElementInViewport method", ThreadContext.get("testName"));

	        List<WebElement> elements = driver.findElements(locator);
	        if (elements.isEmpty()) {
	        	return false;
	        }
    		


	        WebElement element = elements.get(0);
	        if (!element.isDisplayed()) {
	        	return false;
	        }
    		


	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        return (Boolean) js.executeScript(
	            "var elem = arguments[0],                 " +
	            "  box = elem.getBoundingClientRect(),    " +
	            "  cx = box.left + box.width / 2,         " +
	            "  cy = box.top + box.height / 2,         " +
	            "  e = document.elementFromPoint(cx, cy); " +
	            "for (; e; e = e.parentElement) {         " +
	            "  if (e === elem)                        " +
	            "    return true;                         " +
	            "}                                        " +
	            "return false;", element);
	    } catch (Exception e) {
	        return false;
	    }
	    
	}


	
	
	
	public boolean isElementVisibleOnUI(By locator) throws InterruptedException {
		
		log.info("[{}] Checking if element visible on UI via isElementVisibleOnUI Method", ThreadContext.get("testName"));

		System.out.println("Checking if the filter and options visible on UI via isElementVisibleOnUI");
		Thread.sleep(3000);
	    try {
	    
	        List<WebElement> elements = driver.findElements(locator); // Returns empty list if not found
	        if (elements.isEmpty()) {
	        	System.out.println("Filter and Filter options not visible on Ui hence Returning from the function");
	            return false;
	        }

	        WebElement element = elements.get(0);
	        if (!element.isDisplayed()) {
	            return false;
	        }

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        return (Boolean) js.executeScript(
	            "var elem = arguments[0],                 " +
	            "  box = elem.getBoundingClientRect(),    " +
	            "  cx = box.left + box.width / 2,         " +
	            "  cy = box.top + box.height / 2,         " +
	            "  e = document.elementFromPoint(cx, cy); " +
	            "for (; e; e = e.parentElement) {         " +
	            "  if (e === elem)                        " +
	            "    return true;                         " +
	            "}                                        " +
	            "return false;", element);
	    } catch (Exception e) {
	        return false;
	    }
	}



	
	public boolean isElementPresent(By locator) {
		log.info("[{}] Checking if present via isElementPresent Method", ThreadContext.get("testName"));

	    try {
	        driver.findElement(locator);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

    public boolean filterCheckUnderList(String filterName1,String filterName2) {
	
		log.info("[{}] Within filterCheckUnderList method", ThreadContext.get("testName"));

    List<String> filterNames = new ArrayList<>();
    String target1 = filterName1.trim().toLowerCase(); // convert input to lowercase
    String target2 = filterName2.trim().toLowerCase();


    for (WebElement el : listOfFilterNameInLeftNav) {
        String text = el.getText().trim().toLowerCase(); // convert element text to lowercase
        filterNames.add(text);
    }
    
    for (String filterName : filterNames) {
        System.out.println(filterName+"  Filter list printing here from filter checklist function in generic utility function");
    }

    if (filterNames.contains(target1) ||filterNames.contains(target2) ) {
        System.out.println(filterName1+ "  and " +filterName2 + " matches with assert text here");
        return true;
    } else {
        System.out.println("Filter option '" + filterName1 +"  " +filterName2+ "' does not exist in the list. Skipping the test.");
        return false;
    }
}
    

    
	
	
    public void printFilterNamesOnly(By filterName) {
		log.info("[{}] Within printFilterNamesOnly method", ThreadContext.get("testName"));

		List<WebElement> filterOptions=safeFindElements(filterName);		
		for (int i = 0; i < filterOptions.size(); i++) {
			System.out.println(filterOptions.get(i).getText() + "   size of the list is  " + filterOptions.size());
		}
    }
    
    public void printNamesOnly(List<WebElement> filterOptions) {
		log.info("[{}] Within printNamesOnly method", ThreadContext.get("testName"));

		for (int i = 0; i < filterOptions.size(); i++) {
			System.out.println(filterOptions.get(i).getText() + "   size of the list is  " + filterOptions.size());
		}
    }

    
    public void scrollByPixel(int x, int y) {
		log.info("[{}] Within scrollByPixel method", ThreadContext.get("testName"));

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        try {
            Thread.sleep(2000); // Wait to allow scroll animation to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Best practice to preserve interrupt status
            e.printStackTrace();
        }
    }
    
    public void smoothScrollToElement(By locator) {
		log.info("[{}] Within smoothScrollToElement method", ThreadContext.get("testName"));

        SafeActions safeAct=new SafeActions();
       WebElement element= safeAct.safeFindElement(locator);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", 
            element
        );
    }
 
    
 public void closeCurrentWindowAndSwitchBack(String currentWindow) throws InterruptedException {
		log.info("[{}] Within closeCurrentWindowAndSwitchBack method", ThreadContext.get("testName"));

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (handle.equals(currentWindow)) {
            	System.out.println("In the closeCurrentWindowAndSwitchBack from genetic utility");
            	Thread.sleep(1000);
                driver.close(); // Close the current popup or child window
                Thread.sleep(1000);
                System.out.println("Switching back to the listing page");
                driver.switchTo().window(handle); // Switch back to original window
            }
        }
    }

    
}
