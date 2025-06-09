package amazonfilterapplicatione2e;
import static org.testng.Assert.expectThrows;  
import java.time.Duration; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AmazonTests extends BaseTest {

	
	@Test(priority=13)
	public void verifyingGetItByTomorrowFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		
		    List<WebElement> filterOptions=driver.findElements(By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']"));
		    
		    for(int k=0;k<filterOptions.size();k++) {
		    	System.out.println(filterOptions.get(k).getText().trim());
		    }
		    

		    
		    GenericUtility genericUtility=new GenericUtility();
			if (!genericUtility.filterCheckUnderList("Get It by Tomorrow")) {
			    System.out.println("Filter option 'Get It by Tomorrow' does not exist in the list. Skipping the test.");
			    return;
			}
			
			
		    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It by Tomorrow']"))).click();
			Thread.sleep(2000);
			
			    List<WebElement> deliveryChild=driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));	
			
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
			    String todayFormatted = LocalDate.now().format(formatter);
			    String tomorrowFormatted = LocalDate.now().plusDays(1).format(formatter);

			    // Clean and split for partial matching
			    Set<String> allowedDateParts = new HashSet<>();
			    Collections.addAll(allowedDateParts, todayFormatted.replace(",", "").split(" "));
			    Collections.addAll(allowedDateParts, tomorrowFormatted.replace(",", "").split(" "));
			    allowedDateParts.add("Tomorrow");
			    allowedDateParts.add("Today");
			    
			    System.out.println("Allowed date parts: " + allowedDateParts);
 	       	
     
	for(int j=0;j<deliveryChild.size();j++) {
		System.out.println(deliveryChild.get(j).getText()+"   size is " +deliveryChild.size() +" index no is "+j);
		String assertString=deliveryChild.get(j).getText();
		
		  boolean found = false;

		    for (String part : allowedDateParts) {
		        if (assertString.contains(part)) {
		            found = true;
		            break; // stop as soon as a match is found
		        }
		    }
		    
		    Assert.assertTrue(found, "❌ None of the allowed date parts are present in: " + assertString);
		    System.out.println("✔ Valid delivery date found in: " + assertString);
		
	        }
	
			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			
			System.out.println("Clicking clear under delivery");
			Thread.sleep(2000);
		}
	
	
	@Test(priority=12)
	public void verifyingGetItIn2DaysFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		
		        		    GenericUtility genericUtility=new GenericUtility();
		            		
		            		if (!genericUtility.filterCheckUnderList("Get It in 2 Days")) {
		            		    System.out.println("Filter option 'Get It in 2 Days' does not exist in the list. Skipping the test.");
		            		    return ;
		            		}
		            		

			
			
		    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It in 2 Days']"))).click();
			Thread.sleep(2000);
			
			    List<WebElement> deliveryChild=driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));	
			
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
			    String todayFormatted = LocalDate.now().format(formatter);
			    String tomorrowFormatted = LocalDate.now().plusDays(1).format(formatter);
			    String dayAfterTomorrowFormatted = LocalDate.now().plusDays(2).format(formatter);

			    // Clean and split for partial matching
			    Set<String> allowedDateParts = new HashSet<>();
			    Collections.addAll(allowedDateParts, todayFormatted.replace(",", "").split(" "));
			    Collections.addAll(allowedDateParts, tomorrowFormatted.replace(",", "").split(" "));
			    Collections.addAll(allowedDateParts, dayAfterTomorrowFormatted.replace(",", "").split(" "));
			    allowedDateParts.add("Tomorrow");
			    allowedDateParts.add("Today");
			    
			    System.out.println("Allowed date parts: " + allowedDateParts);
 	       	
     
	for(int j=0;j<deliveryChild.size();j++) {
		System.out.println(deliveryChild.get(j).getText()+"   size is " +deliveryChild.size() +" index no is "+j);
		String assertString=deliveryChild.get(j).getText();
		
		  boolean found = false;

		    for (String part : allowedDateParts) {
		        if (assertString.contains(part)) {
		            found = true;
		            break; // stop as soon as a match is found
		        }
		    }
		    
		    Assert.assertTrue(found, "❌ None of the allowed date parts are present in: " + assertString);
		    System.out.println("✔ Valid delivery date found in: " + assertString);
		
	        }
	
			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			
			System.out.println("Clicking clear under delivery");
			Thread.sleep(2000);
		}
	
	

	
	
	@Test(priority=11)
	public void verifyingGetItByTodayFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		               
		                    GenericUtility genericUtility=new GenericUtility();
		            		
		            		if (!genericUtility.filterCheckUnderList("Get It Today")) {
		            		    System.out.println("Filter option 'Get It Today' does not exist in the list. Skipping the test.");
		            		    return ;
		            		}
		

			
			
		    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-size-base a-color-base' and text()='Get It Today']"))).click();
			Thread.sleep(2000);
			
			List<WebElement> deliveryChild=driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));	
			
 	       	
    
	for(int j=0;j<deliveryChild.size();j++) {
		System.out.println(deliveryChild.get(j).getText()+"   size is " +deliveryChild.size() +" index no is "+j);
		String assertString=deliveryChild.get(j).getText();
		
		  boolean found = false;
		  if (assertString.contains("Today")) {
	            found = true;
	        }
		    Assert.assertTrue(found, "❌ None of the allowed date parts are present in: " + assertString);
		    System.out.println("✔ Valid delivery date found in: " + assertString);
	        }
	
			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			
			System.out.println("Clicking clear under delivery");
		}

	
	
	@Test(priority=10)
	public void verifyingTheBrandsFilterFunctionality() throws InterruptedException {
		
	
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		GenericUtility genericUtility=new GenericUtility();
		
		if (!genericUtility.filterCheckUnderList("brands")) {
		    System.out.println("Filter option 'brands' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement moreInBrands = wait.until(ExpectedConditions.elementToBeClickable(
		By.xpath("//a[@aria-label='See more, Brands']")));
		
		
        JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", moreInBrands);
		moreInBrands.click();
		
	
		List<WebElement> listBrandOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
		By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));

		for (int i = 1; i < listBrandOptions.size(); i++) {
			System.out.println(listBrandOptions.get(i).getText() + "   size is  " + listBrandOptions.size());
		}
		
		
		
		for (int i = 1; i < listBrandOptions.size(); i++) {
			
			
	    	  WebElement  moreBrands = wait.until(ExpectedConditions.elementToBeClickable(
	   		  By.xpath("//a[@aria-label='See more, Brands']")));
				
	    	  
					js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' })", moreBrands);
					Thread.sleep(2000);
					moreBrands.click();
					
				
				List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));

				if(i>inloopParent.size()-1) {
					System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
					return;
				}
				
				System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
				String str = inloopParent.get(i).getText().trim();
				
				
				WebElement ele=driver.findElement(By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ str + "']"));
				js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' })", ele);
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"
								+ str + "']"))).click();
				
				Thread.sleep(3000);
				System.out.println("Before checking the product name the code execution is here");
				
			List<WebElement> productNameOnListingPage =	driver.findElements(By.xpath("//div[@data-cy='title-recipe']"));
			int noOfBrandNameNotIntheList =0;
			for(int k=0;k<productNameOnListingPage.size();k++) {
				if(productNameOnListingPage.get(k).getText().contains(str)) {
					System.out.println("Product name found in the list index no is -->" +k);
				}else {
					System.out.println("Product name not found in the list hence the filter functionality failed index no is -->"+k);
					noOfBrandNameNotIntheList++;
				//	Assert.fail("Product name not found in the list hence the filter functionality failed index no is -->"+k);
				}
				
			}
			
				System.out.println("The no of brand name not present in the list is -->"+noOfBrandNameNotIntheList +"for the brand filter -->"+str);
				
