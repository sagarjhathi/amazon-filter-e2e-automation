package amazonfilterapplicatione2e;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AmazonTests extends BaseTest {

	
	@Test
	public void searchingTheProduct() {
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
	}
	
	@Test
	public void searchingTheProduct1() {
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
	}
	@Test
	public void searchingTheProduct2() {
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
	}
	@Test
	public void searchingTheProduct3() {
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
	}
	
	@Test
	public void verifyingDeliveryFilterFunctionality() {
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		LeftNavFilterSection lf=new LeftNavFilterSection();
		
		List<WebElement> parentDelivery = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));

//		for (int i = 0; i < parentDelivery.size(); i++) {
//			System.out.println(parentDelivery.get(i).getText() + "   size is  " + parentDelivery.size());
//		}
		
		
		
		for (int i = 0; i < parentDelivery.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));

			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());

			String str = inloopParent.get(i).getText().trim();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			driver.navigate().refresh();
		}
	}
	

}
