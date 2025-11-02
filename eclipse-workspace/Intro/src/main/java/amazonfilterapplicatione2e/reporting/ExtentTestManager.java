package main.java.amazonfilterapplicatione2e.reporting;
import com.aventstack.extentreports.ExtentReports; 
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentTest startTest(String testName) {
        ExtentReports extent = ExtentManager.getInstance();
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void removeTest() {
        extentTest.remove();
    }
    
}

