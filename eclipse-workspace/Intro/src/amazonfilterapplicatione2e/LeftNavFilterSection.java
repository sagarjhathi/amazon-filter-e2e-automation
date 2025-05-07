package amazonfilterapplicatione2e;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftNavFilterSection {

	@FindBy(xpath="//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']")
	List<WebElement> filteroptionsHeaderLeftNav;
	
	
}
