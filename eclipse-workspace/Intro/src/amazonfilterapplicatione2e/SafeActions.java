package amazonfilterapplicatione2e;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SafeActions extends BasePage{
   

    public WebElement safeFindElement(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (StaleElementReferenceException | TimeoutException e) {
                System.out.println("Retrying element locate for: " + locator);
                attempts++;
            }
        }
        throw new RuntimeException("Element not found after multiple retries: " + locator);
    }

    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return;
            } catch (ElementClickInterceptedException | TimeoutException | StaleElementReferenceException e) {
                System.out.println("Retrying click for: " + locator);
                attempts++;
            }
        }
        throw new RuntimeException("Click failed after multiple retries: " + locator);
    }

    public String safeGetText(By locator) {
        return safeFindElement(locator).getText();
    }
}

