package amazonfilterapplicatione2e;


import java.io.File;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.ThreadContext;



public class BaseTest {
 
	private static final Logger log = LoggerUtility.getLogger(BaseTest.class);

	
	 protected WebDriver driver;

//	    @BeforeMethod
//	    public void setUp() {
//	        // Initialize WebDriver instance using DriverManager
//	    	DriverManager.initDriver(); 
//	        driver = DriverManager.getDriver();
//	    }
//
//	    @AfterMethod
//	    public void tearDown() {
//	        // Quit WebDriver instance after test
//	        DriverManager.quitDriver();
//	    }
//	 
//
//	    @BeforeMethod(alwaysRun = true)
//	    public void setLoggingContext(Method method) {
//	        String threadName = method.getName() + "-" + Thread.currentThread().threadId();
//	        ThreadContext.put("threadName", threadName);
//	        log.info("🔹 Starting test method: " + method.getName());
//	    }
//
//	 
//	    @AfterMethod(alwaysRun = true)
//	    public void clearLoggingContext(ITestResult result) {
//	        log.info("✅ Finished test method: " + result.getName());
//	        ThreadContext.clearAll();
//	    }


	    @BeforeMethod
	    public void setUp(Method method) {
	        // ✅ Assign unique thread name for routing log
	    	 String testName = method.getName(); // The actual test method name
	    	    String threadName = testName + "-" + Thread.currentThread().threadId();

	    	    ThreadContext.put("threadName", threadName); // Used in file name routing (if needed)
	    	    ThreadContext.put("testName", testName);     // ✅ Add this for use in logs
	    	   
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
