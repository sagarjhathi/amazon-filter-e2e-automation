package amazonfilterapplicatione2e;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	
	@Test(priority=1)
	public void verifyingGetItByTomorrowFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		
		    List<WebElement> filterOptions=driver.findElements(By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']"));
		    
		    for(int k=0;k<filterOptions.size();k++) {
		    	System.out.println(filterOptions.get(k).getText().trim());
		    }
		    
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Get It by Tomorrow")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}
			

			if (!exist) {
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
	
	
	
	
	
	
	@Test(priority=2)
	public void verifyingGetItIn2DaysFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		
		      List<WebElement> filterOptions=driver.findElements(By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']"));

			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Get It in 2 Days")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}
			

			if (!exist) {
			    System.out.println("Filter option 'Get It in 2 Days' does not exist in the list. Skipping the test.");
			    return;
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
	
	

	
	
	@Test(priority=3)
	public void verifyingGetItByTodayFilterFunctionality() throws InterruptedException{
		
		                    AmazonLandingPage am=new AmazonLandingPage();
		                    am.openingLandingPage();
		                    am.givingInputWithinSearchBar("Mobile");
		                    am.clickingOnSubmitSearchButton();
		
		
		    List<WebElement> filterOptions=driver.findElements(By.xpath("//ul[@id='filter-p_90']//span[@class='a-list-item']"));

			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Get It Today")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}
			

			if (!exist) {
			    System.out.println("Filter option 'Get It Today' does not exist in the list. Skipping the test.");
			    return;
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

	
	
	@Test(priority=4)
	public void verifyingTheBrandsFilterFunctionality() throws InterruptedException {
		
	
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
        List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-"
        		+ "size-base a-color-base puis-bold-weight-text']"));
		boolean exist = false;
		for (int i = 0; i < filterOptions.size(); i++) {
		    String text = filterOptions.get(i).getText().trim();
		    if (text.equalsIgnoreCase("brands")) {
		        System.out.println(text + "  matches with assert text here");
		        exist = true;
		        break;
		    }
		}
		
		

		if (!exist) {
		    System.out.println("Filter option 'Brands' does not exist in the list. Skipping the test.");
		    return;
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
				wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
				
				if(i==10 || i==20 || i==30) {
					
					driver.navigate().refresh();
					System.out.println("Refreshing the page here ");
				}
			}
		
	}
	
	
	
	@Test(priority=5)
	public void verifyingStorageCapacityFilterFunctionality() {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Storage Capacity")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> listStorageCapacityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));

		
		for (int i = 1; i < listStorageCapacityOptions.size(); i++) {
			System.out.println(listStorageCapacityOptions.get(i).getText() + "   size is  " + listStorageCapacityOptions.size());
		}
		
		
		for (int i = 1; i < listStorageCapacityOptions.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));

			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			//driver.navigate().refresh();
		}
	}
	
	
	@Test(priority=6)
	public void verifyingPriceSilderFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
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
					    
					    // Optional: Wait a bit to observe the change (adjust as needed)
					    Thread.sleep(2000); // Wait 1 second for each iteration
					}
					
	}
	
	
	@Test(priority =7)
	public void verifyingBatteryCapacityFilterFunctionality() {
		
		
		
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Battery Capacity")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> listBatteryCapacityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']")));

		
		for (int i = 1; i < listBatteryCapacityOptions.size(); i++) {
			System.out.println(listBatteryCapacityOptions.get(i).getText() + "   size is  " + listBatteryCapacityOptions.size());
		}
		
		
		for (int i = 1; i < listBatteryCapacityOptions.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base']")));

			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_thirty-five_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			//driver.navigate().refresh();
		}
	}
	
	
		
	
	@Test(priority=8)
	public void verifyingDisplaySizeFilterFunctionality() {
		
		
		
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Display Size")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> listDisplaySizeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']")));

		
		for (int i = 1; i < listDisplaySizeOptions.size(); i++) {
			System.out.println(listDisplaySizeOptions.get(i).getText() + "   size is  " + listDisplaySizeOptions.size());
		}
		
		
		for (int i = 1; i < listDisplaySizeOptions.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_six_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			//driver.navigate().refresh();
		}
	}
	
	
	
	
	@Test(priority=9)
	public void verifyingProcessorSpeedFilterFunctionality() {
		
		
		
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Processor Speed")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> listProcessorSpeedOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base']")));

		
		for (int i = 1; i < listProcessorSpeedOptions.size(); i++) {
			System.out.println(listProcessorSpeedOptions.get(i).getText() + "   size is  " + listProcessorSpeedOptions.size());
		}
		
		
		for (int i = 1; i < listProcessorSpeedOptions.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base']")));
			
			if(i>inloopParent.size()-1) {
				System.out.println("Avoiding out of bounds issue by traversing only upto the inloop size");
				return;
			}
			
			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
			String str = inloopParent.get(i).getText().trim();

			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			
			//driver.navigate().refresh();
		}
	}
	
	
	//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']
	
	@Test(priority=10)
	public void verifyingDisplayTypeFilterFunctionality() {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Display Size")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> listDisplayTypeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	    By.xpath("//ul[@id='filter-p_n_feature_thirty-four_browse-bin']//span[@class='a-size-base a-color-base']")));

		
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
	
	
	
	
	@Test(priority=11)
	public void verifyingOperatingSystemVersionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Operating System ") || text.equalsIgnoreCase("Operating System Version")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
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
		
		
		
		for (int i = 1; i < listOperatingSystemVersionOptions.size(); i++) {
			System.out.println(listOperatingSystemVersionOptions.get(i).getText() + "   size is  " + listOperatingSystemVersionOptions.size());
		}
		
		
		for (int i = 1; i < listOperatingSystemVersionOptions.size(); i++) {
			
			       if(i!=1) {
			    	   js.executeScript("window.scrollBy(0, 1500);");
				        WebElement inLoopmoreInOperatingSystemVersion = wait.until(ExpectedConditions.elementToBeClickable(
				        By.xpath("//a[@aria-label='See more, Operating System ']")));
						
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
			
			driver.navigate().refresh();
		}
	}
	
	//ul[@id='filter-p_n_feature_fourteen_browse-bin']//span[@class='a-size-base a-color-base']
	
	
	
	
	@Test(priority=12)
	public void verifyingMobilePhonePrimaryCameraResolutionFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Mobile Phone Primary Camera Resolution") || text.equalsIgnoreCase("Mobile Phone Primary Camera Resolution")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
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
	
	
	//ul[@id='filter-p_n_pct-off-with-tax']//span[@class='a-size-base a-color-base']
	
	
	@Test(priority=13)
	public void verifyingDiscountFilterFunctionality() throws InterruptedException {
		
		AmazonLandingPage am=new AmazonLandingPage();
		am.openingLandingPage();
		am.givingInputWithinSearchBar("Mobile");
		am.clickingOnSubmitSearchButton();
		
		
		  List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
			boolean exist = false;
			for (int i = 0; i < filterOptions.size(); i++) {
			    String text = filterOptions.get(i).getText().trim();
			    if (text.equalsIgnoreCase("Discount")) {
			        System.out.println(text + "  matches with assert text here");
			        exist = true;
			        break;
			    }
			}

			if (!exist) {
			    System.out.println("Filter option 'storage capacity' does not exist in the list. Skipping the test.");
			    return;
			}
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
			
		
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
