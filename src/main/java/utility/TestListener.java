package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * TestListener
 *
 * Minimal TestNG listener that logs test lifecycle events and attempts to capture screenshots
 * on failure. It uses reflection to call a `getDriver()` method on the test instance if available.
 *
 * Usage notes:
 * - Register this listener in TestNG (`@Listeners(utility.TestListener.class)`) or in `testng.xml`.
 * - The listener is intentionally simple: it does not depend on framework internals so it can
 *   work with existing test classes that expose a `getDriver()` method (like `TestBase`).
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TEST START] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[TEST PASS] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[TEST FAIL] " + result.getName());
        Object instance = result.getInstance();
        try {
            // Attempt to capture screenshot if test class exposes a WebDriver via a getDriver() method
            WebDriver driver = null;
            try {
                driver = (WebDriver) instance.getClass().getMethod("getDriver").invoke(instance);
            } catch (Exception ignored) {
                // If no getDriver method is present, listener will skip screenshot step
            }

            if (driver != null) {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path target = Paths.get("target", "screenshots", result.getName() + ".png");
                Files.createDirectories(target.getParent());
                Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saved screenshot to: " + target.toString());
            }
        } catch (IOException e) {
            System.out.println("Failed saving screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIP] " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[SUITE START] " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[SUITE FINISH] " + context.getName());
    }
}
