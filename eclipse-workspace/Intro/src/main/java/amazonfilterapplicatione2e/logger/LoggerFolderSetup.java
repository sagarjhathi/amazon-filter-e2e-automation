package main.java.amazonfilterapplicatione2e.logger;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.BeforeSuite;

public class LoggerFolderSetup {

	
//	 @BeforeSuite(alwaysRun = true)
//	    public void createLogFolderForRun() {
//	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
//	        String logDir = "logs/run_" + timestamp;
//	        System.setProperty("logs.dir", logDir);
//
//	        File folder = new File(logDir);
//	        if (!folder.exists()) folder.mkdirs();
//
//	        new File(logDir + "/archive").mkdirs();
//	        System.out.println("✅ Logs will be stored under: " + logDir);
//
//	    }
	
	
	 @BeforeSuite(alwaysRun = true)
	    public void createLogFolderForRun() {
	        // prefer CI-provided runTimestamp if present, otherwise generate one locally
	        String runTs = System.getProperty("runTimestamp");
	        if (runTs == null || runTs.isEmpty()) {
	            runTs = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	        }

	        String logDir = "logs/run_" + runTs;
	        System.setProperty("logs.dir", logDir);  // keep Log4j2 happy if it reads logs.dir
	        File folder = new File(logDir);
	        if (!folder.exists()) folder.mkdirs();
	        new File(logDir + "/archive").mkdirs();

	        System.out.println("✅ Logs will be stored under: " + folder.getAbsolutePath());
	    }
	 
}
