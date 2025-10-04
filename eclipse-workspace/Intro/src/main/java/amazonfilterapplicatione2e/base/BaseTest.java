package main.java.amazonfilterapplicatione2e.base;
import java.io.File;
import main.java.amazonfilterapplicatione2e.*;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.ThreadContext;


@Listeners(main.java.amazonfilterapplicatione2e.reporting.TestListener.class)
public class BaseTest {
 
	 private   Logger log = LoggerUtility.getLogger(BaseTest.class);

	 protected WebDriver driver;

	    @BeforeMethod
	    public void setUp(Method method) {
	        // ✅ Assign unique thread name for routing log
	    	    String testName = method.getName(); // The actual test method name
	    	    String threadName = testName + "-" + Thread.currentThread().threadId();
	    	    String logName = method.getName() + "_" + Thread.currentThread().getId();
	    	    ThreadContext.put("logFileName", logName);  // ✅ Very important
	    	    ThreadContext.put("threadName", threadName); // Used in file name routing (if needed)
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
