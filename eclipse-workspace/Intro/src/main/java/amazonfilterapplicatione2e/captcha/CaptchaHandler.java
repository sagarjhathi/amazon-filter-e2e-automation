package main.java.amazonfilterapplicatione2e.captcha;
import java.time.Duration;   
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import main.java.amazonfilterapplicatione2e.base.BasePage;
import main.java.amazonfilterapplicatione2e.configManager.*;

public class CaptchaHandler extends BasePage{
	
	
	public boolean isCaptchaPage() {
	    try {
	        String src = driver.getPageSource().toLowerCase();

	        if (src.contains("click the button below to continue shopping") ||
	            src.contains("/errors/validatecaptcha")) {
	            return true;
	        }

	        return !driver.findElements(By.xpath(
	            "//*[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']"
	        )).isEmpty();

	    } catch (Exception e) {
	        return false;
	    }
	}

	
		
	public void handleCaptcha() {
	    int retries = ConfigManager.getInt("handleCaptcha.retries", 3);
	    long backoff = 1000;

	    for (int i = 1; i <= retries; i++) {

	        if (!isCaptchaPage()) return;

	        System.out.println("Captcha detected. Attempt " + i);

	        try {
	            List<WebElement> buttons = driver.findElements(By.xpath(
	                "//*[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']"
	            ));

	            if (!buttons.isEmpty()) {
	                WebElement button = buttons.get(0);

	                ((JavascriptExecutor) driver)
	                    .executeScript("arguments[0].scrollIntoView(true);", button);

	                new WebDriverWait(driver, Duration.ofSeconds(10))
	                        .until(d -> button.isDisplayed() && button.isEnabled());

	                button.click();
	            } else {
	                driver.navigate().refresh();
	            }

	            waitForPageLoad();
	            Thread.sleep(backoff);
	            backoff = Math.min(backoff * 2, 5000);

	        } catch (Exception e) {
	            System.out.println("Captcha handling attempt failed: " + e.getMessage());
	        }
	    }

	    throw new SkipException("Captcha persisted after retries.");
	}
	
	private void waitForPageLoad() {
	    int timeout = ConfigManager.getInt("pageLoad.timeout", 15);

	    new WebDriverWait(driver, Duration.ofSeconds(timeout))
	        .until(d -> ((JavascriptExecutor) d)
	        .executeScript("return document.readyState")
	        .equals("complete"));
	}
}
