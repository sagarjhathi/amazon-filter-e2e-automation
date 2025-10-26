package main.java.amazonfilterapplicatione2e.reporting;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;




public class TestListener implements ITestListener {

    //private static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
  //  public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());


    private static final String SCREENSHOT_BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP;
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
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

    
    
    private void attachLogFile(ITestResult result) {
        try {
            if (result == null) return;

            // Determine test-specific name
            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }
            final String logName = perTestName;

            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();

            // Preferred location: logs/run_<timestamp>/<test>.log
            Path expected = projectRoot.resolve(Paths.get("logs", "run_" + ExtentManager.RUN_TIMESTAMP, perTestName + ".log"));
            Path logPath = expected;

            // If not found, deep search under logs/ for latest match
            if (!java.nio.file.Files.exists(logPath)) {
                Path logsRoot = projectRoot.resolve("logs");
                if (java.nio.file.Files.exists(logsRoot)) {
                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
                        Optional<Path> found = walk
                            .filter(p -> java.nio.file.Files.isRegularFile(p)
                                      && p.getFileName().toString().equalsIgnoreCase(logName + ".log"))
                            .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
                        if (found.isPresent()) {
                            logPath = found.get();
                        }
                    } catch (IOException ignored) {
                        // ignore search errors
                    }
                }
                if (!java.nio.file.Files.exists(logPath)) return; // nothing found
            }

            // Candidate report locations (where ExtentReport may live)
            Path[] candidates = new Path[] {
                projectRoot.resolve(Paths.get("test-output", "ExtentReports")),
                projectRoot.resolve(Paths.get("artifacts", "extent", "ExtentReports")),
                projectRoot.resolve("ExtentReports"),
                projectRoot
            };

            String href = null;
            for (Path candidate : candidates) {
                try {
                    Path candAbs = candidate.toAbsolutePath();
                    Path rel = candAbs.relativize(logPath.toAbsolutePath());
                    String relStr = rel.toString().replace("\\", "/");
                    if (relStr.startsWith("/")) relStr = relStr.substring(1);
                    if (relStr.startsWith("./")) relStr = relStr.substring(2);
                    href = relStr;
                    break;
                } catch (Exception ex) {
                    // try next candidate
                }
            }

            if (href == null) {
                // Fallback: project-root relative
                Path projRel = projectRoot.relativize(logPath.toAbsolutePath());
                href = projRel.toString().replace("\\", "/");
                if (href.startsWith("/")) href = href.substring(1);
                if (href.startsWith("./")) href = href.substring(2);
            }

            // ✅ Normalize to a clean relative path
            if (href == null) href = "";
            href = href.replace("\\", "/");
            if (href.startsWith("./")) href = href.substring(2);

            // ✅ Remove any leading "../" so prefix is not cancelled by path traversal
            while (href.startsWith("../")) {
                href = href.substring(3);
            }

            // ✅ Remove leading slash (we want "logs/..." not "/logs/...")
            if (href.startsWith("/")) href = href.substring(1);

            // ✅ Apply REPORT_BASE prefix (for GitHub Pages)
            String reportBase = System.getenv("REPORT_BASE"); // e.g. "/amazon-filter-e2e-automation/"
            if (reportBase != null && !reportBase.trim().isEmpty()) {
                reportBase = reportBase.trim();
                if (!reportBase.startsWith("/")) reportBase = "/" + reportBase;
                if (!reportBase.endsWith("/")) reportBase = reportBase + "/";

                if (!href.startsWith(reportBase) && !href.startsWith(reportBase.substring(1))) {
                    href = reportBase + href;
                    href = href.replaceAll("//+", "/"); // collapse accidental double slashes
                }
            }

            // Optional debug log — remove once verified
            System.out.println("DEBUG: final log href -> " + href);
            ExtentTestManager.getTest().info("DEBUG: final log href -> " + href);

            // ✅ Attach the final link to Extent Report
            if (href == null || href.trim().isEmpty()) {
                ExtentTestManager.getTest().info("⚠️ Log path could not be resolved for: " + logName);
            } else {
                ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
            }

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
