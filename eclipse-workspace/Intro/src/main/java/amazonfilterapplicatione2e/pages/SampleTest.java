package main.java.amazonfilterapplicatione2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		
		driver.findElement(By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and text()='Operating System']/parent::div/following-sibling::ul"));
		
	}

}
