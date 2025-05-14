package amazonfilterapplicatione2e;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorsTrial {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-agent=" + userAgent);
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--lang=en");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();

		
		//Clicking on the search bar
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).click();
		
		//Giving input
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")))
				.sendKeys("Mobile");
		
		//clicking on search button 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nav-search-submit-button']")))
				.click();

		
		
		
//		//Fetching the list of filter options we have 
////		List<WebElement> filterOptions=driver.findElements(By.xpath("//div[@id='s-refinements']//span[@class='a-size-base a-color-base puis-bold-weight-text']"));
////		
////		
////		//Printing the list to visually see the list
////		for(int i=0;i<filterOptions.size();i++) {
////			System.out.println(filterOptions.get(i).getText()+"   printing the list of filter options exist");
////			if(filterOptions.get(i).getText().trim().equalsIgnoreCase("brands")) {
////				System.out.println("Filter option exist in the list");
////			}
////		}
//		
//		
//		
//		Set<String> clickedOptions = new HashSet<>();
//		int j = 0;
//
//		while (true) {
//		    List<WebElement> deliveryOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//		        By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));
//
//		    if (j >= deliveryOptions.size()) {
//		        System.out.println("Finished looping through all delivery options.");
//		        break;
//		    }
//
//		    String str = deliveryOptions.get(j).getText().trim();
//
//		    // Skip duplicate labels already clicked
//		    if (clickedOptions.contains(str) || str.isEmpty()) {
//		        j++;
//		        continue;
//		    }
//
//		    System.out.println("Clicking on delivery option: " + str);
//		    clickedOptions.add(str); // mark as clicked
//
//		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//		        "//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base' and normalize-space(text())='" + str + "']"))).click();
//
//		    Thread.sleep(3000);
//
//		    // Clear filter
//		    wait.until(ExpectedConditions.elementToBeClickable(
//		        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
//
//		    wait.until(ExpectedConditions.invisibilityOfElementLocated(
//		        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']")));
//
//		    Thread.sleep(1500);
//		    j++;
//		}
//
//
//
//		
//		
//		
//
//		
//		//Delivery filter option functionality verification
////		List<WebElement> listDeliveryOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////				By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));
////
////		for (int i = 0; i < listDeliveryOptions.size(); i++) {
////			System.out.println(listDeliveryOptions.get(i).getText() + "   size is  " + listDeliveryOptions.size());
////		}
////		
////		
////		
////		for (int i = 0; i < listDeliveryOptions.size(); i++) {
////			
////			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////					By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));
////
////			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
////
////			String str = inloopParent.get(i).getText().trim();
////
////			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
////					"//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base' and text()='"
////							+ str + "']"))).click();
////
////			Thread.sleep(5000);
////			
////			wait.until(ExpectedConditions.elementToBeClickable(
////					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
////			Thread.sleep(2000);
////			
////		
////			
////			driver.navigate().refresh();
////		}
//		
//		
//		
//		
//		
//		
//		//Storage Capacity filter option functionality verification
//		
////		List<WebElement> listStorageCapacityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////				By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));
////
////		for (int i = 0; i < listStorageCapacityOptions.size(); i++) {
////			System.out.println(listStorageCapacityOptions.get(i).getText() + "   size is  " + listStorageCapacityOptions.size());
////		}
////		
////		for (int i = 0; i < listStorageCapacityOptions.size(); i++) {
////			
////			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////					By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));
////
////			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
////
////			String str = inloopParent.get(i).getText().trim();
////
////			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
////					"//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
////							+ str + "']"))).click();
////
////			wait.until(ExpectedConditions.elementToBeClickable(
////					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
////			driver.navigate().refresh();
////		}
//		
//		
//		
//		Set<String> clickedStorageOptions = new HashSet<>();
//		int k = 0;
//
//		while (true) {
//		    List<WebElement> storageOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//		        By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));
//
//		    if (k >= storageOptions.size()) {
//		        System.out.println("Finished looping through all storage capacity options.");
//		        break;
//		    }
//
//		    String str = storageOptions.get(k).getText().trim();
//
//		    if (clickedStorageOptions.contains(str) || str.isEmpty()) {
//		        k++;
//		        continue;
//		    }
//
//		    System.out.println("Clicking on storage option: " + str);
//		    clickedStorageOptions.add(str); // mark as clicked
//
//		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//		        "//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and normalize-space(text())='" + str + "']"))).click();
//
//		    Thread.sleep(3000); // optional wait after applying filter
//
//		    wait.until(ExpectedConditions.elementToBeClickable(
//		        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
//
//		    wait.until(ExpectedConditions.invisibilityOfElementLocated(
//		        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']")));
//
//		    Thread.sleep(1500); // buffer before re-evaluating
//		    k++;
//		}
//
//		
//		
//	
//		
//		
//		
//		
//		
//		//Brands filter option functionality verification
//		
//		//clicking on more button to fetch complete list of brands
////		WebElement moreInBrands = wait.until(ExpectedConditions.elementToBeClickable(
////				By.xpath("//a[@aria-label='See more, Brands']")));
////		moreInBrands.click();
////			
////		
////		
////		List<WebElement> listBrandOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////				By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));
////
////		
////		for (int i = 0; i < listBrandOptions.size(); i++) {
////			System.out.println(listBrandOptions.get(i).getText() + "   size is  " + listBrandOptions.size());
////		}
////		
////		
////       for (int i = 0; i < listBrandOptions.size(); i++) {
////    	  WebElement  moreBrands = wait.until(ExpectedConditions.elementToBeClickable(
////   				By.xpath("//a[@aria-label='See more, Brands']")));
////    	       JavascriptExecutor js=(JavascriptExecutor)driver;
////			
////				js.executeScript("arguments[0].scrollIntoView", moreBrands);
////				Thread.sleep(3000);
////				moreBrands.click();
////			
////			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
////					By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));
////
////			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());
////
////			String str = inloopParent.get(i).getText().trim();
////			
////			WebElement ele=driver.findElement(By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ str + "']"));
////			js.executeScript("arguments[0].scrollIntoView", ele);
////			Thread.sleep(4000);
////			
////			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
////					"//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"
////							+ str + "']"))).click();
////			
////			wait.until(ExpectedConditions.elementToBeClickable(
////					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
////			
////			driver.navigate().refresh();
////		}
//		
//		
//		
//		WebElement moreInBrands = wait.until(ExpectedConditions.elementToBeClickable(
//			    By.xpath("//a[@aria-label='See more, Brands']")));
//			moreInBrands.click();
//
//			Set<String> clickedBrands = new HashSet<>();
//			int l = 0;
//
//			while (true) {
//				
//				
//				WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(
//			    By.xpath("//a[@aria-label='See more, Brands']")));
//				ele.click();
//				
//				
//				
//			        List<WebElement> brandOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//			        By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));
//
//			        
//			        
//			    if (l >= brandOptions.size()) {
//			        System.out.println("Finished looping through all brand options.");
//			        break;
//			    }
//
//			    
//			    String str = brandOptions.get(l).getText().trim();
//			    if (clickedBrands.contains(str) || str.isEmpty()) {
//			        l++;
//			        continue;
//			    }
//
//			    System.out.println("Clicking on brand: " + str);
//			    clickedBrands.add(str); // mark as clicked
//
//			    JavascriptExecutor js = (JavascriptExecutor) driver;
//
//			    WebElement brandElement = wait.until(ExpectedConditions.elementToBeClickable(
//			        By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and normalize-space(text())='" + str + "']")));
//			    js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' })", brandElement);
//			    Thread.sleep(2000);
//			    brandElement.click();
//
//			    wait.until(ExpectedConditions.elementToBeClickable(
//			        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
//
//			    wait.until(ExpectedConditions.invisibilityOfElementLocated(
//			        By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']")));
//
//			    Thread.sleep(1500);
//			    l++;
//			}

			
			
			
			
			
			
			
			//WebElement maxSlider = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy", 0,300);
			Thread.sleep(2000);
			// Set min slider value
