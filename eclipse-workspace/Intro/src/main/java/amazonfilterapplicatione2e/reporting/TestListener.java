//package main.java.amazonfilterapplicatione2e.reporting;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Comparator;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.Status;
//
//
//
//
//public class TestListener implements ITestListener {
//
//    //private static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
//  //  public static final String RUN_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//
//
//    private static final String SCREENSHOT_BASE_DIR = System.getProperty("user.dir") + "/test-output/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP;
//    
//    @Override
//    public void onTestStart(ITestResult result) {
//        String testName = result.getMethod().getMethodName();
//        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//     //   ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed");
//        attachLogFile(result);
//        attachScreenshotFolder(result);
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        // Screenshot on failure
//        String testName = result.getMethod().getMethodName();
//        Throwable cause = result.getThrowable();
//
//        // Log failure in Extent Report
//
//        Throwable throwable = result.getThrowable();
//        if (throwable != null) {
//            String message = throwable.getMessage();
//
//            if (message != null) {
//                String formattedMessage = formatFailureMessage(message);
//                ExtentTestManager.getTest().fail(formattedMessage);
//            }
//        }
//        // No screenshot taken
//        attachLogFile(result);             // 🔹 Still attach logs if needed
//        attachScreenshotFolder(result); // 🔹 Still attach folder path for context if needed
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//    	if (result.getThrowable() != null) {
//            ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
//        } else {
//            ExtentTestManager.getTest().skip("Test Skipped (No Exception)");
//        }
//        attachLogFile(result);
//        attachScreenshotFolder(result);
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        ExtentManager.getInstance().flush();
//    }    
//    
//    
//    
////    private void attachLogFile(ITestResult result) {
////        try {
////            if (result == null) return;
////            String testName = result.getMethod().getMethodName();
////
////            // 1) Try expected exact path first (fast)
////            Path expected = Paths.get(System.getProperty("user.dir"),
////                                      "logs",
////                                      "run_" + ExtentManager.RUN_TIMESTAMP,
////                                      testName + ".log");
////            File found = expected.toFile();
////            if (!found.exists()) {
////                // 2) Fallback: deep search under ./logs for any file whose name contains the testName
////                File logsRoot = new File(System.getProperty("user.dir"), "logs");
////                if (logsRoot.exists() && logsRoot.isDirectory()) {
////                    File best = null;
////                    long bestTime = 0L;
////                    java.util.Queue<File> q = new java.util.ArrayDeque<>();
////                    q.add(logsRoot);
////                    while (!q.isEmpty()) {
////                        File dir = q.poll();
////                        File[] children = dir.listFiles();
////                        if (children == null) continue;
////                        for (File c : children) {
////                            if (c.isDirectory()) q.add(c);
////                            else {
////                                String name = c.getName().toLowerCase();
////                                if (name.contains(testName.toLowerCase()) && name.endsWith(".log")) {
////                                    long t = c.lastModified();
////                                    // pick the most recently modified candidate
////                                    if (best == null || t > bestTime) {
////                                        best = c;
////                                        bestTime = t;
////                                    }
////                                }
////                            }
////                        }
////                    }
////                    if (best != null) found = best;
////                }
////            }
////
////            if (found != null && found.exists()) {
////                // compute relative href from report folder so links remain portable
////                Path reportDir = Paths.get(System.getProperty("user.dir"), "test-output", "ExtentReports");
////                Path target = found.toPath().toAbsolutePath();
////                String href;
////                try {
////                    href = reportDir.relativize(target).toString().replace("\\", "/");
////                } catch (Exception e) {
////                    // fallback: project-root relative path
////                    Path proj = Paths.get(System.getProperty("user.dir"));
////                    href = "./" + proj.relativize(target).toString().replace("\\", "/");
////                }
////
////                ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
////            } else {
////                ExtentTestManager.getTest().info("⚠️ Log file not found: " + expected.toAbsolutePath().toString());
////            }
////        } catch (Exception ex) {
////            ExtentTestManager.getTest().warning("Could not attach log file: " + ex.getMessage());
////        }
////    }
//
//    
//    private void attachLogFile(ITestResult result) {
//        try {
//            if (result == null) return;
//
//            // Determine test-specific name
//            String perTestName = (String) result.getAttribute("logFileName");
//            if (perTestName == null || perTestName.isEmpty()) {
//                perTestName = result.getMethod().getMethodName();
//            }
//
//            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
//
//            // Preferred expected log file path
//            Path expected = projectRoot.resolve(Paths.get(
//                "logs",
//                "run_" + ExtentManager.RUN_TIMESTAMP,
//                perTestName + ".log"
//            )).normalize();
//
//            Path logPath = expected;
//
//            // If not found, search logs/ for the newest matching file (lambda needs effectively-final capture)
//            if (!java.nio.file.Files.exists(logPath)) {
//                Path logsRoot = projectRoot.resolve("logs");
//                if (java.nio.file.Files.exists(logsRoot)) {
//                    final String expectedFileName = perTestName + ".log";  // <-- effectively final for lambda
//                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
//                        Optional<Path> found = walk
//                            .filter(p -> java.nio.file.Files.isRegularFile(p)
//                                      && p.getFileName().toString().equalsIgnoreCase(expectedFileName))
//                            .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
//                        if (found.isPresent()) {
//                            logPath = found.get().toAbsolutePath().normalize();
//                        }
//                    } catch (Exception ignored) {
//                        // best-effort search; ignore errors
//                    }
//                }
//            }
//
//            // If we still don't have a log file, stop here
//            if (logPath == null || !java.nio.file.Files.exists(logPath)) {
//                ExtentTestManager.getTest().info("⚠️ Log file missing for test: " + perTestName);
//                return;
//            }
//
//            // ==== SAFE, DETERMINISTIC URL (no filesystem absolute paths) ====
//            final String testLogFile = logPath.getFileName().toString();        // e.g., verifyingXxx.log
//            final String runFolder   = "run_" + ExtentManager.RUN_TIMESTAMP;    // e.g., run_2025-10-27_12-34-56
//
//            String href;
//            String reportBase = System.getenv("REPORT_BASE"); // set in CI to "/amazon-filter-e2e-automation/"
//            if (reportBase != null && !reportBase.trim().isEmpty()) {
//                reportBase = reportBase.trim();
//                if (!reportBase.startsWith("/")) reportBase = "/" + reportBase;
//                if (!reportBase.endsWith("/")) reportBase = reportBase + "/";
//                // On GitHub Pages the site root contains logs/ alongside ExtentReports/
//                href = reportBase + "logs/" + runFolder + "/" + testLogFile;   // -> /repo/logs/run_.../file.log
//            } else {
//                // Local run: report is at test-output/ExtentReports/ExtentReport.html
//                // logs are at projectRoot/logs/... so go up two levels
//                href = "../../logs/" + runFolder + "/" + testLogFile;          // -> ../../logs/run_.../file.log
//            }
//
//            // Normalize double slashes
//            href = href.replaceAll("//+", "/");
//
//            // Debug (remove once verified)
//            System.out.println("DEBUG: final log href -> " + href);
//            ExtentTestManager.getTest().info("DEBUG: final log href -> " + href);
//
//            // Attach link to Extent
//            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("❌ Failed to attach log file: " + e.getMessage());
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//    
//    
//    
//    
//    
//    private void attachScreenshotFolder(ITestResult result) {
//        try {
//            String testName = result.getMethod().getMethodName();
//            String relativeFolderPath = "./screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//            String absoluteFolderPath = System.getProperty("user.dir") + "/screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//            File testFolder = new File(absoluteFolderPath);
//
//            if (testFolder.exists()) {
//                StringBuilder html = new StringBuilder();
//                html.append("<details><summary>📂 Open Screenshots (" + testName + ")</summary>");
//
//                File[] files = testFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
//                if (files != null && files.length > 0) {
//                    for (File file : files) {
//                        String relativePath = relativeFolderPath + "/" + file.getName();
//                        html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
//                            .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(file.getName()).append("</div>")
//                            .append("<a href='").append(relativePath).append("' target='_blank'>")
//                            .append("<img src='").append(relativePath).append("' style='max-width:600px; border:1px solid #ddd;'/>")
//                            .append("</a></div>");
//                    }
//                } else {
//                    html.append("<div>No screenshots found</div>");
//                }
//
//                html.append("</details>");
//                ExtentTestManager.getTest().info(html.toString());
//            } else {
//                ExtentTestManager.getTest().info("⚠️ Screenshot folder not found: " + relativeFolderPath);
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
//        }
//    }
//
//   
//    
//
//    private String formatFailureMessage(String message) {
//        // Optional: detect structured failure message by keywords
//        if (message.contains("Brand filter") && message.contains("📦 Title:")) {
//
//            // Split for better readability
//            String[] lines = message.split("\n");
//            String filter = lines[0]; // first line like Brand filter '...' not found
//
//            // Build collapsible HTML
//            StringBuilder html = new StringBuilder();
//            html.append("<b>").append(filter).append("</b>");
//            html.append("<details><summary>📄 Click to expand product details</summary>");
//
//            for (int i = 1; i < lines.length; i++) {
//                html.append(lines[i].replace("\n", "<br>")).append("<br>");
//            }
//
//            html.append("</details>");
//            return html.toString();
//        }
//
//        // If no special format, return as-is
//        return message;
//    }
//
//
//    
//
//}


