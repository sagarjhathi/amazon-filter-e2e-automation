# Amazon Filter E2E Automation

A fully engineered end to end automation framework designed to validate Amazon product listing filters with precision, reliability, and deep observability. This project demonstrates how a well structured test automation system behaves when it is treated as a software product rather than a simple collection of scripts. Everything from the execution engine to the CI notifications has been built with clarity, scalability, and maintainability in mind.

The framework performs real world validation of Amazon filter flows such as battery capacity, discount ranges, operating system, brand, delivery options, price sliders, display attributes, and more. It searches for a product category, navigates to the listing page, applies filter combinations based on configuration rules, extracts product sets, validates attributes on the product detail pages, and produces a complete visual and textual report. Every step is logged and every important action is captured through screenshots.

This project is designed to feel polished, easy to understand, and impressive to anyone reading its README. The structure below leads the reader through an intuitive narrative that clearly communicates what the project does, how it is engineered, and why it stands out.

---

# Technology Stack

This framework is built using a rich, modern, and production oriented technology stack. The goal of this section is to clearly showcase every major technology, tool, subsystem, and engineering concept powering the project. This is one of the strongest aspects of the framework, so this version highlights it with the visibility it deserves.

## Programming Language and Ecosystem
- **Java 21** as the core language, providing strong typing, modern syntax, and long term support.
- **Maven** as the build and dependency management system.
- **JUnit-style annotations and TestNG structure** for clean test orchestration.

## Automation and Browser Tooling
- **Selenium 4.27 (W3C-compliant)** for browser automation.
- **WebDriverManager 6.1.0** for automatic driver provisioning, eliminating manual driver downloads.
- **Multi-browser compatibility** built in (Chrome, Firefox, Edge).
- **Customizable browser configurations** including user agents, headless mode, window sizing, GPU control, and sandbox settings.

## Test Execution Engine
- **TestNG 7.10** used for:
  - Parallel test execution (method-level parallelism)
  - Data providers for parameterization
  - Listeners for lifecycle hooks
  - Retry analyzers for flaky test handling

## Data and Input Systems
- **Apache POI 5.4.1** for reading structured Excel files.
- **Custom FileReader classes** for handling property files and environment configurations.
- **ConfigManager** for centralized configuration control.

## Stability, Reliability, and Resilience Systems
- **SafeActions (custom-built reliability abstraction)** providing:
  - Wrapped interactions for click, type, scroll, and element lookup.
  - Intelligent retries for stale or temporarily unavailable elements.
  - Centralized waiting logic.
  - Automatic screenshot capture.
  - Standardized exceptions and logs.
- **WaitUtility** for explicit waits with reusable conditions.
- **GenericUtility** for parsing, URL handling, string normalization, and product detail extraction.
- **Failsafe-inspired retry patterns** built into the workflows.

## Logging and Diagnostics
- **Log4j2** with routing appenders that dynamically create per-run folders.
- **Per-test logs** generated automatically and independent of each other.
- **Timestamped artifact organization** for intuitive debugging.

## Reporting System
- **ExtentReports (Spark HTML)** for:
  - Visual dashboards
  - Screenshots embedded per step
  - Linked test logs
  - Sequential and chronological execution views

## CI/CD and DevOps Toolchain
- **GitHub Actions** running automated workflows including:
  - Push based runs
  - Pull request validations
  - Scheduled nightly CRON jobs
  - On demand manual dispatches
- **CI aware configuration toggling** that adjusts execution scale based on the trigger source.
- **Artifact storage** for preserving logs and screenshots.
- **GitHub Pages deployment** for easy, public access to latest test reports.

## Team Communication and Alerting
- **Discord Webhooks** integrated into the CI for:
  - Execution start alerts
  - Execution completion summaries
  - Status highlighting
  - Direct links to reports, logs, and artifacts

## Code Quality and Maintainability
- **Well-structured object oriented design** with separation of flows, pages, actions, utilities, listeners, and managers.
- **Reusable components** across filters and test flows.
- **Consistent naming conventions**, making navigation predictable.
- **Layered abstraction approach** improving adaptability and reducing coupling.

---

# Key Highlights

