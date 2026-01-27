package main.java.amazonfilterapplicatione2e.driverManager;
import java.net.URI; 
import java.net.URL;
import java.util.List; 
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.http.HttpHandler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;



import org.openqa.selenium.remote.http.Routable;

import java.util.function.Predicate;

import main.java.amazonfilterapplicatione2e.configManager.ConfigManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;


import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.Route;

import java.util.List;


public class DriverManager  {
	
	
	
	

	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	

	private  static Logger log = LoggerUtility.getLogger(DriverManager.class);

	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver() {
		if(driver.get()==null) {

			log.info("No existing WebDriver found for current thread. Initializing a new ChromeDriver...");

			try {            	
			
				String browser=System.getProperty("browser")!=null?System.getProperty("browser"):ConfigManager.get("browser", "chrome");
				if (browser != null) browser = browser.trim().toLowerCase(Locale.ENGLISH);

				switch (browser) {
				case "firefox": {

					org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();

					if (ConfigManager.getBoolean("firefox.arg.disable_gpu", true)) {
						firefoxOptions.addArguments("--disable-gpu");
					}
					if (ConfigManager.getBoolean("firefox.arg.disable_dev_shm_usage", true)) {
						firefoxOptions.addArguments("--disable-dev-shm-usage");
					}
					if (ConfigManager.getBoolean("firefox.arg.no_sandbox", true)) {
						firefoxOptions.addArguments("--no-sandbox");
					}
					if (ConfigManager.getBoolean("firefox.arg.disable_extensions", true)) {
						firefoxOptions.addArguments("--disable-extensions");
					}
					if (ConfigManager.getBoolean("firefox.headless", false)) {
						firefoxOptions.addArguments("-headless");
					}
					if(ConfigManager.getBoolean("disable.image.loading", false)) {
						firefoxOptions.addArguments(
							    "--blink-settings=imagesEnabled=false",
							    "--disable-gpu",
							    "--disable-extensions"
							);
					}


					try {
						io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
						log.info("WebDriverManager: geckodriver setup OK");
					} catch (Exception e) {
						log.warn("WDM geckodriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
						String local = ConfigManager.get("webdriver.firefox.local.path", "");
						if (!local.isBlank()) System.setProperty("webdriver.gecko.driver", local);
					}
				
					WebDriver firefoxDriver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
				
					
//					URI gridUri = URI.create("http://192.168.0.112:4444");
//					URL gridUrl = gridUri.toURL();
//
//					WebDriver firefoxDriver =
//					        new RemoteWebDriver(gridUrl, firefoxOptions);

					driver.set(firefoxDriver);
					try { firefoxDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}

					if (ConfigManager.getBoolean("firefox.arg.start_maximized", true)) {
						try { firefoxDriver.manage().window().maximize(); } catch (Exception ignored) {}
					}
					log.info("FirefoxDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
					break;
				}

				case "edge": {

					org.openqa.selenium.edge.EdgeOptions edgeOptions = new org.openqa.selenium.edge.EdgeOptions();

					if (ConfigManager.getBoolean("edge.arg.start_maximized", true)) {
						edgeOptions.addArguments("--start-maximized");
					}
					if (ConfigManager.getBoolean("edge.arg.disable_gpu", true)) {
						edgeOptions.addArguments("--disable-gpu");
					}
					if (ConfigManager.getBoolean("edge.arg.disable_dev_shm_usage", true)) {
						edgeOptions.addArguments("--disable-dev-shm-usage");
					}
					if (ConfigManager.getBoolean("edge.arg.no_sandbox", true)) {
						edgeOptions.addArguments("--no-sandbox");
					}
					if (ConfigManager.getBoolean("edge.arg.disable_extensions", true)) {
						edgeOptions.addArguments("--disable-extensions");
					}
					if(ConfigManager.getBoolean("chrome.headless", false)) {
						edgeOptions.addArguments("--headless");
					}
					if(ConfigManager.getBoolean("disable.image.loading", false)) {
						edgeOptions.addArguments(
							    "--blink-settings=imagesEnabled=false",
							    "--disable-gpu",
							    "--disable-extensions"
							);
					}

					boolean useExtEdge = ConfigManager.getBoolean("edge.use.automation.extension", false);
					edgeOptions.setExperimentalOption("useAutomationExtension", useExtEdge);

					try {

						io.github.bonigarcia.wdm.WebDriverManager.edgedriver().browserVersion("17134").setup();
						log.info("WebDriverManager: edgedriver setup OK");
					} catch (Exception e) {
						log.warn("WDM edgedriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
						String local = ConfigManager.get("webdriver.edge.local.path", "");
						if (!local.isBlank()) System.setProperty("webdriver.edge.driver", local);
					}
					System.setProperty("SE_MSEDGEDRIVER_MIRROR_URL", "https://msedgedriver.microsoft.com");
					log.info("Trying local fallback with == System.setProperty(\"SE_MSEDGEDRIVER_MIRROR_URL\", \"https://msedgedriver.microsoft.com\")");


					WebDriver edgeDriver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
					driver.set(edgeDriver);
					try { edgeDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}
					log.info("EdgeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
					break;
				}


				default: {

					org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();

					
					
					if (ConfigManager.getBoolean("chrome.arg.start_maximized", true)) {
						options.addArguments("--start-maximized");
					}
					if (ConfigManager.getBoolean("chrome.arg.disable_gpu", true)) {
						options.addArguments("--disable-gpu");
					}
					if (ConfigManager.getBoolean("chrome.arg.disable_blink_features_automation_controlled", true)) {
						options.addArguments("--disable-blink-features=AutomationControlled");
					}
					if (ConfigManager.getBoolean("chrome.arg.disable_dev_shm_usage", true)) {
						options.addArguments("--disable-dev-shm-usage");
					}
					if (ConfigManager.getBoolean("chrome.arg.no_sandbox", true)) {
						options.addArguments("--no-sandbox");
					}
					if (ConfigManager.getBoolean("chrome.arg.disable_extensions", true)) {
						options.addArguments("--disable-extensions");

					}
					if(ConfigManager.getBoolean("chrome.headless", false)) {
						options.addArguments("--headless");
					}
					if(ConfigManager.getBoolean("disable.image.loading", false)) {
						options.addArguments(
							    "--blink-settings=imagesEnabled=false",
							    "--disable-gpu",
							    "--disable-extensions"
							);
					}


					try {
						io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
						log.info("WebDriverManager: chromedriver setup OK");
					} catch (Exception e) {
						log.warn("WDM chromedriver setup failed: {}. Will try local fallback if configured.", e.getMessage());
						String local = ConfigManager.get("webdriver.chrome.local.path", "");
						if (!local.isBlank()) System.setProperty("webdriver.chrome.driver", local);
					}

					WebDriver chromeDriver = new org.openqa.selenium.chrome.ChromeDriver(options);
					
					

					driver.set(chromeDriver);
					
					try { chromeDriver.manage().deleteAllCookies(); } catch (Exception ignored) {}
					log.info("ChromeDriver initialized successfully for thread: {}", Thread.currentThread().threadId());
					break;
				}
				}


			}catch(Exception e) {
				log.error("Failed to initialize WebDriver: {}", e.getMessage(), e);
				throw new RuntimeException("WebDriver initialization failed", e);
			}

		} 
		else {
			log.info("Reusing existing WebDriver instance for thread: {}", Thread.currentThread().threadId());
		}

	}
	

	
	
	/** Quit and clean up WebDriver for current thread */
	public static void quitDriver() {
		WebDriver currentDriver = driver.get();

		if (currentDriver != null) {
			try {
				currentDriver.quit();
			} catch (Exception e) {
				
			} finally {
				driver.remove();
			}
		} else {
			log.warn("quitDriver() called but no WebDriver was found for thread: {}", Thread.currentThread().threadId());
		}
	}
	
	
	
	



}