//			js.executeScript(
//			    "arguments[0].value = arguments[1];" +
//			    "arguments[0].dispatchEvent(new Event('input'));" +
//			    "arguments[0].dispatchEvent(new Event('change'));",
//			    minSlider, "0" // set min to 10 (₹ value depends on site's mapping)
//			);

//			// Set max slider value
//			js.executeScript(
//			    "arguments[0].value = arguments[1];" +
//			    "arguments[0].dispatchEvent(new Event('input'));" +
//			    "arguments[0].dispatchEvent(new Event('change'));",
//			    maxSlider, "90" // set max to 150
//			);
			

//			List<Integer> maxValues = Arrays.asList(50, 100, 150, 180);
//
//			WebElement maxSlider = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider"));
//
//		//    WebElement goButton=driver.findElement(By.xpath("//span[@id='a-autoid-124']"));
//
//			for (Integer value : maxValues) {
//			    js.executeScript(
//			        "arguments[0].value = arguments[1];" +
//			        "arguments[0].dispatchEvent(new Event('input'));" +
//			        "arguments[0].dispatchEvent(new Event('change'));",
//			        maxSlider, String.valueOf(value)
//			    );
//
//			    // Optional: Wait a bit to see it in action
//			    Thread.sleep(1000); // 1 second delay
////			    js.executeScript("arguments[0].click();", goButton);
//			    
//			    driver.findElement(By.xpath("//div[@class='a-section sf-submit-range-button']")).click();
//			    Thread.sleep(2000);
//			}
//
//
//

			
			
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

}