## Fully Isolated Per Run Artifacts
Every test run generates its own uniquely timestamped folder. Both logs and screenshots are placed inside this folder in a structured manner that mirrors the test execution itself. This separation ensures predictable debugging and keeps historical artifacts cleanly organized.

```
logs/run_<timestamp>/<testName>.log
screenshots/Run_<timestamp>/<testName>/<step>.png
```

## Configuration Driven Execution
The framework reads from a central properties file to decide how extensively the tests should run. This allows wide flexibility. For example it can be configured to use only a few filter options for quick feedback during code pushes, or it can be configured to exhaustively test all available filters and all products during nightly regressions.

## Smart CI Decision Making
The CI pipeline identifies whether the run originated from a code push or from the nightly CRON job. Based on this, the framework automatically adjusts configuration values. Push based runs stay fast while nightly runs are thorough. This removes manual toggling and keeps the pipeline efficient.

## Real Time Discord Notifications
Whenever CI starts, a clear and informative message is sent to Discord. It includes the triggering user, branch name, commit reference, start time, and a direct link to the run. When execution finishes, another message appears showing the final status, completion time, artifact links, and a button to open the latest hosted report on GitHub Pages. This provides immediate visibility and eliminates the need to hunt through CI logs.

## GitHub Pages Hosted Reports
The CI publishes the entire Extent Report folder to GitHub Pages. Anyone can open the interactive report in a browser and navigate through test steps, screenshots, and logs. This makes sharing results extremely convenient.

---

# Architecture

```text
Test Layer
   AmazonTests and Data Providers
         |
Flow Layer
   SharedFilterFlows and specialized flows for brand, discount, OS, delivery, and pricing
         |
Page Layer
   AmazonLandingPage and ProductListingPage
         |
SafeActions Layer
   Stable interaction layer improving reliability across all flows
         |
Driver and Utility Layer
   DriverManager, WaitUtility, GenericUtility, ExcelReader
         |
Reporting and Logging Layer
   ExtentReports and Log4j2 managing artifacts and diagnostics
```

---

# Execution Flow

```text
Trigger (push or CRON)
        |
Read configuration file
        |
Driver initialization
        |
Navigate to listing page
        |
Identify available filters
        |
Apply filter one by one based on configuration
        |
Collect updated product list for each filter
        |
Open product pages and validate attributes
        |
Capture logs and screenshots for each stage
        |
Generate Extent Report
        |
CI uploads artifacts and deploys report to GitHub Pages
        |
Discord webhook publishes notification with links
```

---

# CI and Reporting Intelligence

```text
Push Event
   Uses reduced filter and product count for quick runs

Scheduled CRON Event
   Uses full filter depth and all product validations

Manual CI Dispatch
   Uses developer provided inputs
```

Each run publishes logs, screenshots, and a complete Extent Report. The report is then deployed to GitHub Pages for immediate access.

---

# Discord Notifications

```text
Start Notification
   Branch
   Triggered by
   Commit reference
   Start time
   Link to CI run

Completion Notification
   Status
   Duration
   Commit reference
   Links to logs and artifacts
   Button to open latest GitHub Pages report
```

This allows anyone on the team to know test results instantly.

---

# Folder Structure

Below is a broad and expanded folder visualization that clearly illustrates how each layer of the framework connects. This diagram is intentionally detailed so the reader can grasp the complete structure in one view.

```text
project-root/
│
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │     BaseTest.java
│   │   │     BasePage.java
│   │   │
│   │   ├── driverManager/
│   │   │     DriverManager.java
│   │   │     ConfigManager.java
│   │   │
│   │   ├── pages/
│   │   │     AmazonLandingPage.java
│   │   │     ProductListingPage.java
│   │   │
│   │   ├── flows/
│   │   │     SharedFilterFlows.java
│   │   │     BrandFilterFlows.java
│   │   │     PriceSliderFlows.java
│   │   │     DeliveryFilterFlows.java
│   │   │     OperatingSystemFilterFlows.java
│   │   │
│   │   ├── safeActions/
│   │   │     SafeActions.java
│   │   │     CaptchaHandler.java
│   │   │
│   │   ├── util/
│   │   │     ScreenshotUtil.java
│   │   │     GenericUtility.java
│   │   │     WaitUtility.java
│   │   │     ExcelReader.java
│   │   │     FileReader.java
│   │   │
│   │   ├── reporting/
│   │   │     ExtentManager.java
│   │   │     ExtentTestManager.java
│   │   │     TestListener.java
│   │   │
│   │   └── logger/
│   │         LoggerFolderSetup.java
│   │         LoggerUtility.java
│   │         log4j2.xml
│   │
│   ├── test/java/tests/
│   │       AmazonTests.java
│   │       RetryFailedTest.java
│   │       TestDataProvider.java
│   │
│   └── test/resources/
│           UtilData.properties
│           testng.xml
│
├── data/
│     Products.xlsx
│
├── logs/
│     run_<timestamp>/
│          <testName>.log
│
├── test-output/
│     ExtentReports/
│          ExtentReport.html
│          screenshots/
│               Run_<timestamp>/
│                    <testName>/
│                         <captured_images>.png
│
├── .github/workflows/
│     main.yml (CI/CD pipeline with Discord + Pages)
│
├── pom.xml
└── README.md
```

