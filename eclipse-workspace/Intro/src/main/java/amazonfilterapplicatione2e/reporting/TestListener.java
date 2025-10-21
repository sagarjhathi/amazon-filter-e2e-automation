package main.java.amazonfilterapplicatione2e.reporting;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class TestListener implements ITestListener {

    //private static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
  //  public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

    private static final String SCREENSHOT_BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP;

    

    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
       
        // create a per-test file name (no extension)
//        String perTestName = testName; // or testName + "_" + timestamp if you want uniqueness
//        org.apache.logging.log4j.ThreadContext.put("logFileName", perTestName);
//        result.setAttribute("logFileName", perTestName);
//        ExtentTestManager.startTest(testName).log(Status.INFO, "Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
     //   ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
        attachLogFile(result);
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // Screenshot on failure
        String testName = result.getMethod().getMethodName();
        Throwable cause = result.getThrowable();

        // Log failure in Extent Report

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            String message = throwable.getMessage();

            if (message != null) {
                String formattedMessage = formatFailureMessage(message);
                ExtentTestManager.getTest().fail(formattedMessage);
            }
        }
        // No screenshot taken
        attachLogFile(result);             // 🔹 Still attach logs if needed
        attachScreenshotFolder(result); // 🔹 Still attach folder path for context if needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	if (result.getThrowable() != null) {
            ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
        } else {
            ExtentTestManager.getTest().skip("Test Skipped (No Exception)");
        }
        attachLogFile(result);
        attachScreenshotFolder(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }


    
//    
//    private void attachLogFile(ITestResult result) {
//        try {
//            // Build relative log file path (relative to project root)
//            String testName = result.getMethod().getMethodName();
//            String relativePath = "./logs/run_" + RUN_TIMESTAMP + "/" + testName + ".log";
//
//            File logFile = new File(System.getProperty("user.dir"), relativePath);
//            if (logFile.exists()) {
//                // use relative path directly in the href
//                ExtentTestManager.getTest()
//                    .info("📄 <a href='" + relativePath + "' target='_blank'>Open log file</a>");
//            } else {
//                ExtentTestManager.getTest()
//                    .info("⚠️ Log file not found: " + relativePath);
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest()
//                .warning("Failed to attach log file: " + e.getMessage());
//        }
//    }
    
//    private void attachLogFile(ITestResult result) {
//        try {
//            String testName = result.getMethod().getMethodName();
//
//            // 1️⃣  Build the absolute path to the log file in your root-level "logs" folder
//            Path logFilePath = Paths.get(
//                    System.getProperty("user.dir"),
//                    "logs",
//                    "run_" + ExtentManager.RUN_TIMESTAMP,
//                    testName + ".log"
//            );
//
//            File logFile = logFilePath.toFile();
//
//            // 2️⃣  Check if the file actually exists
//            if (!logFile.exists()) {
//                ExtentTestManager.getTest().info("⚠️ Log file not found: " + logFilePath);
//                return;
//            }
//
//            // 3️⃣  Compute a **relative path from the ExtentReports folder** to this log file
//            Path reportDir = Paths.get(
//                    System.getProperty("user.dir"),
//                    "test-output",
//                    "ExtentReports"
//            );
//
//            String relativePath = reportDir.relativize(logFilePath).toString().replace("\\", "/");
//
//            // 4️⃣  Add a clickable relative link into the Extent report
//            ExtentTestManager.getTest()
//                    .info("📄 <a href='" + relativePath + "' target='_blank'>Open log file</a>");
//
//        } catch (Exception e) {
//            ExtentTestManager.getTest()
//                    .warning("Failed to attach log file: " + e.getMessage());
//        }
//    }


    
    
//    private void attachLogFile(ITestResult result) {
//        try {
//            String perTestName = (String) result.getAttribute("logFileName");
//            if (perTestName == null) {
//                // fallback to method name if attribute missing
//                perTestName = result.getMethod().getMethodName();
//            }
//
//            // Build relative path to log file (project-root relative)
//            String relativePath = "./logs/run_" + ExtentManager.RUN_TIMESTAMP + "/" + perTestName + ".log";
//            File logFile = new File(System.getProperty("user.dir"), relativePath);
//
//            if (logFile.exists()) {
//                // compute path relative to report folder so links work when opening report bundle
//                Path reportDir = Paths.get(System.getProperty("user.dir"), "test-output", "ExtentReports");
//                String href;
//                try {
//                    href = reportDir.relativize(logFile.toPath()).toString().replace("\\", "/");
//                } catch (Exception ex) {
//                    href = relativePath.replace("\\", "/");
//                }
//                ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//            } else {
//                ExtentTestManager.getTest().info("⚠️ Log file not found: " + logFile.getAbsolutePath());
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
//        }
//    }


    
    
