package amazonfilterapplicatione2e;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListingPage extends  BasePage{

	
	@FindBy(xpath="//div[@data-cy='title-recipe']")
	WebElement productNameListingPage;
	
	@FindBy(xpath="//span[@class='a-price-whole']")
	WebElement productPriceListingPage;
	
	@FindBy(xpath="//div[@data-cy='delivery-recipe']")
	WebElement productDeliveryDayListingPage;
	
	
	
	@FindBy(xpath="//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']")
	List<WebElement> listOfFilterNameInLeftNav;
	
	
	
	public boolean filterCheckUnderList(String filterName) {
		
//			boolean exist = false;
//			for (int i = 0; i < listOfFilterNameInLeftNav.size(); i++) {
//			    String text = listOfFilterNameInLeftNav.get(i).getText().trim();
//			    if (text.equalsIgnoreCase(filterName)) {
//			        System.out.println(text + "  matches with assert text here");
//			        exist = true;
//			        break;
//			    }
//			}
//
//			if (!exist) {
//			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
//			    return exist;
//			}
//			return exist;
		
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
