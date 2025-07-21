package amazonfilterapplicatione2e;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import amazonfilterapplicatione2e.reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/latest";

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

    public static String capture(String testName, String filterValue, int productIndex) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());

        // Folder path to store screenshots (absolute path)
        String folderPath = "test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
        new File(folderPath).mkdirs();  // Create folder if not exists

        // File name with filter and index info
        String fileName = "Filter_" + filterValue + "_Index_" + productIndex + "__" + timestamp + ".png";

        // Full path to save file
        String fullPath = folderPath + "/" + fileName;
        WebDriver driver = DriverManager.getDriver();
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // ✅ Return relative path to be used in Extent Report
        String relativePath = "../screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName + "/" + fileName;
        return relativePath.replace("\\", "/");
    }

}
