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

    
    
    	
    
//    
//    
//    private void attachLogFile(ITestResult result) {
//        try {
//            if (result == null) return;
//
//            String perTestName = (String) result.getAttribute("logFileName");
//            if (perTestName == null || perTestName.isEmpty()) {
//                perTestName = result.getMethod().getMethodName();
//            }
//
//            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
//            Path expectedLog = projectRoot.resolve(Paths.get("logs", "run_" + ExtentManager.RUN_TIMESTAMP, perTestName + ".log"));
//
//            File logFile = expectedLog.toFile();
//            if (!logFile.exists()) {
//                // helpful diagnostic for CI logs and debugging
//                ExtentTestManager.getTest().info("⚠️ Log file not found at expected path: " + expectedLog.toString());
//                // attempt to find it anywhere under project logs (best-effort)
//                File logsRoot = projectRoot.resolve("logs").toFile();
//                if (logsRoot.exists()) {
//                    File found = null;
//                    long latest = 0;
//                    java.util.Queue<File> q = new java.util.ArrayDeque<>();
//                    q.add(logsRoot);
//                    while (!q.isEmpty()) {
//                        File d = q.poll();
//                        File[] children = d.listFiles();
//                        if (children == null) continue;
//                        for (File c : children) {
//                            if (c.isDirectory()) q.add(c);
//                            else if (c.getName().equalsIgnoreCase(perTestName + ".log")) {
//                                if (c.lastModified() > latest) { found = c; latest = c.lastModified(); }
//                            }
//                        }
//                    }
//                    if (found != null) logFile = found;
//                }
//                if (!logFile.exists()) return;
//            }
//
//            Path logPath = logFile.toPath().toAbsolutePath();
//
//            // Candidate report directories (where ExtentReport.html may be when published or in artifact)
//            Path[] reportCandidates = new Path[] {
//                projectRoot.resolve(Paths.get("test-output", "ExtentReports")),       // local default
//                projectRoot.resolve(Paths.get("artifacts", "extent", "ExtentReports")), // packaged artifact layout
//                projectRoot.resolve("ExtentReports"),                                 // sometimes placed at repo root
//                projectRoot                                                             // if report copied to repo root (index.html)
//            };
//
//            String href = null;
//            for (Path reportDir : reportCandidates) {
//                try {
//                    Path reportAbs = reportDir.toAbsolutePath();
//                    // only try relativize if reportDir exists or is plausible
//                    if (!reportAbs.toFile().exists()) {
//                        // still attempt relativize (works even if folder not present) but be safe
//                    }
//                    Path rel = reportAbs.relativize(logPath);
//                    String relStr = rel.toString().replace("\\", "/");
//                    if (relStr.startsWith("/")) relStr = relStr.substring(1);
//                    href = relStr;
//                    // if relStr contains ".." that's OK (relative link)
//                    break;
//                } catch (Exception ex) {
//                    // try next candidate
//                }
//            }
//
//            if (href == null) {
//                // fallback to project-root relative
//                Path projRel = projectRoot.relativize(logPath);
//                href = "./" + projRel.toString().replace("\\", "/");
//            }
//
//            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
//        }
//    }

    
    private void attachLogFile(ITestResult result) {
        try {
            if (result == null) return;

            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }

            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
            Path expectedLog = projectRoot.resolve(Paths.get("logs", "run_" + ExtentManager.RUN_TIMESTAMP, perTestName + ".log"));

            File logFile = expectedLog.toFile();
            if (!logFile.exists()) {
                ExtentTestManager.getTest().info("⚠️ Log file not found at expected path: " + expectedLog.toString());
                File logsRoot = projectRoot.resolve("logs").toFile();
                if (logsRoot.exists()) {
                    File found = null;
                    long latest = 0;
                    java.util.Queue<File> q = new java.util.ArrayDeque<>();
                    q.add(logsRoot);
                    while (!q.isEmpty()) {
                        File d = q.poll();
                        File[] children = d.listFiles();
                        if (children == null) continue;
                        for (File c : children) {
                            if (c.isDirectory()) q.add(c);
                            else if (c.getName().equalsIgnoreCase(perTestName + ".log")) {
                                if (c.lastModified() > latest) { found = c; latest = c.lastModified(); }
                            }
                        }
                    }
                    if (found != null) logFile = found;
                }
                if (!logFile.exists()) return;
            }

            Path logPath = logFile.toPath().toAbsolutePath();

            Path[] reportCandidates = new Path[] {
                projectRoot.resolve(Paths.get("test-output", "ExtentReports")),
                projectRoot.resolve(Paths.get("artifacts", "extent", "ExtentReports")),
                projectRoot.resolve("ExtentReports"),
                projectRoot
            };

            String href = null;
            for (Path reportDir : reportCandidates) {
                try {
                    Path reportAbs = reportDir.toAbsolutePath();
                    Path rel = reportAbs.relativize(logPath);
                    String relStr = rel.toString().replace("\\", "/"); // normalize separators
                    // Make relative: remove a leading slash if any so link is repo-relative, not site-root absolute
                    if (relStr.startsWith("/")) relStr = relStr.substring(1);
                    // Avoid leading "./" for cleanliness (still relative)
                    if (relStr.startsWith("./")) relStr = relStr.substring(2);
                    href = relStr;
                    break;
                } catch (Exception ex) {
                    // try next candidate
                }
            }

            if (href == null) {
                Path projRel = projectRoot.relativize(logPath);
                href = projRel.toString().replace("\\", "/");
                if (href.startsWith("/")) href = href.substring(1);
                if (href.startsWith("./")) href = href.substring(2);
            }

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
