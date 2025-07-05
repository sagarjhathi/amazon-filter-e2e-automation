package amazonfilterapplicatione2e;


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
	 
	 @BeforeMethod(alwaysRun = true)
	 public void setup(Method method, ITestContext context) {
	     // Set ThreadContext first
	     String methodName = method.getName();
	     String threadName = methodName + "-" + Thread.currentThread().threadId();
	     ThreadContext.put("threadName", threadName);
	     log.info("🔹 Starting test method: " + methodName);

	     // Then initialize driver
	     DriverManager.initDriver(); 
	     driver = DriverManager.getDriver();
	 }

	 
	 @AfterMethod(alwaysRun = true)
	 public void tearDown(ITestResult result) {
	     log.info("✅ Finished test method: " + result.getName());
	     DriverManager.quitDriver();
	     ThreadContext.clearAll();
	 }

	
	   
	    
	   


	  

	
	
}
