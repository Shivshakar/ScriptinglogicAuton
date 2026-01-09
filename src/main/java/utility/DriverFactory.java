package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * DriverFactory
 *
 * Lightweight ThreadLocal-based WebDriver factory used to support parallel execution.
 * Each thread gets its own WebDriver instance stored in a ThreadLocal container.
 *
 * Usage notes:
 * - Call DriverFactory.initDriver(browser) in the test setup (e.g. @BeforeMethod) to create
 *   a driver for the current thread.
 * - Use DriverFactory.getDriver() anywhere in the same thread to retrieve that instance.
 * - Call DriverFactory.quitDriver() in teardown to quit and remove the thread's driver.
 *
 * This class purposefully keeps driver creation simple (Chrome/Firefox/Edge) so it's easy
 * to understand. In production you can extend this to configure browser options and capabilities.
 */
public class DriverFactory {

    // ThreadLocal container for WebDriver instances to support parallel execution
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Initialize driver for current thread
    public static void initDriver(String browser) {
        WebDriver driver;
        switch (browser == null ? "chrome" : browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        // store created driver in ThreadLocal for this thread
        tlDriver.set(driver);
    }

    // Get WebDriver for current thread
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Quit and remove WebDriver for current thread
    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
                // ignore exceptions on quit to avoid masking test failures
            }
            // remove reference from ThreadLocal to prevent memory leaks
            tlDriver.remove();
        }
    }
}
