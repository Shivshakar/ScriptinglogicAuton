package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ElementActions
 *
 * Small helper utilities for common element interactions. Keep these wrappers thin and
 * focused to make tests more readable and resilient.
 */
public class ElementActions {

    /**
     * Attempts to click an element located by `locator` with retry logic to handle
     * common transient issues such as StaleElementReferenceException or
     * ElementClickInterceptedException.
     *
     * @param driver WebDriver instance to use
     * @param locator By locator of the element to click
     * @param maxRetries number of retry attempts (>=1)
     * @param waitBetweenMs milliseconds to wait between retries
     *
     * Behavior:
     * - Uses WaitUtils.waitForClickable(...) to locate and wait for the element.
     * - Retries on StaleElementReferenceException and ElementClickInterceptedException.
     * - On repeated failures, throws the last exception wrapped in a RuntimeException.
     */
    public static void safeClick(WebDriver driver, By locator, int maxRetries, int waitBetweenMs) {
        Exception lastEx = null;
        for (int i = 0; i < maxRetries; i++) {
            try {
                WebElement el = WaitUtils.waitForClickable(driver, locator, 5);
                el.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                lastEx = e;
                try {
                    Thread.sleep(waitBetweenMs);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // If we reach here, all retries failed — rethrow the last exception for the test to fail
        if (lastEx instanceof RuntimeException) {
            throw (RuntimeException) lastEx;
        } else if (lastEx != null) {
            throw new RuntimeException(lastEx);
        }
    }
}