//    private void attachScreenshotFolder(ITestResult result) {
//        try {
//            String testName = result.getMethod().getMethodName();
//            String relativeFolderPath = "./test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//            String testFolderPath = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//            File testFolder = new File(testFolderPath);
//
//            if (testFolder.exists()) {
//                StringBuilder html = new StringBuilder();
//                html.append("<details><summary>📂 Open Screenshots</summary>");
//
//                File[] files = testFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
//                if (files != null && files.length > 0) {
//                    for (File file : files) {
//                        String filePath = file.getAbsolutePath().replace("\\", "/");
//                        String fileUrl = "file:///" + filePath;
//                        String fileName = file.getName(); // 📌 This is the screenshot file name
//
//                        // Embed image with file name as label
//                        html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
//                            .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(fileName).append("</div>")
//                            .append("<a href='").append(fileUrl).append("' target='_blank'>")
//                            .append("<img src='").append(fileUrl).append("' style='max-width:600px; border:1px solid #ddd;'/>")
//                            .append("</a></div>");
//                    }
//                } else {
//                    html.append("<div>No screenshots found</div>");
//                }
//
//                html.append("</details>");
//
//                ExtentTestManager.getTest().info(html.toString());
//                
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
//        }
//    }
    
    
    
    
    
//    private void attachLogFile(ITestResult result) {
//        try {
//            if (result == null) return;
//            String testName = result.getMethod().getMethodName();
//
//            // 1) Try expected exact path first (fast)
//            Path expected = Paths.get(System.getProperty("user.dir"),
//                                      "logs",
//                                      "run_" + ExtentManager.RUN_TIMESTAMP,
//                                      testName + ".log");
//            File found = expected.toFile();
//            if (!found.exists()) {
//                // 2) Fallback: deep search under ./logs for any file whose name contains the testName
//                File logsRoot = new File(System.getProperty("user.dir"), "logs");
//                if (logsRoot.exists() && logsRoot.isDirectory()) {
//                    File best = null;
//                    long bestTime = 0L;
//                    java.util.Queue<File> q = new java.util.ArrayDeque<>();
//                    q.add(logsRoot);
//                    while (!q.isEmpty()) {
//                        File dir = q.poll();
//                        File[] children = dir.listFiles();
//                        if (children == null) continue;
//                        for (File c : children) {
//                            if (c.isDirectory()) q.add(c);
//                            else {
//                                String name = c.getName().toLowerCase();
//                                if (name.contains(testName.toLowerCase()) && name.endsWith(".log")) {
//                                    long t = c.lastModified();
//                                    // pick the most recently modified candidate
//                                    if (best == null || t > bestTime) {
//                                        best = c;
//                                        bestTime = t;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (best != null) found = best;
//                }
//            }
//
//            if (found != null && found.exists()) {
//                // compute relative href from report folder so links remain portable
//                Path reportDir = Paths.get(System.getProperty("user.dir"), "test-output", "ExtentReports");
//                Path target = found.toPath().toAbsolutePath();
//                String href;
//                try {
//                    href = reportDir.relativize(target).toString().replace("\\", "/");
//                } catch (Exception e) {
//                    // fallback: project-root relative path
//                    Path proj = Paths.get(System.getProperty("user.dir"));
//                    href = "./" + proj.relativize(target).toString().replace("\\", "/");
//                }
//
//                ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//            } else {
//                ExtentTestManager.getTest().info("⚠️ Log file not found: " + expected.toAbsolutePath().toString());
//            }
//        } catch (Exception ex) {
//            ExtentTestManager.getTest().warning("Could not attach log file: " + ex.getMessage());
//        }
//    }

    
//    private void attachLogFile(ITestResult result) {
//        try {
//            if (result == null) return;
//
//            // 1) determine per-test filename (set earlier in onTestStart())
//            String perTestName = (String) result.getAttribute("logFileName");
//            if (perTestName == null || perTestName.isEmpty()) {
//                perTestName = result.getMethod().getMethodName();
//            }
//
//            // 2) absolute path to the log file (project-root/logs/run_<timestamp>/<perTestName>.log)
//            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
//            Path logFilePath = projectRoot.resolve(Paths.get("logs", "run_" + ExtentManager.RUN_TIMESTAMP, perTestName + ".log"));
//
//            File logFile = logFilePath.toFile();
//            if (!logFile.exists()) {
//                ExtentTestManager.getTest().info("⚠️ Log file not found: " + logFilePath.toString());
//                return;
//            }
//
//            // 3) candidate report directories (where ExtentReport.html may live)
//            // prefer the test-output/ExtentReports folder, but also try project root and artifacts folder
//            Path[] candidates = new Path[] {
//                projectRoot.resolve(Paths.get("test-output", "ExtentReports")),     // typical local output
//                projectRoot.resolve("ExtentReports"),                              // sometimes copied to repo root/ExtentReports
//                projectRoot,                                                       // if report is at repo root (index.html)
//            };
//
//            String href = null;
//            for (Path reportDir : candidates) {
//                try {
//                    if (reportDir == null) continue;
//                    Path reportDirAbs = reportDir.toAbsolutePath();
//                    // only attempt if folder exists OR it's a plausible target (we still test relativize)
//                    Path relative = reportDirAbs.relativize(logFilePath.toAbsolutePath());
//                    String relStr = relative.toString().replace("\\", "/");
//                    // Ensure no accidental leading slash (so it's not absolute to the site root)
//                    if (relStr.startsWith("/")) relStr = relStr.substring(1);
//
//                    // Accept this relative path (it may contain ../ which is fine)
//                    href = relStr;
//                    break;
//                } catch (Exception e) {
//                    // ignore and try next candidate
//                }
//            }
//
//            // 4) fallback: project-root relative (safe)
//            if (href == null) {
//                Path projRel = projectRoot.relativize(logFilePath.toAbsolutePath());
//                href = "./" + projRel.toString().replace("\\", "/");
//            }
//
//            // 5) add link to Extent report (relative link)
//            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
//        }
//    }
    
    private void attachLogFile(ITestResult result) {
        try {
            if (result == null) return;

            // 1) determine per-test filename (set earlier in onTestStart())
            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }

            // 2) absolute path to the log file (project-root/logs/run_<timestamp>/<perTestName>.log)
            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
            Path logFilePath = projectRoot.resolve(Paths.get("logs", "run_" + ExtentManager.RUN_TIMESTAMP, perTestName + ".log"));

            File logFile = logFilePath.toFile();
            if (!logFile.exists()) {
                // If not found, report exact expected absolute path for diagnosis
                ExtentTestManager.getTest().info("⚠️ Log file not found: " + logFilePath.toString());
                return;
            }

            // 3) candidate report directories (where ExtentReport.html may live)
            Path[] candidates = new Path[] {
                projectRoot.resolve(Paths.get("test-output", "ExtentReports")), // typical local output
                projectRoot.resolve("ExtentReports"),                           // sometimes copied to repo root/ExtentReports
                projectRoot                                                     // if report is at repo root (index.html)
            };

            String href = null;
            for (Path reportDir : candidates) {
                try {
                    if (reportDir == null) continue;
                    Path reportDirAbs = reportDir.toAbsolutePath();
                    // compute relative path from report dir to log file
                    Path relative = reportDirAbs.relativize(logFilePath.toAbsolutePath());
                    String relStr = relative.toString().replace("\\", "/");
                    if (relStr.startsWith("/")) relStr = relStr.substring(1);
                    href = relStr;
                    break;
                } catch (Exception e) {
                    // ignore and try next candidate
                }
            }

            // fallback: project-root relative (safe)
            if (href == null) {
                Path projRel = projectRoot.relativize(logFilePath.toAbsolutePath());
                href = "./" + projRel.toString().replace("\\", "/");
            }

            // 4) add relative link to Extent report
            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
        }
    }
    
    
    
    
    
    private void attachScreenshotFolder(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            String relativeFolderPath = "./screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
            String absoluteFolderPath = System.getProperty("user.dir") + "/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
            File testFolder = new File(absoluteFolderPath);

            if (testFolder.exists()) {
                StringBuilder html = new StringBuilder();
                html.append("<details><summary>📂 Open Screenshots (" + testName + ")</summary>");

                File[] files = testFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        String relativePath = relativeFolderPath + "/" + file.getName();
                        html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
                            .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(file.getName()).append("</div>")
                            .append("<a href='").append(relativePath).append("' target='_blank'>")
                            .append("<img src='").append(relativePath).append("' style='max-width:600px; border:1px solid #ddd;'/>")
                            .append("</a></div>");
                    }
                } else {
                    html.append("<div>No screenshots found</div>");
                }

                html.append("</details>");
                ExtentTestManager.getTest().info(html.toString());
            } else {
                ExtentTestManager.getTest().info("⚠️ Screenshot folder not found: " + relativeFolderPath);
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
        }
    }

   
    

    private String formatFailureMessage(String message) {
        // Optional: detect structured failure message by keywords
        if (message.contains("Brand filter") && message.contains("📦 Title:")) {

            // Split for better readability
            String[] lines = message.split("\n");
            String filter = lines[0]; // first line like Brand filter '...' not found

            // Build collapsible HTML
            StringBuilder html = new StringBuilder();
            html.append("<b>").append(filter).append("</b>");
            html.append("<details><summary>📄 Click to expand product details</summary>");

            for (int i = 1; i < lines.length; i++) {
                html.append(lines[i].replace("\n", "<br>")).append("<br>");
            }

            html.append("</details>");
            return html.toString();
        }

        // If no special format, return as-is
        return message;
    }


    

}
