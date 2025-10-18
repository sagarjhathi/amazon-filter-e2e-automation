package main.java.amazonfilterapplicatione2e.reporting;


import java.io.File;
import java.text.SimpleDateFormat; 
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

	import java.text.SimpleDateFormat;
	import java.util.Date;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentManager {

	    private static ExtentReports extent; // keep private
	   // public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date());
	 // include dashes between hour/minute as required: yyyy-MM-dd-HH-mm
	 // ExtentManager.java
	    public static final String RUN_TIMESTAMP = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());

	    public static final String BASE_SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/Run_" + RUN_TIMESTAMP;
	    public static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";

	    // synchronized to be safe in parallel test startup
	    public synchronized static ExtentReports getInstance() {
	        if (extent == null) {
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
	            sparkReporter.config().setDocumentTitle("Automation Report");
	            sparkReporter.config().setReportName("Amazon Filter Automation Report");
	            sparkReporter.config().setTheme(Theme.STANDARD);

	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	            extent.setSystemInfo("Tester", "Sagar Hathi");
	            System.setProperty("logs.dir", System.getProperty("user.dir") + "/logs/run_" + ExtentManager.RUN_TIMESTAMP);
	          //  new File(System.getProperty("logs.dir")).mkdirs();

	        }
	        return extent;
	    }

	    // convenience helper
	    public synchronized static void flush() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	}



//	import java.io.IOException;
//	import java.nio.file.Files;
//	import java.nio.file.Path;
//	import java.text.SimpleDateFormat;
//	import java.util.Date;
//
//	import com.aventstack.extentreports.ExtentReports;
//	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//	import com.aventstack.extentreports.reporter.configuration.Theme;
//
//	public class ExtentManager {
//
//	    private static ExtentReports extent; // keep private
//
//	    // include seconds to avoid timestamp mismatch with folders that include seconds
//	    public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
//
//	    public static final String BASE_SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/Run_" + RUN_TIMESTAMP;
//	    public static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReports/Report_" + RUN_TIMESTAMP + ".html";
//
//	    // synchronized to be safe in parallel test startup
//	    public synchronized static ExtentReports getInstance() {
//	        if (extent == null) {
//	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
//	            sparkReporter.config().setDocumentTitle("Automation Report");
//	            sparkReporter.config().setReportName("Amazon Filter Automation Report");
//	            sparkReporter.config().setTheme(Theme.STANDARD);
//
//	            extent = new ExtentReports();
//	            extent.attachReporter(sparkReporter);
//
//	            extent.setSystemInfo("OS", System.getProperty("os.name"));
//	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
//	            extent.setSystemInfo("Tester", "Sagar Hathi");
//
//	            // set logs.dir so Log4j2 ${sys:logs.dir} resolves to logs/run_<timestamp>
//	            String logsDir = System.getProperty("user.dir") + "/logs/run_" + RUN_TIMESTAMP;
//	            System.setProperty("logs.dir", logsDir);
//
//	            // also optionally expose runTimestamp system property if you use it in log4j2.xml
//	            System.setProperty("runTimestamp", RUN_TIMESTAMP);
//
//	            // create the directory
//	            try {
//	                Files.createDirectories(Path.of(logsDir));
//	            } catch (IOException e) {
//	                // don't crash test init — print to stderr and continue
//	                System.err.println("Warning: could not create logs directory '" + logsDir + "': " + e.getMessage());
//	            }
//	        }
//	        return extent;
//	    }
//
//	    // convenience helper
//	    public synchronized static void flush() {
//	        if (extent != null) {
//	            extent.flush();
//	        }
//	    }
//	}

	

