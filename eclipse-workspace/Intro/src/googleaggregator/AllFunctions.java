package googleaggregator;

import java.time.Duration;
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

public class AllFunctions {

    public static WebDriver initDriver(String userAgent) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + userAgent);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void setImplicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public static void clickOnSearchBarLandingPage(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.click();
    }

    public static void inputWithInTheSearchBar(WebDriver driver, String input) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.sendKeys(input);
    }

    public static void selectFromRecommendations(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='What are you looking for?']"));
        element.sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public static void scrollToSellerLandingPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement seller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lg3aE' and text()='Seller']")));
        js.executeScript("arguments[0].scrollIntoView(true);", seller);
    }

    public static void clickOnMoreInSellerMenuLandingPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement moreSellerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='More Seller']")));
        moreSellerButton.click();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollByPixels(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public static void selectAmazonFromSellerMenu(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement amazonInSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.lg3aE[title='Amazon.in']")));
        scrollByPixels(driver, 0, -300);
        amazonInSeller.click();
    }

    public static List<WebElement> getAllProducts(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'sh-pr__product-results-grid') and contains(@class, 'sh-pr__product-results')]")));
        return products.findElements(By.xpath("./*"));
    }
    
    public static void applyingPriceFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the price filter then re-running the function");
        WebElement priceFilter=driver.findElement(By.xpath("(//span[@class='lg3aE']/span[contains(text(),'₹')])[2]"));
        Base.applyFilterAndTraverse(driver, priceFilter, list);
    }
    
    public static void applyingRamFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the ram filter then re-running the function");
         WebElement ram=driver.findElement(By.xpath("(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'GB')])[5]"));
         Base.applyFilterAndTraverse(driver, ram, list);
    }
    
    
    public static void applyingRatingFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the rating  filter then re-running the function");
         WebElement moreFromProductRating=driver.findElement(By.xpath("(//div[text()='More'])[1]"));
         
         scrollToElement(driver,moreFromProductRating);
         scrollByPixels(driver, 0, -300);
         Thread.sleep(3000);
         moreFromProductRating.click();
         
         WebElement fourAndUp=driver.findElement(By.xpath("(//div[@class='vq3ore' and @aria-label='4 out of 5 stars'])[2]"));
         Base.applyFilterAndTraverse(driver, fourAndUp, list);
    }
    
    
    public static void applyingWeightfilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	System.out.println("Applying the weight filter then re-running the function");
        WebElement weight=driver.findElement(By.xpath("(//*[contains(text(), 'grams')])[1]"));
        Base.applyFilterAndTraverse(driver, weight, list);
    }
    
    
   
    
    public static void applyingDeliveryFilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	 System.out.println("Applying the delivery filter then re-running the function");
    	    WebElement delivery=driver.findElement(By.xpath("//span[@title='1–3 day delivery']"));
    	    Base.applyFilterAndTraverse(driver, delivery, list);
    }
    
    
    
    
    public static void applyingBrandFilter(WebDriver driver,List<WebElement> list ) throws InterruptedException {
    	System.out.println("Applying the mobile brand  filter then re-running the function");
        WebElement mobileBrand=driver.findElement(By.xpath("//span[@title='Realme']"));
        Base.applyFilterAndTraverse(driver, mobileBrand, list);
   }
    
    
    public static void applying5gFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the 5G filter then re-running the function");
        WebElement selecting5G =driver.findElement(By.xpath("//span[@title='5G']"));
        Base.applyFilterAndTraverse(driver, selecting5G, list);
        
    }
    
    
    
    
    public static void applyingDualSimFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the Dual Sim filter then re-running the function");
        WebElement dualSim=  driver.findElement(By.xpath("//span[@title='Dual SIM']"));
        Base.applyFilterAndTraverse(driver, dualSim, list);
    }
    
    
    public static void applyingColorFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the color filter then re-running the function");
         WebElement color=driver.findElement(By.xpath("//span[text()='Black']"));
         Base.applyFilterAndTraverse(driver, color, list);
    }
    
    
    public static void applyingStorageFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the storage filter then re-running the function");
         WebElement storageCapacity =driver.findElement(By.xpath("//span[text()='64 GB']"));
         Base.applyFilterAndTraverse(driver, storageCapacity, list);
    }
    
    
    public static void applyingOsFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the os filter then re-running the function");      
         WebElement os=driver.findElement(By.xpath("//span[text()='Android']"));
         Base.applyFilterAndTraverse(driver, os, list);
    }
    
    
    public static void applyingCellularNetworkFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the cellular Network filter then re-running the function");
        WebElement cellularNetwork=driver.findElement(By.xpath("//span[@title='GSM Network']"));
        Base.applyFilterAndTraverse(driver, cellularNetwork, list);
    }
    
    public static void applyingSecurityFeaturesFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the security features filter then re-running the function");
        WebElement securityFeatures=driver.findElement(By.xpath("//span[@title='Mobile Phones With Fingerprint Scanners']"));
        Base.applyFilterAndTraverse(driver, securityFeatures, list);
    }
    
    public static void applyingScreenResolutionFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the sreen resolution filter then re-running the function");      
        WebElement screenResolution = driver.findElement(By.xpath("//span[@title='2408 x 1080']"));
        Base.applyFilterAndTraverse(driver, screenResolution, list);
    }
    
    public static void applyingCameraFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	System.out.println("Applying the camera filter then re-running the function");
        WebElement camera= driver.findElement(By.xpath("(//a[@class='vjtvke ch6u2b']//span[@class='lg3aE' and contains(@title, 'MP')])[1]"));
        Base.applyFilterAndTraverse(driver, camera, list);
    }
    
    public static void applyingHeadphoneFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the headphone filter then re-running the function");
         WebElement headphoneConnector=driver.findElement(By.xpath("//span[@title='Headphone Jack']"));
         Base.applyFilterAndTraverse(driver, headphoneConnector, list);
    }
    
    public static void applyingLensFilter(WebDriver driver,List<WebElement> list) throws InterruptedException {
    	 System.out.println("Applying the lens filter then re-running the function");
         WebElement lensType=driver.findElement(By.xpath("//span[@title='Ultra Wide Angle']"));
         Base.applyFilterAndTraverse(driver, lensType, list);
    }
}