//				wait.until(ExpectedConditions.elementToBeClickable(
//				By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
				
				
				try {
				    // First attempt
				    wait.until(ExpectedConditions.elementToBeClickable(
				        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']")))
				        .click();
				} catch (Exception e) {
				    // Refresh and retry once
				    System.out.println("Element not clickable, going back via navigate.back()...filter name is"+str);
				    driver.navigate().back();
				}
				
				
				if(i==10 || i==20 || i==30) {
					
					driver.navigate().refresh();
					System.out.println("Refreshing the page here ");
				}
			}
		
	}
	
	
	
	@Test(priority=1)
	public void verifyingStorageCapacityFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		GenericUtility genericUtility=new GenericUtility();
		SafeActions safeAct=new SafeActions();
		
		if (!genericUtility.filterCheckUnderList("Storage Capacity")) {
		    System.out.println("Filter option 'Storage Capacity' does not exist in the list. Skipping the test.");
		    return ;
		}
			
		
		By listStorageCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']");
		List<WebElement> listStorageCapacityOptions=safeAct.safeFindElements(listStorageCapacityOptionsBy);
		
		

		for (int i = 1; i < listStorageCapacityOptions.size(); i++) {
			System.out.println(listStorageCapacityOptions.get(i).getText() + "   size is  " + listStorageCapacityOptions.size());
		}
		
		
		for (int i = 1; i < listStorageCapacityOptions.size(); i++) {
					
			List<WebElement> inloopParent =safeAct.safeFindElements(listStorageCapacityOptionsBy);
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			By filterName=By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ str + "']");
			safeAct.safeClick(filterName);
			
			Thread.sleep(2000);

			String currentWindow=driver.getWindowHandle();
			System.out.println("Printing current window  "+ currentWindow);
			
			
			    By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
				List<WebElement> productNameListingPage=safeAct.safeFindElements(productNameListingPageBy);
			    for(int p=1;p<productNameListingPage.size();p++) {
				
				By elementClickOnListingPage=By.xpath("(//div[@data-cy='title-recipe'])["+p+"]");
				safeAct.safeClick(elementClickOnListingPage);
				
				System.out.println("Clicked on the producct name new pop-up should open");
				Thread.sleep(2000);
				Set<String> allWindowHandles=driver.getWindowHandles();
				
				for(String e:allWindowHandles) {
					if(!e.equals(currentWindow)) {
						System.out.println("found the window switching now");

						driver.switchTo().window(e);
					}else {
						System.out.println("did not find the window trying again");
					}
				}
				
				
				System.out.println("Switched to the new window here");
				Thread.sleep(2000);

				By productNameIndividualPage=By.xpath("//span[@id='productTitle']");
				safeAct.safeFindElement(productNameIndividualPage);
				
				
				By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
				safeAct.safeFindElement(productKeyFeatureBlock);
					
				
				By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");
				safeAct.safeFindElement(aboutThisItemBulletPoint);
				
				
				By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");
				safeAct.safeFindElement(technicalDetailsBlockIndividualPage);
				
			
				// Scroll and wait for the 'See More Product Details' button
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700);");
				Thread.sleep(2000);
						
				try {					
					By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");
					WebElement seeMoreProductDetailsButtonIndividualPage = safeAct.safeFindElement(seeMoreProductDetailsButtonIndividualPageBy);
			        ((JavascriptExecutor) driver).executeScript(
			            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);

			        safeAct.safeClick(seeMoreProductDetailsButtonIndividualPageBy);
			        System.out.println("'See More Details' clicked.");

			    } catch (Exception e1) {
//			    	System.out.println("Unable to click the show more details button and the filter and product is --?"+str+"   "+pName);
			    	driver.close();
			    	driver.switchTo().window(currentWindow);
			    	continue; // ✅ move on to the next product
			    }
			    
				Thread.sleep(2000);
