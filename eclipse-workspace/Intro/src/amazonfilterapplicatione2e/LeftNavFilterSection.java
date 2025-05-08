package amazonfilterapplicatione2e;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftNavFilterSection {

	@FindBy(xpath="//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']")
	List<WebElement> filteroptionsHeaderLeftNav;
	
	@FindBy(xpath="//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")
	List<WebElement> memoryStorageCapacityFilterOptionsLeftNav;
	
	@FindBy(xpath="//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")
	List<WebElement> deliveryDayFilterOptionsLeftNav;
	
	@FindBy(xpath="//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")
	List<WebElement> brandsFilterOptionsLeftNav;
	
	
	
}
