package amazonfilterapplicatione2e;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']")))
				.sendKeys("Mobile");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nav-search-submit-button']")))
				.click();

		
		
		
		
		
		List<WebElement> parent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));

		for (int i = 0; i < parent.size(); i++) {
			System.out.println(parent.get(i).getText() + "   size is  " + parent.size());
		}
		
		
		

		
		
		List<WebElement> parentDelivery = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[@id='filter-p_90']//span[@class='a-size-base a-color-base']")));

		for (int i = 0; i < parentDelivery.size(); i++) {
			System.out.println(parentDelivery.get(i).getText() + "   size is  " + parent.size());
		}
		
		
		
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
		
		
		
		
		for (int i = 0; i < parent.size(); i++) {
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base']")));

			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());

			String str = inloopParent.get(i).getText().trim();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_n_feature_twenty-nine_browse-bin']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			driver.navigate().refresh();
		}
		
		
	
			
		WebElement moreInBrands = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[@aria-label='See more, Brands']")));
			
		moreInBrands.click();
			
		List<WebElement> parentBrand = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));

		for (int i = 0; i < parentBrand.size(); i++) {
			System.out.println(parentBrand.get(i).getText() + "   size is  " + parentBrand.size());
		}
		
			
		
       for (int i = 0; i < parentBrand.size(); i++) {
    	   moreInBrands = wait.until(ExpectedConditions.elementToBeClickable(
   				By.xpath("//a[@aria-label='See more, Brands']")));
    	   JavascriptExecutor js=(JavascriptExecutor)driver;
			
				js.executeScript("arguments[0].scrollIntoView", moreInBrands);
				Thread.sleep(3000);
				moreInBrands.click();
			
			List<WebElement> inloopParent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base']")));

			System.out.println(inloopParent.get(i).getText() + "   size is in loop " + inloopParent.size());

			String str = inloopParent.get(i).getText().trim();
			
			
			WebElement ele=driver.findElement(By.xpath("//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"+ str + "']"));
			js.executeScript("arguments[0].scrollIntoView", ele);
			Thread.sleep(4000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//ul[@id='filter-p_123']//span[@class='a-size-base a-color-base' and text()='"
							+ str + "']"))).click();
			

			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[@class='a-size-base a-color-base' and text()='Clear']"))).click();
			driver.navigate().refresh();
		}
			
			
		
	}

}
