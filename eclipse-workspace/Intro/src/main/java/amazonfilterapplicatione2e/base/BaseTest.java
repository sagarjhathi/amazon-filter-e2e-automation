package main.java.amazonfilterapplicatione2e.base;
import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import main.java.amazonfilterapplicatione2e.driverManager.DriverManager;
import main.java.amazonfilterapplicatione2e.logger.LoggerUtility;
import main.java.amazonfilterapplicatione2e.pathManager.PathManager;
import main.java.amazonfilterapplicatione2e.reporting.ReportManager;

public class BaseTest {
<<<<<<< Updated upstream
 
	private   Logger log = LoggerUtility.getLogger(BaseTest.class);
	protected WebDriver driver;
	@BeforeMethod
	public void setUp(Method method) {

		String testName = method.getName(); // The actual test method name
		ThreadContext.put("testName", testName);    
		ThreadContext.put("logFileName", testName); // must come before logger is called
		log = LogManager.getLogger(testName); 
		
		System.out.println("🧪 logFileName: " + ThreadContext.get("logFileName"));	    	   
		log.info("🔹 Starting test method: " + testName);
=======
 	
	
	public WebDriver driver;
	  
	  @BeforeSuite(alwaysRun = true)
	  public void createRunFolder() {
		  
	      String timestamp = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	      
	      String runPath= System.getProperty("user.dir")
	              + File.separator + "run_" + timestamp;
	      
	      System.out.println(runPath+"    RUN PATH HERE FOR TESTING");
	      
	      
		  PathManager.setRunFolderPath(runPath);
	      ReportManager.initReport(runPath);
	 
	      File runFolder = new File(runPath);
	      runFolder.mkdirs();
	  }

	    	    
	  
	  @BeforeMethod(alwaysRun = true)
	  public void beforeTest(ITestResult result) {
>>>>>>> Stashed changes

	      // Now safe to do anything else
	      DriverManager.initDriver();
	      driver = DriverManager.getDriver();

	      String path = PathManager.getRunFolderPath()
	              + File.separator + testName;

	      PathManager.setTestFolderPath(path);
	  }
	    
	  
	  
	  
	  
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTest(ITestResult result) {
		  
	      String testName = ThreadContext.get("testName");
	      System.out.println(testName+"    checking the test name being null");
	      	      
	      System.out.println("Looking logs in: " + PathManager.getLogPath(testName));
	      System.out.println("Looking screenshots in: " + PathManager.getScreenshotPath(testName));
	     
	      
	      ThreadContext.clearAll();
	      PathManager.clearTestFolder();
	      DriverManager.quitDriver();
	      
	  }


	
	
	
}
