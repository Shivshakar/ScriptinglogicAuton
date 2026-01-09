package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.ConfigReader;
import utility.DriverFactory;

import java.io.IOException;

import static utility.ConfigReader.getBrowser;

/**
 * TestBase
 *
 * Base test class that initializes a thread-local WebDriver before each test and quits it after.
 * Tests can extend this class to get a fresh, isolated driver per test method (useful for parallel runs).
 *
 * Notes for migration:
 * - Existing tests in this repo use a static driver in `util.OpenURL`. Those tests will continue to work.
 * - To migrate a test to the thread-local approach, make the test class extend `base.TestBase`
 *   and reference `TestBase.getDriver()` instead of the static `OpenURL.driver`.
 */
public class TestBase {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        // initialize a thread-local driver based on config
        String browser = getBrowser();

        DriverFactory.initDriver(browser);
        // navigate to base URL
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.getUrl());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    // helper for tests/pages to access the driver when TestBase is used as a parent
    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
