package amazonfilterapplicatione2e.reporting;


import amazonfilterapplicatione2e.ScreenshotUtil;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.ThreadContext;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
        attachLogFile();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        // Screenshot on failure
        String testName = result.getMethod().getMethodName();
        String screenshotPath = ScreenshotUtil.captureScreenshot(testName);
        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

        attachLogFile();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "⚠️ Test Skipped");
        attachLogFile();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    private void attachLogFile() {
        try {
            String logFileName = ThreadContext.get("logFileName");
            if (logFileName != null) {
                // Absolute path version
                String absolutePath = System.getProperty("user.dir") + "/logs/" + logFileName + ".log";
                String fileUrl = "file:///" + absolutePath.replace("\\", "/");

                ExtentTestManager.getTest().info("📄 <a href='" + fileUrl + "' target='_blank'>Click to view log file</a>");
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("⚠️ Failed to attach log file: " + e.getMessage());
        }
    }

}

