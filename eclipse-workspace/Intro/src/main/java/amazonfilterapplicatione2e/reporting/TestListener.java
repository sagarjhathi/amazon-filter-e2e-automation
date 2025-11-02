package main.java.amazonfilterapplicatione2e.reporting;
import java.io.IOException; 
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

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
            System.out.println("VMARK: passing rel = " + ("logs/" + RUN_FOLDER + "/" + perTestName + ".log"));
            System.out.println("VMARK: REPORT_BASE env = " + System.getenv("REPORT_BASE"));

            final String fileName = perTestName + ".log";
            String href = buildPublicUrl("logs/" + RUN_FOLDER + "/" + fileName);

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
    
    
    
    
//    private void attachScreenshotFolder(ITestResult result) {
//        try {
//            if (result == null) return;
//
//            String testName = result.getMethod().getMethodName();
//            String runFolder = "Run_" + ExtentManager.RUN_TIMESTAMP;
//
//            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
//            Path reportDir = projectRoot.resolve(Paths.get("test-output", "ExtentReports")).toAbsolutePath().normalize();
//
//            // prefer test-output/screenshots, fallback to screenshots/
//            Path folder = projectRoot.resolve(Paths.get("test-output", "screenshots", runFolder, testName));
//            if (!java.nio.file.Files.exists(folder)) {
//                folder = projectRoot.resolve(Paths.get("screenshots", runFolder, testName));
//            }
//            if (!java.nio.file.Files.exists(folder) || !java.nio.file.Files.isDirectory(folder)) {
//                ExtentTestManager.getTest().info("Screenshot folder not found for test: " + testName);
//                return;
//            }
//
//            List<Path> images = new ArrayList<>();
//            try (Stream<Path> s = java.nio.file.Files.list(folder)) {
//                s.filter(p -> {
//                    String n = p.getFileName().toString().toLowerCase();
//                    return n.endsWith(".png") || n.endsWith(".jpg") || n.endsWith(".jpeg");
//                }).forEach(images::add);
//            }
//
//            if (images.isEmpty()) {
//                ExtentTestManager.getTest().info("No screenshots found in: " + folder.toString());
//                return;
//            }
//
//            images.sort(Comparator.comparing(p -> p.getFileName().toString()));
//
//            StringBuilder html = new StringBuilder();
//            html.append("<details><summary>Open Screenshots (").append(testName).append(")</summary><div>");
//
//            boolean isCI = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));
//            String repoName = null;
//            if (isCI) {
//                String ghRepo = System.getenv("GITHUB_REPOSITORY"); // owner/repo
//                if (ghRepo != null && ghRepo.contains("/")) repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
//            }
//
//            for (Path img : images) {
//                try {
//                    Path imgAbs = img.toAbsolutePath().normalize();
//                    // compute path relative to reportDir (works for local view)
//                    String rel;
//                    try {
//                        rel = reportDir.relativize(imgAbs).toString().replace("\\", "/");
//                    } catch (Exception e) {
//                        rel = projectRoot.relativize(imgAbs).toString().replace("\\", "/");
//                    }
//
//                    // encode segments
//                    String[] parts = rel.split("/");
//                    StringBuilder enc = new StringBuilder();
//                    for (int i = 0; i < parts.length; i++) {
//                        if (parts[i].isEmpty()) continue;
//                        String seg = java.net.URLEncoder.encode(parts[i], java.nio.charset.StandardCharsets.UTF_8);
//                        seg = seg.replace("+", "%20"); // nicer encoding for spaces
//                        if (enc.length() > 0) enc.append("/");
//                        enc.append(seg);
//                    }
//                    String href;
//                    if (isCI && repoName != null && !repoName.isEmpty()) {
//                        // GitHub Pages friendly: /<repo>/<path-from-report-root>
//                        // note: rel is relative from reportDir (ExtentReports), but pages publish root is repo root,
//                        // so the simplest reliable construction assumes images are published under the repo root with same relative path.
//                        // we prefix repo name to make absolute site link.
//                        if (enc.length() > 0 && enc.charAt(0) == '/') enc.deleteCharAt(0);
//                        href = "/" + repoName + "/" + enc.toString();
//                    } else {
//                        // Local: relative path from report file to image (works when double-clicking ExtentReport.html)
//                        href = enc.toString();
//                    }
//
//                    html.append("<div style='margin-top:8px; padding:6px; border:1px solid #ddd;'>")
//                        .append("<div style='font-weight:bold;margin-bottom:6px;'>").append(img.getFileName().toString()).append("</div>")
//                        .append("<a href='").append(href).append("' target='_blank'>")
//                        .append("<img src='").append(href).append("' style='max-width:600px; border:1px solid #ccc;'/>")
//                        .append("</a></div>");
//                } catch (Exception ex) {
//                    // ignore single-file errors
//                }
//            }
//
//            html.append("</div></details>");
//            ExtentTestManager.getTest().info(html.toString());
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
//        }
//    }
//
//
//    /** Build a URL that works in both environments:
//     *  - CI/Pages: prefix with REPORT_BASE (e.g. "/amazon-filter-e2e-automation/")
//     *  - Local:    make it relative from ExtentReports/ExtentReport.html  -> "../../<rel>"
//     */
//    private String buildPublicUrl(String relativeFromSiteRoot) {
//
//        System.out.println("VMARK: buildPublicUrl input='" + relativeFromSiteRoot + "'");
//
//        // sanitize purely relative
//        String rel = relativeFromSiteRoot.replace("\\", "/");
//        while (rel.startsWith("./"))  rel = rel.substring(2);
//        while (rel.startsWith("../")) rel = rel.substring(3);
//        if (rel.startsWith("/"))      rel = rel.substring(1);
//
//        // read possibly corrupted REPORT_BASE (CI)
//        String base = System.getenv("REPORT_BASE");
//        if (base != null) base = base.trim();
//
//        // detect invalid REPORT_BASE (Windows absolute paths)
//        boolean invalid =
//            (base == null || base.isEmpty()) ||
//            base.matches("(?i).*[A-Za-z]:.*") ||
//            base.toLowerCase().contains("program files");
//
//        if (invalid) {
//            // repair using official GitHub env
//            String ghRepo = System.getenv("GITHUB_REPOSITORY"); // e.g. "username/repo"
//            if (ghRepo != null && ghRepo.contains("/")) {
//                String repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
//                if (!repoName.isEmpty()) {
//                    base = "/" + repoName + "/"; // correct Pages prefix
//                }
//            }
//        }
//
//        // final URL construction
//        String out;
//        if (base != null && !base.isEmpty()) {
//            if (!base.startsWith("/")) base = "/" + base;
//            if (!base.endsWith("/"))   base = base + "/";
//            out = (base + rel).replaceAll("//+", "/");
//        } else {
//            out = ("../../" + rel).replaceAll("//+", "/");
//        }
//
//        System.out.println("VMARK: buildPublicUrl output='" + out + "'");
//        return out;
//    }
//



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

