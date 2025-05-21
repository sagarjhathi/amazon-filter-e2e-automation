package amazonfilterapplicatione2e;

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
		
			boolean exist = false;
			for (int i = 0; i < listOfFilterNameInLeftNav.size(); i++) {
			    String text = listOfFilterNameInLeftNav.get(i).getText().trim();
			    if (text.equalsIgnoreCase(filterName)) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return exist;
			}
			return exist;
	}
	
}
