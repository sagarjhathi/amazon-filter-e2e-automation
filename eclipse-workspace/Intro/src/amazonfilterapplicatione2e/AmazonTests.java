package amazonfilterapplicatione2e;

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
	
}
