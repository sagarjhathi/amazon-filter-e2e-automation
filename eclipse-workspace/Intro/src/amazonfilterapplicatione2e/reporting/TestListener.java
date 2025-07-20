package amazonfilterapplicatione2e.reporting;

import amazonfilterapplicatione2e.DriverManager;
import amazonfilterapplicatione2e.ScreenshotUtil;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
    private static final String SCREENSHOT_BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + RUN_TIMESTAMP;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
        attachLogFile();
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        // Screenshot on failure
        String testName = result.getMethod().getMethodName();
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.capture(driver, testName, "General", 0);
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

        attachLogFile();
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "⚠️ Test Skipped");
        attachLogFile();
        attachScreenshotFolder(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    private void attachLogFile() {
        try {
            String logFileName = ThreadContext.get("logFileName");
            if (logFileName != null) {
                String absolutePath = System.getProperty("user.dir") + "/logs/" + logFileName + ".log";
                String fileUrl = "file:///" + absolutePath.replace("\\", "/");

                ExtentTestManager.getTest().info("📄 <a href='" + fileUrl + "' target='_blank'>Click to view log file</a>");
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("⚠️ Failed to attach log file: " + e.getMessage());
        }
    }

    private void attachScreenshotFolder(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            File testFolder = new File(SCREENSHOT_BASE_DIR + "/" + testName);
            if (testFolder.exists()) {
                String folderPath = testFolder.getAbsolutePath().replace("\\", "/");
                String folderLink = "file:///" + folderPath;

                ExtentTestManager.getTest().info("🖼️ <a href='" + folderLink + "' target='_blank'>Open Screenshot Folder</a>");
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("⚠️ Could not attach screenshot folder: " + e.getMessage());
        }
    }
}