package main.java.amazonfilterapplicatione2e.reporting;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    private static final String RUN_FOLDER = "run_" + ExtentManager.RUN_TIMESTAMP;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName).log(Status.INFO, "🔹 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachLogFile(result);
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable t = result.getThrowable();
        if (t != null && t.getMessage() != null) {
            ExtentTestManager.getTest().fail(formatFailureMessage(t.getMessage()));
        } else {
            ExtentTestManager.getTest().fail("Test failed.");
        }
        attachLogFile(result);
        attachScreenshotFolder(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getThrowable() != null) {
            ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
        } else {
            ExtentTestManager.getTest().skip("Test Skipped (no exception)");
        }
        attachLogFile(result);
        attachScreenshotFolder(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    /** =======================================================================
     *  LOG LINK (deterministic URL, never from OS path)
     *  ======================================================================= */
    private void attachLogFile(ITestResult result) {
    	System.out.println("VMARK: TestListener class = " + this.getClass().getName());
    	System.out.println("VMARK: CodeSource = " + this.getClass().getProtectionDomain().getCodeSource().getLocation());
    	System.out.println("VMARK: entering attachLogFile()");

        try {
            if (result == null) return;

            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }

            // --- Find the log on disk (for sanity/artifacts), but DO NOT use this path to build the URL.
            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
            Path expected = projectRoot.resolve(Paths.get("logs", RUN_FOLDER, perTestName + ".log")).normalize();
            Path logPath = expected;

            if (!java.nio.file.Files.exists(logPath)) {
                Path logsRoot = projectRoot.resolve("logs");
                if (java.nio.file.Files.exists(logsRoot)) {
                    final String expectedFileName = perTestName + ".log"; // effectively-final for lambda
                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
                        Optional<Path> found = walk
                                .filter(p -> java.nio.file.Files.isRegularFile(p)
                                        && p.getFileName().toString().equalsIgnoreCase(expectedFileName))
                                .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
                        if (found.isPresent()) {
                            logPath = found.get();
                        }
                    } catch (IOException ignored) {}
                }
            }
            if (logPath == null || !java.nio.file.Files.exists(logPath)) {
                ExtentTestManager.getTest().info("⚠️ Log file missing for test: " + perTestName);
                return;
            }

            // --- Build the PUBLIC URL deterministically (never from absolute OS path)
            final String fileName = perTestName + ".log";
            String href = buildPublicUrl("logs/" + RUN_FOLDER + "/" + fileName);

            System.out.println("DEBUG V_MARK: final log href -> " + href);
            ExtentTestManager.getTest().info("DEBUG V_MARK: final log href -> " + href);
            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
            return; // ensure nothing else overwrites href

        } catch (Exception e) {
            ExtentTestManager.getTest().warning("❌ Failed to attach log file: " + e.getMessage());
        }
    }

    /** =======================================================================
     *  SCREENSHOTS (same deterministic approach)
     *  Expected on disk: test-output/screenshots/Run_<ts>/<testName>/*.png
     *  Published beside report: artifacts/extent/screenshots/Run_<ts>/<testName>/*.png
     *  ======================================================================= */
    private void attachScreenshotFolder(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            String relFolder = "screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;

            // Build a publicly accessible URL example to the folder, plus inline thumbs:
            java.nio.file.Path absFolder = Paths.get(System.getProperty("user.dir"))
                    .resolve("test-output").resolve(relFolder).normalize();

            if (java.nio.file.Files.exists(absFolder)) {
                StringBuilder html = new StringBuilder();
                html.append("<details><summary>📂 Open Screenshots (").append(testName).append(")</summary>");

                try (Stream<Path> files = java.nio.file.Files.list(absFolder)) {
                    files.filter(p -> p.getFileName().toString().toLowerCase().endsWith(".png"))
                         .sorted()
                         .forEach(p -> {
                             String href = buildPublicUrl(relFolder + "/" + p.getFileName().toString());
                             html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
                                 .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(p.getFileName()).append("</div>")
                                 .append("<a href='").append(href).append("' target='_blank'>")
                                 .append("<img src='").append(href).append("' style='max-width:600px; border:1px solid #ddd;'/>")
                                 .append("</a></div>");
                         });
                } catch (IOException ignored) {}

                html.append("</details>");
                ExtentTestManager.getTest().info(html.toString());
            } else {
                ExtentTestManager.getTest().info("⚠️ Screenshot folder not found: " + relFolder);
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
        }
    }

    /** Build a URL that works in both environments:
     *  - CI/Pages: prefix with REPORT_BASE (e.g. "/amazon-filter-e2e-automation/")
     *  - Local:    make it relative from ExtentReports/ExtentReport.html  -> "../../<rel>"
     */
    private String buildPublicUrl(String relativeFromSiteRoot) {
        // normalize: no leading slash inside the site path
        String rel = relativeFromSiteRoot.replace("\\", "/");
        while (rel.startsWith("./")) rel = rel.substring(2);
        while (rel.startsWith("../")) rel = rel.substring(3);
        if (rel.startsWith("/")) rel = rel.substring(1);

        String base = System.getenv("REPORT_BASE"); // set in CI job env
        if (base != null && !base.trim().isEmpty()) {
            base = base.trim();
            if (!base.startsWith("/")) base = "/" + base;
            if (!base.endsWith("/"))  base = base + "/";
            String out = (base + rel).replaceAll("//+", "/");
            return out;
        } else {
            // local: ExtentReport is at test-output/ExtentReports/ExtentReport.html
            return ("../../" + rel).replaceAll("//+", "/");
        }
    }

    private String formatFailureMessage(String message) {
        if (message != null && message.contains("Brand filter") && message.contains("📦 Title:")) {
            String[] lines = message.split("\n");
            StringBuilder html = new StringBuilder();
            html.append("<b>").append(lines[0]).append("</b>");
            html.append("<details><summary>📄 Click to expand product details</summary>");
            for (int i = 1; i < lines.length; i++) {
                html.append(lines[i].replace("\n", "<br>")).append("<br>");
            }
            html.append("</details>");
            return html.toString();
        }
        return message == null ? "" : message;
    }
}

