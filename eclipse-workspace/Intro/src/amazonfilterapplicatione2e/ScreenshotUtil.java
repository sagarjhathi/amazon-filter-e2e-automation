package amazonfilterapplicatione2e;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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

    public static String capture(WebDriver driver, String testName, String filterValue, int productIndex) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String folderPath = BASE_DIR + "/" + testName;
        new File(folderPath).mkdirs();  // Create sub-folder per test

        String fileName = "Filter-" + filterValue + "-Index-" + productIndex + "__" + timestamp + ".png";
        String fullPath = folderPath + "/" + fileName;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
            return fullPath; // Use this path in report
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
