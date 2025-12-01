package main.java.amazonfilterapplicatione2e.base;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import main.java.amazonfilterapplicatione2e.driverManager.DriverManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;




@Listeners(main.java.amazonfilterapplicatione2e.reporting.TestListener.class)
public class BaseTest {
 
	private   Logger log = LoggerUtility.getLogger(BaseTest.class);


	
	 protected WebDriver driver;

	    @BeforeMethod
	    public void setUp(Method method) {
	        
	    	    String testName = method.getName(); // The actual test method name
	    	    String logName = method.getName() + "_" + Thread.currentThread().getId();
	    	    ThreadContext.put("testName", testName);     // ✅ Add this for use in logs
	    	    ThreadContext.put("logFileName", testName); // ✅ must come before logger is called
	    	    log = LogManager.getLogger(testName); 
	    	    System.out.println("🧪 logFileName: " + ThreadContext.get("logFileName"));	    	   
	    	    log.info("🔹 Starting test method: " + testName);
	    	    
	        DriverManager.initDriver();
	        driver = DriverManager.getDriver();
	    }
	 
	    @AfterMethod
	    public void tearDown(ITestResult result) {
	        log.info("✅ Finished test method: " + result.getName());
	        ThreadContext.clearAll();  // ✅ Critical to avoid context bleed
	        DriverManager.quitDriver();
	    }
	   
	   
	
	
	
}
