package amazonfilterapplicatione2e;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

public class CaptchaHandler extends BasePage{

	
	  public  boolean isCaptchaPage() {
	        try {
	            String src = driver.getPageSource().toLowerCase();

	            if (src.contains("click the button below to continue shopping")) return true;
	            if (src.contains("/errors/validatecaptcha")) return true;

	            List<WebElement> contButtons = driver.findElements(By.xpath(
	                "//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']"
	            ));
	            for (WebElement b : contButtons) {
	                if (b.isDisplayed()) return true;
	            }

	        } catch (Exception e) {
	            System.err.println("Captcha detection failed: " + e.getMessage());
	        }
	        return false;
	    }

	    // Try to handle captcha by clicking or refreshing
	    public  void handleCaptcha() {
	        int maxRetries = 3;
	        int attempt = 0;
	        long waitMs = 1000L;

	        while (attempt < maxRetries) {
	            if (!isCaptchaPage()) return;

	            attempt++;
	            System.out.println("Captcha detected. Attempt " + attempt);

	            // Try clicking "Continue shopping"
	            try {
	                List<WebElement> contButtons = driver.findElements(By.xpath(
	                    "//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']"
	                ));
	                for (WebElement b : contButtons) {
	                    if (b.isDisplayed() && b.isEnabled()) {
	                        System.out.println("Clicking 'Continue shopping' button...");
	                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", b);
	                        b.click();

	                        new WebDriverWait(driver, Duration.ofSeconds(15))
	                            .until((ExpectedCondition<Boolean>) wd ->
	                                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	                        Thread.sleep(1000);

	                        if (!isCaptchaPage()) {
	                            System.out.println("Captcha cleared after button click.");
	                            return;
	                        }
	                    }
	                }
	            } catch (Exception e) {
	                System.err.println("Click attempt failed: " + e.getMessage());
	            }

	            // If still captcha, try refresh
	            try {
	                driver.navigate().refresh();
	                new WebDriverWait(driver, Duration.ofSeconds(20))
	                    .until((ExpectedCondition<Boolean>) wd ->
	                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	                Thread.sleep(waitMs);

	                if (!isCaptchaPage()) {
	                    System.out.println("Captcha cleared after refresh.");
	                    return;
	                }
	            } catch (Exception e) {
	                System.err.println("Refresh attempt failed: " + e.getMessage());
	            }

	            waitMs = Math.min(waitMs * 2, 8000L); // exponential backoff
	        }

	        // If still captcha after retries
	      
	        throw new SkipException("Captcha persisted after retries. Skipping test.");
	    }
}
