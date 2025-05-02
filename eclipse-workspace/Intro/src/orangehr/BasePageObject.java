package orangehr;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Wait<WebDriver> fluentWait;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    // Retry mechanism for clicking elements
    protected void clickWithRetry(WebElement element, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                if (attempts == maxRetries) {
                    throw e;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new WebDriverException("Thread was interrupted", ie);
                }
            }
        }
    }

    // Retry mechanism for sending keys
    protected void sendKeysWithRetry(WebElement element, String text, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.clear();
                element.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == maxRetries) {
                    throw e;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new WebDriverException("Thread was interrupted", ie);
                }
            }
        }
    }

    // Get text with retry
    protected String getTextWithRetry(WebElement element, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                return element.getText();
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == maxRetries) {
                    throw e;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new WebDriverException("Thread was interrupted", ie);
                }
            }
        }
        return null;
    }

    // Wait for page load completion
    protected void waitForPageLoadComplete() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    // Find element with retry using function
    protected WebElement findWithRetry(Function<WebDriver, WebElement> findFunction) {
        return fluentWait.until(findFunction);
    }
}