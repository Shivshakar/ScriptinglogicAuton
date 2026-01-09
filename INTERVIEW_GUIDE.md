# Project Guide — ScriptinglogicAuton

Purpose
- This document is a concise, customer-facing guide describing the automated test project, how to run it, what it contains, and how to extend it.

Overview
- ScriptinglogicAuton is a Selenium + TestNG automation framework (Maven) for end-to-end testing of the Stock application.
- It contains Page Object classes, utility helpers, and TestNG tests organized by intent (regression, UI, DB).

Quick start (for your environment)
1. Prerequisites
   - Java 11+ installed and `JAVA_HOME` set.
   - Maven installed on the machine.
   - A supported browser installed (Chrome/Firefox/Edge).

2. Prepare configuration
   - Update `config/config.properties` with the correct `url`, `username`, `password`, and `browser`.

3. Run tests
   - Download dependencies and compile:

```bash
cd /path/to/ScriptinglogicAuton
mvn -DskipTests clean compile
```

   - Run all tests:

```bash
mvn test
```

   - Run a single test class:

```bash
mvn -Dtest=regression.LoginTest test
```

Project structure (high level)
- `pom.xml` — Maven config and dependencies
- `config/config.properties` — environment values used by tests
- `src/main/java/pages` — Page Objects for UI pages
- `src/main/java/utility` — framework helpers (driver factory, waits, element actions, data provider, listeners)
- `src/test/java` — TestNG test classes organized into packages (regression, DBTesting, UITesting)

Key components
- `DriverFactory` — manages WebDriver instances (one per thread) and centralizes driver creation.
- `TestBase` — common setup/teardown that initializes the driver and navigates to the base URL.
- `WaitUtils` / `ElementActions` — helpers for stable element interactions.
- `ForDataProvider` — utility that reads Excel data and supplies TestNG data providers.

Extending the framework
- Add new Page Object classes under `src/main/java/pages`.
- Add tests under `src/test/java` and extend `base.TestBase` to use the shared setup.
- Add reusable utilities under `src/main/java/utility`.

Contact / Ownership
- This repository is prepared for demonstration to customers. For any changes or customizations, contact the project owner or maintainer listed in the repository metadata.

---

If you prefer, I can also:
- Remove this file entirely or replace it with a short `PROJECT_OVERVIEW.md` file.
- Add a simple `testng.xml` suite for smoke/regression runs tailored for demos.
