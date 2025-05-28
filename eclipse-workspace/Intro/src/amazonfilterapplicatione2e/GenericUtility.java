package amazonfilterapplicatione2e;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class GenericUtility extends ProductListingPage{
	
	
	public boolean filterCheckUnderList(String filterName) {
	    String target = filterName.trim().toLowerCase();

	    for (WebElement el : listOfFilterNameInLeftNav) {
	        if (el.getText().trim().toLowerCase().equals(target)) {
	            System.out.println(filterName + " matches with assert text here");
	            return true;
	        }
	    }

	    System.out.println("Filter option '" + filterName + "' does not exist in the list. Skipping the test.");
	    return false;
	}




    public boolean filterCheckUnderList(String filterName1,String filterName2) {
	
    List<String> filterNames = new ArrayList<>();
    String target1 = filterName1.trim().toLowerCase(); // convert input to lowercase
    String target2 = filterName1.trim().toLowerCase();

    for (WebElement el : listOfFilterNameInLeftNav) {
        String text = el.getText().trim().toLowerCase(); // convert element text to lowercase
        filterNames.add(text);
    }

    if (filterNames.contains(target1) ||filterNames.contains(target2) ) {
        System.out.println(filterName1+ "  and " +filterName2 + " matches with assert text here");
        return true;
    } else {
        System.out.println("Filter option '" + filterName1 +"  " +filterName2+ "' does not exist in the list. Skipping the test.");
        return false;
    }
}
    
	By listProcessorSpeedOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base']");

    
	
	
    public void printFilterNamesOnly(SafeActions safeAct) {
		List<WebElement> listProcessorSpeedOptions=safeAct.safeFindElements(listProcessorSpeedOptionsBy);		
		for (int i = 1; i < listProcessorSpeedOptions.size(); i++) {
			System.out.println(listProcessorSpeedOptions.get(i).getText() + "   size of the list is  " + listProcessorSpeedOptions.size());
		}
    }

    
    public void scrollByPixel(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        try {
            Thread.sleep(2000); // Wait to allow scroll animation to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Best practice to preserve interrupt status
            e.printStackTrace();
        }
    }
 
    
    public void closeCurrentWindowAndSwitchBack(String currentWindow) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (handle.equals(currentWindow)) {
                driver.close(); // Close the current popup or child window
                System.out.println("Switching back to the listing page");
                driver.switchTo().window(handle); // Switch back to original window
            }
        }
    }

    
}
