package amazonfilterapplicatione2e;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import amazonfilterapplicatione2e.reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class ScreenshotUtil {

    private static final String BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/latest";
	private  final static Logger log = LoggerUtility.getLogger(ScreenshotUtil.class);

    static {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) {
            try {
                FileUtils.deleteDirectory(baseDir);  // Delete previous run's screenshots
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        baseDir.mkdirs();  // Recreate
    }

//    public static String capture(String testName, String filterValue, int productIndex) {
//        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
//
//        // Folder path to store screenshots (absolute path)
//        String folderPath = "test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//        new File(folderPath).mkdirs();  // Create folder if not exists
//
//        // File name with filter and index info
//        String fileName = "Filter_" + filterValue + "_Index_" + productIndex + "__" + timestamp + ".png";
//
//        // Full path to save file
//        String fullPath = folderPath + "/" + fileName;
//        WebDriver driver = DriverManager.getDriver();
//        try {
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(src, new File(fullPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        // ✅ Return relative path to be used in Extent Report
//        String relativePath = "../screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName + "/" + fileName;
//        return relativePath.replace("\\", "/");
//    }
    
    public static String capture(String testName, String filterValue, int productIndex) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());

        String folderPath = "test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
        new File(folderPath).mkdirs();

        String fileName = "Filter_" + filterValue + "_Index_" + productIndex + "__" + timestamp + ".png";
        String fullPath = folderPath + "/" + fileName;
        WebDriver driver = DriverManager.getDriver();

        log.info("[{}] Attempting screenshot capture for test='{}', filter='{}', index={}", 
                 ThreadContext.get("testName"), testName, filterValue, productIndex);

        try {
            // Check if driver is responsive
            try {
                driver.getTitle();  // Will throw if session is invalid
            } catch (Exception e) {
                log.warn("[{}] WebDriver seems unresponsive. Skipping screenshot.", ThreadContext.get("testName"));
                return null;
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));

            log.info("[{}] Screenshot saved at: {}", ThreadContext.get("testName"), fullPath);
        } catch (WebDriverException we) {
            // Detect if it's likely a timeout from remote
            if (we.getMessage().toLowerCase().contains("timeout") || we.getCause() instanceof java.util.concurrent.TimeoutException) {
                log.error("[{}] Screenshot failed due to timeout: {}", ThreadContext.get("testName"), we.getMessage());
            } else {
                log.error("[{}] WebDriverException during screenshot: {}", ThreadContext.get("testName"), we.getMessage());
            }
            return null;
        } catch (IOException ioe) {
            log.error("[{}] IOException while saving screenshot: {}", ThreadContext.get("testName"), ioe.getMessage());
            return null;
        } catch (Exception e) {
            log.error("[{}] Unexpected exception while capturing screenshot: {}", ThreadContext.get("testName"), e.getMessage());
            return null;
        }

        String relativePath = "../screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName + "/" + fileName;
        return relativePath.replace("\\", "/");
    }



}