This structure emphasizes how every file and module participates in the broader framework. Each folder contains logically grouped responsibilities, making the entire system easy to navigate and easy to extend.

---

# Running Tests

## Quick Smoke Run
Useful during development when fast feedback is preferred.

```bash
mvn clean test -DrunForAllFilterOptions=false -DoverideFilteOptionsCount=2
```

## Full Regression Run
Executes all filter combinations and validates all products.

```bash
mvn clean test -DrunForAllFilterOptions=true -DrunForAllProductsUnderListing=true
```

---

# Engineering Decisions Explained

This section highlights the reasoning behind the key architectural and technical decisions made in the project. These explanations give readers, teams, and reviewers insight into how the framework was intentionally shaped to be reliable, scalable, and easy to maintain.

## SafeActions Abstraction
Direct Selenium calls are prone to flakiness, inconsistent behavior, and complex error handling. SafeActions centralizes all interactions with the browser so the framework behaves consistently across pages and flows. It introduces controlled retry logic, timeout management, standardized screenshot capture, and structured logging. This dramatically improves reliability and reduces duplicated logic across tests.

## TestNG Over JUnit
TestNG was selected due to its stronger support for parallel execution, data driven testing, custom listeners, retry analyzers, dependency hierarchies, and flexible suite configurations. These capabilities are essential for a UI automation framework that needs to scale efficiently and report results in a structured manner.

## ExtentReports Over Allure
Allure provides good visuals but requires more configuration and is less self contained. ExtentReports, especially the Spark HTML variant, offers instant, interactive HTML dashboards without external build steps. It embeds screenshots and logs directly into the report, making debugging much easier. ExtentReports also supports hierarchical test structures which complement this project's flow based model.

## Log4j2 Routing
Log4j2 was chosen for its performance, flexibility, and routing appenders that allow logs to be automatically directed into per run folders. This enables excellent traceability and prevents log mixing between different runs. Each test receives its own log file, which is then linked directly inside the Extent report.

## CI Mode Switching (Push vs CRON)
Automated tests for pull requests and code pushes need fast feedback. Full regressions are better suited for scheduled executions. The CI mode switching mechanism allows the framework to detect the trigger source and adapt execution depth accordingly. This balances speed, thoroughness, and resource usage without requiring any manual intervention.

## GitHub Pages for Report Hosting
Hosting test reports publicly accessible through GitHub Pages allows team members to view results with a single click from Discord or CI logs. It removes the need to download artifacts and improves visibility. GitHub Pages is stable, fast, and integrates naturally with GitHub Actions.

## Discord Notifications
Email notifications are slower and often ignored. Discord provides real time notifications directly where teams communicate. The webhook integration allows the CI to send clear, actionable messages that include links to build logs, artifacts, and the latest hosted report. This increases collaboration and makes the testing system feel alive and responsive.

---

# Extending the Framework
- Add new filters by updating flow classes and page layers
- Add new UI checks by extending GenericUtility and PDP validation methods
- Add new supported browsers by modifying DriverManager

---

# Troubleshooting
- Captcha can appear during Amazon navigation. Retrying the test usually resolves it
- If certain report links do not open, confirm that GitHub Pages deployed all screenshots and logs
- SafeActions already mitigates stale element exceptions. Increase wait durations only when necessary

---



