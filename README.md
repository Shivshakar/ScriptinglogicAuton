ScriptinglogicAuton — README

- New helpers: `utility.DriverFactory`, `utility.WaitUtils`, `utility.ElementActions`, `utility.TestListener` and `test.base.TestBase`.
- Migrated `regression.LoginTest` to use `TestBase` and read credentials from `config/config.properties`.
- Updated `OpenURL` to delegate driver creation to `DriverFactory` (keeps backward compatibility).
- Added WebDriverManager to `pom.xml` and poi-ooxml for XSSF support.

Tech stack
- Java (11), Maven, Selenium Java 4.x, TestNG, Apache POI (XSSF), WebDriverManager.

Project structure (most relevant paths)
- `pom.xml` — Maven config + dependencies
- `config/config.properties` — environment values (url, username, password, browser, etc.)
- `Data/Keywords.xlsx` — example Excel used by data-driven helpers
- `src/main/java/pages` — Page Objects: `Login.java`, `Menu.java`, `ForgotPassword.java`, `Clients/AddClient.java`
- `src/main/java/utility` — framework helpers (new and existing):
  - `ConfigReader.java` — reads `config/config.properties` (used by tests)
  - `DriverFactory.java` — ThreadLocal WebDriver factory (uses WebDriverManager)
  - `WaitUtils.java` — explicit / fluent wait helpers
  - `ElementActions.java` — helpers like `safeClick(...)` with retry logic
  - `TestListener.java` — minimal TestNG listener to capture screenshots on failure
  - `ForDataProvider.java` — Excel → TestNG DataProvider bridge
- `src/test/java` — tests organized by intent
  - `base/TestBase.java` — sets up and tears down thread-local driver
  - `regression/*` — regression test classes (e.g., `LoginTest`, `AddClientTest`)
  - `util/DoLogin.java` — login helper used by some tests

How to run (local)
- Ensure Java 11+ and Maven installed. If not installed on macOS:

```bash
brew update
brew install maven
mvn -v
```

- Download dependencies and compile:

```bash
cd /path/to/ScriptinglogicAuton
mvn -DskipTests clean compile
```

- Run all tests:

```bash
mvn test
```

- Run a single TestNG class:

```bash
mvn -Dtest=regression.LoginTest test
```

Configuration
- `config/config.properties` contains environment values.

Key helpers — what they are and usage
- DriverFactory (ThreadLocal):
  - Purpose: provide one WebDriver instance per thread for safe parallel execution.
  - API: `DriverFactory.initDriver(String browser)`, `DriverFactory.getDriver()`, `DriverFactory.quitDriver()`.
  - Notes: uses WebDriverManager to auto-download driver binaries. To support CI, add headless/browser options and accept-insecure-certs flags.

- TestBase:
  - Purpose: test setup/teardown (call `DriverFactory.initDriver(...)` in `@BeforeMethod`, open base URL, quit in `@AfterMethod`).
  - Tests can extend `base.TestBase` and call `TestBase.getDriver()` to use a thread-local driver.

- WaitUtils:
  - Purpose: provide stable explicit waits: `waitForVisibility`, `waitForClickable`, `waitForPageLoad`, `fluentWait`.
  - Advice: prefer explicit waits, avoid mixing implicit waits and explicit waits.

- ElementActions.safeClick:
  - Purpose: a resilient click wrapper that retries on `StaleElementReferenceException` and `ElementClickInterceptedException`.
  - Usage: `ElementActions.safeClick(driver, By.id("submit"), 3, 300)`.

- TestListener:
  - Purpose: simple `ITestListener` that logs lifecycle events and tries to capture a screenshot on failure by reflecting a `getDriver()` method on the test instance.
  - Register in `testng.xml` or annotate tests with `@Listeners(utility.TestListener.class)`.

Data-driven testing / Excel
- `ForDataProvider.java` uses Apache POI (poi and poi-ooxml) to read `.xlsx` Test data and provide Object[][] to TestNG `@DataProvider` methods.

Security & dependency hygiene
- Upgraded libs: POI and WebDriverManager were added/updated. Validate CVEs periodically. Recommended versions: POI 5.4.0+, WebDriverManager 6.1.0+.
- Don't commit secrets. Use CI secret storage and environment variables.

Styling & formatting
- Quick formatting: `google-java-format` (CLI) or IDE (IntelliJ) plugin.
- For repo enforcement: add Spotless Maven plugin and Checkstyle (Google rules) for CI checks.

Troubleshooting common issues
- mvn command not found: install Maven (Homebrew on macOS is simplest).
- "Cannot resolve XSSF" or similar: run `mvn clean compile` to download dependencies.
- WebDriverManager download failures on CI: ensure agent allows outbound access or cache drivers locally.
- Tests failing only in CI under parallel runs: ensure thread-safety (no shared static driver or data), migrate to ThreadLocal WebDriver, add deterministic test data.
- SSL issues for target URL: add ChromeOptions `setAcceptInsecureCerts(true)` in `DriverFactory` for test environments with self-signed certs.

CI suggestions
- Add Maven Wrapper (`mvnw`) so contributors do not need a global Maven install.
- Add Spotless/Checkstyle to `pom.xml` and fail builds when formatting rules are violated.
- Add a pipeline step to run `mvn test` and archive test reports/screenshots (Surefire/Allure).

Files I changed/added (so you can reference them)
- `src/main/java/utility/DriverFactory.java` (ThreadLocal, WebDriverManager)
- `src/main/java/utility/WaitUtils.java` (explicit waits)
- `src/main/java/utility/ElementActions.java` (safeClick)
- `src/main/java/utility/TestListener.java` (ITestListener)
- `src/test/java/base/TestBase.java` (setup/teardown)
