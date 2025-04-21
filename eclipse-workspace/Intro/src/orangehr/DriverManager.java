package orangehr;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	import java.time.Duration;

	public class DriverManager {

	    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    // Always initializes ChromeDriver
	    public static WebDriver initDriver() {
	        if (driver.get() == null) {
	            driver.set(new ChromeDriver());
	            getDriver().manage().window().maximize();
	            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        }
	        return getDriver();
	    }

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
	}

