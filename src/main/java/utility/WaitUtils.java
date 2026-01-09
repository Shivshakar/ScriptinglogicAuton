package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * WaitUtils
 *
 * Small collection of explicit wait helpers to encourage stable synchronization.
 * Prefer these explicit waits over Thread.sleep or implicit waits for predictable
 * timing behavior. FluentWait is provided for advanced polling scenarios.
 *
 * Note: Avoid mixing implicit waits with explicit waits in a single session as
 * it can lead to unpredictable wait times.
 */
public class WaitUtils {

    /**
     * Waits until an element located by the locator is visible and returns it.
     * @param driver WebDriver instance
     * @param locator locator to find element
     * @param seconds timeout in seconds
     * @return visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element is clickable (visible + enabled) and returns it.
     * Use before click actions to reduce ElementClickInterceptedException.
     */
    public static WebElement waitForClickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for the document readyState to be 'complete'. Useful after navigation or
     * when waiting for heavy page loads. It is not a replacement for element-level waits.
     */
    public static boolean waitForPageLoad(WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until((Function<WebDriver, Boolean>) drv ->
                ((JavascriptExecutor) drv).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Generic FluentWait wrapper to poll for a custom condition.
     * Example: fluentWait(driver, d -> d.findElement(locator).isDisplayed(), 30, 500);
     */
    public static <T> T fluentWait(WebDriver driver, Function<WebDriver, T> condition, int timeoutSeconds, int pollingMillis) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(Exception.class)
                .until(condition);
    }
}
