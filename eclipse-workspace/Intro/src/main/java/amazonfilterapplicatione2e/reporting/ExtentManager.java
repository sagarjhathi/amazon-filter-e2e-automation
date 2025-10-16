package main.java.amazonfilterapplicatione2e.reporting;


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
	    public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date());
	    public static final String BASE_SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/Run_" + RUN_TIMESTAMP;
	    public static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReports/Report_" + RUN_TIMESTAMP + ".html";

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


