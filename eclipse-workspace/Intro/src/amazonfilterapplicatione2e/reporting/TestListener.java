package amazonfilterapplicatione2e.reporting;

import amazonfilterapplicatione2e.DriverManager;
import amazonfilterapplicatione2e.ScreenshotUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

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
        Throwable cause = result.getThrowable();

        // Log failure in Extent Report
        ExtentTestManager.getTest().log(Status.FAIL,
            "❌ Test Failed: " + testName + "<br><pre>" + cause + "</pre>");

        // No screenshot taken
        attachLogFile();             // 🔹 Still attach logs if needed
        attachScreenshotFolder(result); // 🔹 Still attach folder path for context if needed
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
                String absolutePathNew = "C:/Sagar/google-shopping-aggregator-automation/eclipse-workspace/Intro/logs/" + logFileName + ".log";

                String fileUrl = "file:///" + absolutePathNew.replace("\\", "/");

                ExtentTestManager.getTest().info("📄 <a href='" + fileUrl + "' target='_blank'>Click to view log file</a>");
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("⚠️ Failed to attach log file: " + e.getMessage());
        }
    }

//    private void attachScreenshotFolder(ITestResult result) {
//        try {
//            String testName = result.getMethod().getMethodName();
//            File testFolder = new File(SCREENSHOT_BASE_DIR + "/" + testName);
//            if (testFolder.exists()) {
//                String folderPath = testFolder.getAbsolutePath().replace("\\", "/");
//                String folderLink = "file:///" + folderPath;
//
//                // ✅ Collapsible section in Extent Report
//                ExtentTestManager.getTest().info(
//                    "<details><summary>📂 Open Screenshot Folder</summary><a href='" + folderLink + "' target='_blank'>Click Here</a></details>"
//                );
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("⚠️ Could not attach screenshot folder: " + e.getMessage());
//        }
//    }
    
    
    private void attachScreenshotFolder(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            File testFolder = new File(SCREENSHOT_BASE_DIR + "/" + testName);
            if (testFolder.exists()) {
                StringBuilder html = new StringBuilder();
                html.append("<details><summary>📂 Open Screenshot Folder</summary>");

                File[] files = testFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        String filePath = file.getAbsolutePath().replace("\\", "/");
                        String fileUrl = "file:///" + filePath;
                        html.append("<div><a href='").append(fileUrl)
                            .append("' target='_blank'>").append(file.getName()).append("</a></div>");
                    }
                } else {
                    html.append("<div>No screenshots found</div>");
                }

                html.append("</details>");

                // Attach it to the report
                ExtentTestManager.getTest().info(MarkupHelper.createLabel(html.toString(), ExtentColor.GREY));
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("⚠️ Could not attach screenshot folder: " + e.getMessage());
        }
    }

    

}