//				
//				System.out.println(productNameIndividualPage.getText()+"    productNameIndividualPage");
//				System.out.println(productKeyFeatureBlock.getText()+"    productKeyFeatureBlock");
//				System.out.println(aboutThisItemBulletPoint.getText()+"    aboutThisItemBulletPoint");
//				System.out.println(seeMoreProductDetailsButtonIndividualPage.getText()+"    seeMoreProductDetailsButtonIndividualPage");
//				System.out.println(technicalDetailsBlockIndividualPage.getText()+"    technicalDetailsBlockIndividualPage");

				
				for(String e:allWindowHandles) {
					if(e.equals(currentWindow)) {
						driver.close();
						System.out.println("Switching back to the listing page");
						driver.switchTo().window(e);
					}
				}
				
			}

			Thread.sleep(1000);
			By clearButton =By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");
			safeAct.safeClick(clearButton);
			
		}
	}
	
	
	@Test(priority=8)
	public void verifyingPriceSilderFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		GenericUtility genericUtility=new GenericUtility();
		
		if (!genericUtility.filterCheckUnderList("Price")) {
		    System.out.println("Filter option 'Price' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy", 0,300);
		Thread.sleep(2000);
		
		
		            // List of values to set for min and max sliders
					List<Integer> minValues = Arrays.asList(60, 90, 130);
					List<Integer> maxValues = Arrays.asList(80, 120, 160);

					// Find the min and max sliders
					
					WebElement minSlider = driver.findElement(By.id("p_36/range-slider_slider-item_lower-bound-slider"));
					WebElement maxSlider = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider"));

					// Initialize JavascriptExecutor for executing JavaScript
					for (int i = 0; i < minValues.size(); i++) {
					    int min = minValues.get(i);
					    int max = maxValues.get(i);

					    // Set the min slider value
					    js.executeScript(
					        "arguments[0].value = arguments[1];" +
					        "arguments[0].dispatchEvent(new Event('input'));" +
					        "arguments[0].dispatchEvent(new Event('change'));",
					        minSlider, String.valueOf(min)
					    );

					    // Set the max slider value
					    js.executeScript(
					        "arguments[0].value = arguments[1];" +
					        "arguments[0].dispatchEvent(new Event('input'));" +
					        "arguments[0].dispatchEvent(new Event('change'));",
					        maxSlider, String.valueOf(max)
					    );
					    
					    Thread.sleep(1000);
					    driver.findElement(By.xpath("//div[@class='a-section sf-submit-range-button']")).click();
					    Thread.sleep(2000);
					    String maxPriceFilterApplied=driver.findElement(By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']")).getText();
					    List<WebElement> priceOfAllProductsListingPage=  driver.findElements(By.xpath("//span[@class='a-price-whole']"));
					  
					    for(int j=0;j<priceOfAllProductsListingPage.size();j++) {
					    	String productPrice=priceOfAllProductsListingPage.get(j).getText();
					    	productPrice = productPrice.replaceAll("[^\\d]", "");
					    	maxPriceFilterApplied = maxPriceFilterApplied.replaceAll("[^\\d]", "");
					    	int productPriceInt=Integer.parseInt(productPrice);
					    	int maxPriceFilterAppliedInt=Integer.parseInt(maxPriceFilterApplied);
					    	
					    	boolean bool=true;
					    	if(productPriceInt<=maxPriceFilterAppliedInt) {
					    		System.out.println("Product price is within limits --> product index and applied filter and product price is "+ j+"  "+maxPriceFilterApplied+"  "+productPrice);
					    	}else {
					    		String errorMessage="Product price is above limit --> product index and applied filter is and  product price "+ j+"  "+maxPriceFilterApplied+"  "+productPrice;
					    		bool = false;
					    	    System.out.println(errorMessage);
					    	    Assert.fail(errorMessage); 
					    	}
					    }
					  
					    // Optional: Wait a bit to observe the change (adjust as needed)
					    Thread.sleep(2000); // Wait 1 second for each iteration
					    
					    
					}
					
	}
	
	

	
	@Test(priority = 2)
	public void verifyingBatteryCapacityFilterFunctionality() throws InterruptedException {

	    AmazonLandingPage am = new AmazonLandingPage();
	    am.openingLandingPage();
	    am.givingInputWithinSearchBar("Mobile");
	    am.clickingOnSubmitSearchButton();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    SafeActions safeAct=new SafeActions();
	    
	    GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Battery Capacity")) {
		    System.out.println("Filter option 'Battery Capacity' does not exist in the list. Skipping the test.");
		    return ;
		}


	   

//	    List<WebElement> listBatteryCapacityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	            By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']")));
	    
	    By listBatteryCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']");
	    List<WebElement> listBatteryCapacityOptions=driver.findElements(listBatteryCapacityOptionsBy);
	    
	    for (int i = 1; i < listBatteryCapacityOptions.size(); i++) {
	        System.out.println(listBatteryCapacityOptions.get(i).getText() + " size is " + listBatteryCapacityOptions.size());
	    }

	    for (int i = 1; i < listBatteryCapacityOptions.size(); i++) {

//	        List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	                By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']")));
	    	
	    	List<WebElement> inloopParent=driver.findElements(listBatteryCapacityOptionsBy);

	        if (i > inloopParent.size() - 1) {
	            System.out.println("Avoiding out of bounds issue by traversing only up to the inloop size");
	            return;
	        }

	        System.out.println(inloopParent.get(i).getText() + " size is in loop " + inloopParent.size());
	        String str = inloopParent.get(i).getText().trim();

	        
//	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//	                "//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base' and text()='" + str + "']"))).click();

	        By filterName=By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ str + "']");
	        safeAct.safeClick(filterName);
			
	        Thread.sleep(2000);

	        String currentWindow = driver.getWindowHandle();
	        System.out.println("Printing current window  " + currentWindow);

	        // Fetch products freshly to avoid stale elements
	        List<WebElement> productNameListingPage = null;
	        By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
	        By clearButton=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");
	        try {
	        	
			    productNameListingPage=safeAct.safeFindElements(productNameListingPageBy);
//	            productNameListingPage = driver.findElements(By.xpath("//div[@data-cy='title-recipe']"));
	            if (productNameListingPage.isEmpty()){
	                System.out.println("No products found for filter: " + str);
	                // Clear filter and continue to next option
	                
//	                wait.until(ExpectedConditions.elementToBeClickable(
//	                        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
	                safeAct.safeClick(clearButton);
	                Thread.sleep(2000);
	                continue;
	            }
	        } catch (Exception e) {
	            System.out.println("Error finding product listings: " + e.getMessage());
	            // Clear filter and continue to next option
//	            wait.until(ExpectedConditions.elementToBeClickable(
//	                    By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
	            safeAct.safeClick(clearButton);
	            Thread.sleep(2000);
	            continue;
	        }

	        for (int p = 0; p < productNameListingPage.size(); p++) {
	            try {
	                // Refetch product elements fresh inside loop to avoid stale element
	                List<WebElement> productsNow = driver.findElements(productNameListingPageBy);
	                if (p >= productsNow.size()) {
	                    System.out.println("Less products available now, ending product loop.");
	                    break;
	                }

	                WebElement productElement = productsNow.get(p);
	                System.out.println("Inside the loop and product name is " + productElement.getText());

	                productElement.click();
	                System.out.println("Clicked on the product name, new pop-up should open");
	                Thread.sleep(2000);

	                Set<String> allWindowHandles = driver.getWindowHandles();

	                for (String handle : allWindowHandles) {
	                    if (!handle.equals(currentWindow)) {
	                        System.out.println("Found the new window, switching now");
	                        driver.switchTo().window(handle);
	                        break;
	                    }
	                }

	                System.out.println("Switched to the new window here");
	                Thread.sleep(2000);

	                // Wait and verify product details elements with try-catch for each
//	                WebElement productNameIndividualPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
//	                WebElement productKeyFeatureBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")));
//	                WebElement aboutThisItemBulletPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-bullets']")));
//	                WebElement technicalDetailsBlockIndividualPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='prodDetails']")));

					By productNameIndividualPage=By.xpath("//span[@id='productTitle']");
//					WebElement productNameIndividualPage = wait.until(
//					    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
					safeAct.safeFindElement(productNameIndividualPage);
					
					

					By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
//					WebElement productKeyFeatureBlock = wait.until(
//					    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")));
					safeAct.safeFindElement(productKeyFeatureBlock);
					
					
					By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");
//					WebElement aboutThisItemBulletPoint = wait.until(
//					    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-bullets']")));
					safeAct.safeFindElement(aboutThisItemBulletPoint);
					
					
					By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");
//					WebElement technicalDetailsBlockIndividualPage = wait.until(
//					    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='prodDetails']")));
					safeAct.safeFindElement(technicalDetailsBlockIndividualPage);
					
					
	                // Scroll and wait for the 'See More Product Details' button
	                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700);");
	                Thread.sleep(2000);

	                try {
//	                    WebElement seeMoreProductDetailsButtonIndividualPage = wait.until(ExpectedConditions.elementToBeClickable(
//	                            By.xpath("//a[@id='seeMoreDetailsLink']")));

//	                    ((JavascriptExecutor) driver).executeScript(
//	                            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);
//	                    Thread.sleep(500);
	                	
	                	By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");
						   
						WebElement seeMoreProductDetailsButtonIndividualPage = safeAct.safeFindElement(seeMoreProductDetailsButtonIndividualPageBy);
				        ((JavascriptExecutor) driver).executeScript(
				            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);

//	                    seeMoreProductDetailsButtonIndividualPage.click();
				        safeAct.safeClick(seeMoreProductDetailsButtonIndividualPageBy);
	                    System.out.println("'See More Details' clicked.");

	                } catch (Exception e1) {
	                    System.out.println("Unable to click the 'See More Details' button for filter " + str + " product " + productElement.getText());
	                    driver.close();
	                    driver.switchTo().window(currentWindow);
	                    continue; // move on to the next product
	                }

	                Thread.sleep(2000);

//	                System.out.println(productNameIndividualPage.getText() + "    productNameIndividualPage");
//	                System.out.println(productKeyFeatureBlock.getText() + "    productKeyFeatureBlock");
//	                System.out.println(aboutThisItemBulletPoint.getText() + "    aboutThisItemBulletPoint");
//	                System.out.println(technicalDetailsBlockIndividualPage.getText() + "    technicalDetailsBlockIndividualPage");

	                // Close the product details window and switch back to listing page
	                driver.close();
	                System.out.println("Closing product window and switching back to listing page");
	                driver.switchTo().window(currentWindow);

	            } catch (Exception e) {
	                System.out.println("Exception while processing product index " + p + ": " + e.getMessage());
	                // Attempt to close any popup if open and switch back safely
	                try {
	                    driver.close();
	                } catch (Exception closeEx) {
	                    // Ignore
	                }
	                driver.switchTo().window(currentWindow);
	                // continue to next product
	            }
	        }

	        // Clear the battery capacity filter before moving to the next option
