package main.java.amazonfilterapplicatione2e.reporting;
import java.io.IOException;
import java.nio.file.Files;
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
//    private void attachLogFile(ITestResult result) {
//    
//        try {
//            if (result == null) return;
//
//            String perTestName = (String) result.getAttribute("logFileName");
//            if (perTestName == null || perTestName.isEmpty()) {
//                perTestName = result.getMethod().getMethodName();
//            }
//
//            // --- Find the log on disk (for sanity/artifacts), but DO NOT use this path to build the URL.
//            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
//            Path expected = projectRoot.resolve(Paths.get("logs", RUN_FOLDER, perTestName + ".log")).normalize();
//            Path logPath = expected;
//
//            if (!java.nio.file.Files.exists(logPath)) {
//                Path logsRoot = projectRoot.resolve("logs");
//                if (java.nio.file.Files.exists(logsRoot)) {
//                    final String expectedFileName = perTestName + ".log"; // effectively-final for lambda
//                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
//                        Optional<Path> found = walk
//                                .filter(p -> java.nio.file.Files.isRegularFile(p)
//                                        && p.getFileName().toString().equalsIgnoreCase(expectedFileName))
//                                .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
//                        if (found.isPresent()) {
//                            logPath = found.get();
//                        }
//                    } catch (IOException ignored) {}
//                }
//            }
//            if (logPath == null || !java.nio.file.Files.exists(logPath)) {
//                ExtentTestManager.getTest().info("⚠️ Log file missing for test: " + perTestName);
//                return;
//            }
//
//            // --- Build the PUBLIC URL deterministically (never from absolute OS path)
//            System.out.println("VMARK: passing rel = " + ("logs/" + RUN_FOLDER + "/" + perTestName + ".log"));
//            System.out.println("VMARK: REPORT_BASE env = " + System.getenv("REPORT_BASE"));
//
//            final String fileName = perTestName + ".log";
//            String href = buildPublicUrl("logs/" + RUN_FOLDER + "/" + fileName);
//
//            ExtentTestManager.getTest().info("📄 <a href='" + href + "' target='_blank'>Open log file</a>");
//            return; // ensure nothing else overwrites href
//
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("❌ Failed to attach log file: " + e.getMessage());
//        }
//    }
    
    
    
    
    private void attachLogFile(ITestResult result) {
        try {
            if (result == null) return;

            // determine test name (allow custom attribute)
            String perTestName = (String) result.getAttribute("logFileName");
            if (perTestName == null || perTestName.isEmpty()) {
                perTestName = result.getMethod().getMethodName();
            }

            final String runFolder = "run_" + ExtentManager.RUN_TIMESTAMP;
            final String fileName = perTestName + ".log";
            final String relPath = "logs/" + runFolder + "/" + fileName; // normalized relative path (posix-style)

            // find OS path for diagnostics only (do not use as href)
            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
            Path foundLogPath = projectRoot.resolve(Paths.get("logs", runFolder, fileName)).normalize();
            if (!java.nio.file.Files.exists(foundLogPath)) {
                Path logsRoot = projectRoot.resolve("logs");
                if (java.nio.file.Files.exists(logsRoot)) {
                    final String expectedFileName = fileName; // effectively-final for lambda
                    try (Stream<Path> walk = java.nio.file.Files.walk(logsRoot)) {
                        Optional<Path> found = walk
                            .filter(p -> java.nio.file.Files.isRegularFile(p)
                                && p.getFileName().toString().equalsIgnoreCase(expectedFileName))
                            .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
                        if (found.isPresent()) {
                            foundLogPath = found.get().toAbsolutePath().normalize();
                        }
                    } catch (IOException ignored) { }
                }
            }

            if (foundLogPath == null || !java.nio.file.Files.exists(foundLogPath)) {
                ExtentTestManager.getTest().info("Log file not found for: " + perTestName);
                return;
            }

            // 1) GitHub Pages absolute path (if env present)
            String pagesHref = "";
            String ghRepo = System.getenv("GITHUB_REPOSITORY"); // owner/repo
            if (ghRepo != null && ghRepo.contains("/")) {
                String repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
                if (!repoName.isEmpty()) {
                    pagesHref = ("/" + repoName + "/" + relPath).replaceAll("//+", "/");
                }
            }

            // 2) local/artifact relative fallbacks (cover common depths)
            String hrefDot    = ("./"  + relPath).replaceAll("//+", "/"); // if index.html sits next to logs/
            String hrefUp1    = ("../" + relPath).replaceAll("//+", "/"); // if report sits one level under root
            String hrefUp2    = ("../../" + relPath).replaceAll("//+", "/"); // if report sits two levels under (ExtentReports under test-output)
            // include all anchors in the snippet - user will click the one that works

            StringBuilder html = new StringBuilder();
            html.append("<div style='margin:6px 0; padding:6px; border-left:3px solid #999;'>");
            html.append("<div style='font-weight:bold; margin-bottom:4px;'>Open log — choose the link that works in your environment</div>");
            if (!pagesHref.isEmpty()) {
                html.append("<div><a href='").append(pagesHref).append("' target='_blank'>Open (GitHub Pages)</a></div>");
            }
            html.append("<div><a href='").append(hrefDot).append("' target='_blank'>Open (artifact root / index.html)</a></div>");
            html.append("<div><a href='").append(hrefUp1).append("' target='_blank'>Open (report one level deep)</a></div>");
            html.append("<div><a href='").append(hrefUp2).append("' target='_blank'>Open (report inside ExtentReports/test-output)</a></div>");
            html.append("<div style='margin-top:6px;color:#666;font-size:0.9em;'>Detected log on disk: ");
            html.append(foundLogPath.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"));
            html.append("</div>");
            html.append("</div>");

            ExtentTestManager.getTest().info(html.toString());

        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Failed to attach log file: " + e.getMessage());
        }
    }


    /** =======================================================================
     *  SCREENSHOTS (same deterministic approach)
     *  Expected on disk: test-output/screenshots/Run_<ts>/<testName>/*.png
     *  Published beside report: artifacts/extent/screenshots/Run_<ts>/<testName>/*.png
     *  ======================================================================= */
//    private void attachScreenshotFolder(ITestResult result) {
//        try {
//            String testName = result.getMethod().getMethodName();
//            String relFolder = "screenshots/Run_" + ExtentManager.RUN_TIMESTAMP + "/" + testName;
//
//            // Build a publicly accessible URL example to the folder, plus inline thumbs:
//            java.nio.file.Path absFolder = Paths.get(System.getProperty("user.dir"))
//                    .resolve("test-output").resolve(relFolder).normalize();
//
//            if (java.nio.file.Files.exists(absFolder)) {
//                StringBuilder html = new StringBuilder();
//                html.append("<details><summary>📂 Open Screenshots (").append(testName).append(")</summary>");
//
//                try (Stream<Path> files = java.nio.file.Files.list(absFolder)) {
//                    files.filter(p -> p.getFileName().toString().toLowerCase().endsWith(".png"))
//                         .sorted()
//                         .forEach(p -> {
//                             String href = buildPublicUrl(relFolder + "/" + p.getFileName().toString());
//                             html.append("<div style='margin-top:10px; border:1px solid #ccc; padding:5px;'>")
//                                 .append("<div style='font-weight:bold; margin-bottom:3px;'>📸 ").append(p.getFileName()).append("</div>")
//                                 .append("<a href='").append(href).append("' target='_blank'>")
//                                 .append("<img src='").append(href).append("' style='max-width:600px; border:1px solid #ddd;'/>")
//                                 .append("</a></div>");
//                         });
//                } catch (IOException ignored) {}
//
//                html.append("</details>");
//                ExtentTestManager.getTest().info(html.toString());
//            } else {
//                ExtentTestManager.getTest().info("⚠️ Screenshot folder not found: " + relFolder);
//            }
//        } catch (Exception e) {
//            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
//        }
//    }
    
    
    private void attachScreenshotFolder(ITestResult result) {
        try {
            if (result == null) return;
            String testName = result.getMethod().getMethodName();
            String runFolder = "Run_" + ExtentManager.RUN_TIMESTAMP;

            Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
            Path reportDir = projectRoot.resolve(Paths.get("test-output", "ExtentReports")).toAbsolutePath().normalize();
            Path folder = projectRoot.resolve(Paths.get("test-output","screenshots", runFolder, testName));
            if (!Files.exists(folder)) folder = projectRoot.resolve(Paths.get("screenshots", runFolder, testName));
            if (!Files.exists(folder) || !Files.isDirectory(folder)) {
                ExtentTestManager.getTest().info("Screenshot folder not found for test: " + testName);
                return;
            }

            List<Path> images = new ArrayList<>();
            try (Stream<Path> s = Files.list(folder)) {
                s.filter(p -> { String n=p.getFileName().toString().toLowerCase(); return n.endsWith(".png")||n.endsWith(".jpg")||n.endsWith(".jpeg"); })
                 .forEach(images::add);
            }

            if (images.isEmpty()) {
                ExtentTestManager.getTest().info("No screenshots found in: " + folder.toString());
                return;
            }

            images.sort(Comparator.comparing(p -> p.getFileName().toString()));
            StringBuilder html = new StringBuilder();
            html.append("<details><summary>Open Screenshots (").append(testName).append(")</summary><div>");

            boolean isCI = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));

            for (Path img : images) {
                try {
                    Path imgAbs = img.toAbsolutePath().normalize();

                    // LOCAL: prefer relative path from report dir -> prevents file:///C:/... in browser
                    String localRel = null;
                    try {
                        Path rel = reportDir.relativize(imgAbs);
                        localRel = rel.toString().replace("\\", "/");
                    } catch (Exception e) {
                        // fallback: project-root relative with ../.. prefix
                        Path projRel = projectRoot.relativize(imgAbs);
                        localRel = "../" + projRel.toString().replace("\\", "/");
                    }

                    // site-relative used on CI / Pages
                    String siteRel = "screenshots/" + runFolder + "/" + testName + "/" +
                                     java.net.URLEncoder.encode(img.getFileName().toString(), java.nio.charset.StandardCharsets.UTF_8).replace("+","%20");

                    String href = isCI ? buildPublicUrl(siteRel) : localRel;

                    // Debug helper (optional): uncomment to log href into report for one run
                    // ExtentTestManager.getTest().info("DEBUG_IMG_HREF: " + href);

                    html.append("<div style='margin-top:8px;padding:6px;border:1px solid #ddd;'>")
                        .append("<div style='font-weight:bold;margin-bottom:6px;'>").append(img.getFileName().toString()).append("</div>")
                        .append("<a href='").append(href).append("' target='_blank'>")
                        .append("<img src='").append(href).append("' style='max-width:600px;border:1px solid #ccc;'/>")
                        .append("</a></div>");

                } catch (Exception ex) { /* ignore single-file issues */ }
            }

            html.append("</div></details>");
            ExtentTestManager.getTest().info(html.toString());

        } catch (Exception e) {
            ExtentTestManager.getTest().warning("Could not attach screenshot folder: " + e.getMessage());
        }
    }



    /** Build a URL that works in both environments:
     *  - CI/Pages: prefix with REPORT_BASE (e.g. "/amazon-filter-e2e-automation/")
     *  - Local:    make it relative from ExtentReports/ExtentReport.html  -> "../../<rel>"
     */
    private String buildPublicUrl(String relativeFromSiteRoot) {

        System.out.println("VMARK: buildPublicUrl input='" + relativeFromSiteRoot + "'");

        // sanitize purely relative
        String rel = relativeFromSiteRoot.replace("\\", "/");
        while (rel.startsWith("./"))  rel = rel.substring(2);
        while (rel.startsWith("../")) rel = rel.substring(3);
        if (rel.startsWith("/"))      rel = rel.substring(1);

        // read possibly corrupted REPORT_BASE (CI)
        String base = System.getenv("REPORT_BASE");
        if (base != null) base = base.trim();

        // detect invalid REPORT_BASE (Windows absolute paths)
        boolean invalid =
            (base == null || base.isEmpty()) ||
            base.matches("(?i).*[A-Za-z]:.*") ||
            base.toLowerCase().contains("program files");

        if (invalid) {
            // repair using official GitHub env
            String ghRepo = System.getenv("GITHUB_REPOSITORY"); // e.g. "username/repo"
            if (ghRepo != null && ghRepo.contains("/")) {
                String repoName = ghRepo.substring(ghRepo.indexOf('/') + 1).trim();
                if (!repoName.isEmpty()) {
                    base = "/" + repoName + "/"; // correct Pages prefix
                }
            }
        }

        // final URL construction
        String out;
        if (base != null && !base.isEmpty()) {
            if (!base.startsWith("/")) base = "/" + base;
            if (!base.endsWith("/"))   base = base + "/";
            out = (base + rel).replaceAll("//+", "/");
        } else {
            out = ("../../" + rel).replaceAll("//+", "/");
        }

        System.out.println("VMARK: buildPublicUrl output='" + out + "'");
        return out;
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

