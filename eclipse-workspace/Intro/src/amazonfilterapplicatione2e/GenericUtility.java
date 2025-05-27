package amazonfilterapplicatione2e;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;


public class GenericUtility extends ProductListingPage{
	
	
	public boolean filterCheckUnderList(String filterName) {		
	    List<String> filterNames = new ArrayList<>();
	    String target = filterName.trim().toLowerCase(); // convert input to lowercase

	    for (WebElement el : listOfFilterNameInLeftNav) {
	        String text = el.getText().trim().toLowerCase(); // convert element text to lowercase
	        filterNames.add(text);
	    }

	    if (filterNames.contains(target)) {
	        System.out.println(filterName + " matches with assert text here");
	        return true;
	    } else {
	        System.out.println("Filter option '" + filterName + "' does not exist in the list. Skipping the test.");
	        return false;
	    }
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

    
}