//	        wait.until(ExpectedConditions.elementToBeClickable(
//	                By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
	        safeAct.safeClick(clearButton);
	        Thread.sleep(2000);
	    }
	}

	
	
		
	
	@Test(priority=3)
	public void verifyingDisplaySizeFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
          GenericUtility genericUtility=new GenericUtility();
          SafeActions safeAct=new SafeActions();
          
		
		if (!genericUtility.filterCheckUnderList("Display Size")) {
		    System.out.println("Filter option 'Display Size' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		
		
//		List<WebElement> listDisplaySizeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	    By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']")));
		
		
		By listDisplaySizeOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']");
		List<WebElement> listDisplaySizeOptions=safeAct.safeFindElements(listDisplaySizeOptionsBy);

		
		for (int i = 1; i < listDisplaySizeOptions.size(); i++) {
			System.out.println(listDisplaySizeOptions.get(i).getText() + "   size is  " + listDisplaySizeOptions.size());
		}
		
		
		for (int i = 1; i < listDisplaySizeOptions.size(); i++) {
			
//			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//			By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			List<WebElement> inloopParent=safeAct.safeFindElements(listDisplaySizeOptionsBy);
			
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//					"//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
//							+ str + "']"))).click();
			
			By filterName=By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base' and text()='"+ str + "']");
			safeAct.safeClick(filterName);
			

			
			Thread.sleep(2000);

			String currentWindow=driver.getWindowHandle();
			System.out.println("Printing current window  "+ currentWindow);
//		    List<WebElement> productNameListingPage  =	driver.findElements(By.xpath("//div[@data-cy='title-recipe']"));
		    
		    By productNameListingPageBy=By.xpath("//div[@data-cy='title-recipe']");
			List<WebElement> productNameListingPage=safeAct.safeFindElements(productNameListingPageBy);
		    
		    
			for(int p=1;p<productNameListingPage.size();p++) {
				System.out.println("inside the loop and product name is "+productNameListingPage.get(p).getText());
				
			//	driver.findElement(By.xpath("(//div[@data-cy='title-recipe'])["+p+"]")).click();
				
				By elementClickOnListingPage=By.xpath("(//div[@data-cy='title-recipe'])["+p+"]");
				safeAct.safeClick(elementClickOnListingPage);
				
				System.out.println("Clicked on the producct name new pop-up should open");
				Thread.sleep(2000);
				Set<String> allWindowHandles=driver.getWindowHandles();
				
				for(String e:allWindowHandles) {
					if(!e.equals(currentWindow)) {
						System.out.println("found the window switching now");

						driver.switchTo().window(e);
					}else {
						System.out.println("did not find the window trying again");
					}
				}
				
				
				System.out.println("Switched to the new window here");
				Thread.sleep(2000);

				

				By productNameIndividualPage=By.xpath("//span[@id='productTitle']");
//				WebElement productNameIndividualPage = wait.until(
//				    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
				safeAct.safeFindElement(productNameIndividualPage);
				
				

				By productKeyFeatureBlock= By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
//				WebElement productKeyFeatureBlock = wait.until(
//				    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")));
				safeAct.safeFindElement(productKeyFeatureBlock);
				
				
				By aboutThisItemBulletPoint=By.xpath("//div[@id='feature-bullets']");
//				WebElement aboutThisItemBulletPoint = wait.until(
//				    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='feature-bullets']")));
				safeAct.safeFindElement(aboutThisItemBulletPoint);
				
				
				By technicalDetailsBlockIndividualPage =By.xpath("//div[@id='prodDetails']");
//				WebElement technicalDetailsBlockIndividualPage = wait.until(
//				    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='prodDetails']")));
				safeAct.safeFindElement(technicalDetailsBlockIndividualPage);
				
			
				// Scroll and wait for the 'See More Product Details' button
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700);");
				Thread.sleep(2000);
						
				try {
//			        WebElement seeMoreProductDetailsButtonIndividualPage = wait.until(ExpectedConditions.elementToBeClickable(
//			            By.xpath("//a[@id='seeMoreDetailsLink']")));
					
					By seeMoreProductDetailsButtonIndividualPageBy=By.xpath("//a[@id='seeMoreDetailsLink']");
					   
					WebElement seeMoreProductDetailsButtonIndividualPage = safeAct.safeFindElement(seeMoreProductDetailsButtonIndividualPageBy);
			        ((JavascriptExecutor) driver).executeScript(
			            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);

			        Thread.sleep(500);

//			        seeMoreProductDetailsButtonIndividualPage.click();
			        safeAct.safeClick(seeMoreProductDetailsButtonIndividualPageBy);
			        System.out.println("'See More Details' clicked.");

			    } catch (Exception e1) {
			    	
			    //	System.out.println("Unable to click the show more details button and the filter and product is --?"+str+"   "+productNameListingPage.get(p).getText());
			    	driver.close();
			    	driver.switchTo().window(currentWindow);
			    	continue; // ✅ move on to the next product
			    }
			    
				
				Thread.sleep(2000);
				
//				System.out.println(productNameIndividualPage.getText()+"    productNameIndividualPage");
//				System.out.println(productKeyFeatureBlock.getText()+"    productKeyFeatureBlock");
//				System.out.println(aboutThisItemBulletPoint.getText()+"    aboutThisItemBulletPoint");
//		//		System.out.println(seeMoreProductDetailsButtonIndividualPage.getText()+"    seeMoreProductDetailsButtonIndividualPage");
//				System.out.println(technicalDetailsBlockIndividualPage.getText()+"    technicalDetailsBlockIndividualPage");

				
				for(String e:allWindowHandles) {
					if(e.equals(currentWindow)) {
						driver.close();
						System.out.println("Switching back to the listing page");
						driver.switchTo().window(e);
					}
				}
				
			}

			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(
//			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			By clearButtonBy=By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']");
			safeAct.safeClick(clearButtonBy);
			
			
		}
	}
	
	
	
	
	@Test(priority=0)
	public void verifyingProcessorSpeedFilterFunctionality() throws InterruptedException {
		
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		SafeActions safeAct=new SafeActions();
		ProductListingPage productPage=new ProductListingPage();
        GenericUtility genericUtility =new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Processor Speed")) {
		    return ;
		}
		
	
		List<WebElement> listProcessorSpeedOptions=safeAct.safeFindElements(productPage.listProcessorSpeedOptionsBy);
		genericUtility.printFilterNamesOnly(safeAct);
		
		
		for (int i = 1; i < listProcessorSpeedOptions.size(); i++) {
			
			List<WebElement> inloopParent=safeAct.safeFindElements(productPage.listProcessorSpeedOptionsBy);
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();			
			safeAct.safeClick(productPage.getFilterByName(str));
			Thread.sleep(1000);
			String currentWindow=driver.getWindowHandle();
			System.out.println("Printing current window  "+ currentWindow);
			
			
			List<WebElement> productNameListingPage=safeAct.safeFindElements(productPage.productNameListingPageBy);
			for(int p=1;p<productNameListingPage.size();p++) {
				
				System.out.println("inside the loop and product name is "+productNameListingPage.get(p).getText());				
				safeAct.safeClick(productPage.getProductByIndex(p));
				
				
				System.out.println("Clicked on the producct name new pop-up should open");
				Thread.sleep(2000);		
				productPage.switchToNewWindow(currentWindow);
				
				safeAct.safeFindElement(productPage.productNameIndividualPage);
				
				safeAct.safeFindElement(productPage.productKeyFeatureBlock);
				
				safeAct.safeFindElement(productPage.aboutThisItemBulletPoint);
				
				safeAct.safeFindElement(productPage.technicalDetailsBlockIndividualPage);
				
		        genericUtility.scrollByPixel(0, 700);
		    	
				try {					
				   
					WebElement seeMoreProductDetailsButtonIndividualPage = safeAct.safeFindElement(productPage.seeMoreProductDetailsButtonIndividualPageBy);
			        ((JavascriptExecutor) driver).executeScript(
			            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", seeMoreProductDetailsButtonIndividualPage);
			        Thread.sleep(500);

			        safeAct.safeClick(productPage.seeMoreProductDetailsButtonIndividualPageBy);
			        System.out.println("'See More Details' clicked.");

			    } catch (Exception e1) {
			  //  	System.out.println("Unable to click the show more details button and the filter and product is --?"+str+"   "+productNameListingPage.get(p).getText());
			    	driver.close();
			    	driver.switchTo().window(currentWindow);
			    	continue; // ✅ move on to the next product
			    }
			    
				Thread.sleep(2000);				
				genericUtility.closeCurrentWindowAndSwitchBack(currentWindow);	
			}
			safeAct.safeClick(productPage.clearButtonBy);
		}
	}
	
	
	
	@Test(priority=12)
	public void verifyingDisplayTypeFilterFunctionality() {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		SafeActions safeAct=new SafeActions();
		
		GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Display Type")) {
		    System.out.println("Filter option 'Display Type' does not exist in the list. Skipping the test.");
		    return ;
		}
		
		
		
		
//		List<WebElement> listDisplayTypeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	    By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']")));
		
		By listStorageCapacityOptionsBy=By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']");
		List<WebElement> listDisplayTypeOptions=safeAct.safeFindElements(listStorageCapacityOptionsBy);

		
		for (int i = 1; i < listDisplayTypeOptions.size(); i++) {
			System.out.println(listDisplayTypeOptions.get(i).getText() + "   size is  " + listDisplayTypeOptions.size());
		}
		
		
		for (int i = 1; i < listDisplayTypeOptions.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			//driver.navigate().refresh();
		}
	}
	
	
	//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base']
	
	
	
	
	@Test(priority=13)
	public void verifyingOperatingSystemVersionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
	
		GenericUtility genericUtility=new GenericUtility();	
		if (!genericUtility.filterCheckUnderList("Operating System", "Operating System Version")) {
		    System.out.println("Filter option 'Operating System' or 'Operating System Version' does not exist in the list. Skipping the test.");
		    return;
		}
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement moreInOperatingSystemVersion = wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//a[@aria-label='See more, Operating System Version']")));
		
		
	        JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", moreInOperatingSystemVersion);
			moreInOperatingSystemVersion.click();
		
			
		
		List<WebElement> listOperatingSystemVersionOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base']")));
		Thread.sleep(2000);
		
		
		for (int i = 1; i < listOperatingSystemVersionOptions.size(); i++) {
			System.out.println(listOperatingSystemVersionOptions.get(i).getText() + "   size is  " + listOperatingSystemVersionOptions.size());
		}
		
		
		for (int i = 1; i < listOperatingSystemVersionOptions.size(); i++) {
			
			       if(i!=1) {
			    	   js.executeScript("window.scrollBy(0, 1500);");
				        WebElement inLoopmoreInOperatingSystemVersion = wait.until(ExpectedConditions.elementToBeClickable(
				        By.xpath("//a[contains(@aria-label,'See more, Operating System')]")));
						
						js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", inLoopmoreInOperatingSystemVersion);
						Thread.sleep(2000);
						inLoopmoreInOperatingSystemVersion.click();
			       }
			
		
					
					
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_thirty-one_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
		}
	}
	
	//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base']
	
	
	
	
	@Test(priority=14)
	public void verifyingMobilePhonePrimaryCameraResolutionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
	
			
		GenericUtility genericUtility= new GenericUtility();
		
			

			if (!genericUtility.filterCheckUnderList("Mobile Phone Primary Camera Resolution")) {
			    System.out.println("Filter option 'Mobile Phone Primary Camera Resolution' does not exist in the list. Skipping the test.");
			    return;
			}
		
		
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
			
		
		List<WebElement> listMobilePhonePrimaryCameraResolutionOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base']")));
		
		
		
		for (int i = 1; i < listMobilePhonePrimaryCameraResolutionOptions.size(); i++) {
			System.out.println(listMobilePhonePrimaryCameraResolutionOptions.get(i).getText() + "   size is  " + listMobilePhonePrimaryCameraResolutionOptions.size());
		}
		
		
		for (int i = 1; i < listMobilePhonePrimaryCameraResolutionOptions.size(); i++) {
			
		
					
					
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			driver.navigate().refresh();
		}
	}
	
	
	
	
	@Test(priority=15)
	public void verifyingDiscountFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		
		GenericUtility genericUtility=new GenericUtility();
		if (!genericUtility.filterCheckUnderList("Discount")) {
		    System.out.println("Filter option 'Discount' does not exist in the list. Skipping the test.");
		    return ;
		}
		
			
		List<WebElement> listDiscountOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base']")));
		
		
		for (int i = 1; i < listDiscountOptions.size(); i++) {
			System.out.println(listDiscountOptions.get(i).getText() + "   size is  " + listDiscountOptions.size());
		}
		
		
		for (int i = 1; i < listDiscountOptions.size(); i++) {
				
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			driver.navigate().refresh();
		}
	}
}
