package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * ThreadLocal WebDriver manager.
 * One WebDriver instance per thread enables safe TestNG parallel runs.
 */
public class Config {

    // One driver instance per thread
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    public enum Browser { CHROME, FIREFOX }

    /**
     * Create and register a WebDriver for the current thread.
     * @param browser   "chrome" or "firefox"
     * @param isHeadless run in headless mode if true
     */
    public static void setUp(String browser, boolean isHeadless) {
        Browser type = Browser.valueOf(browser.toUpperCase());
        WebDriver driver;

        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions ch = new ChromeOptions();
                if (isHeadless) ch.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                ch.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(ch);
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ff = new FirefoxOptions();
                if (isHeadless) ff.addArguments("--headless");
                driver = new FirefoxDriver(ff);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        TL_DRIVER.set(driver);
    }

    /**
     * Get the WebDriver bound to the current thread.
     * @return WebDriver instance for this thread
     * @throws IllegalStateException if not initialized in this thread
     */
    public static WebDriver getDriver() {
        WebDriver d = TL_DRIVER.get();
        if (d == null) {
            throw new IllegalStateException("WebDriver not initialized for this thread. Call Config.setUp(...) first.");
        }
        return d;
    }

    /**
     * Quit and remove the thread-bound WebDriver (if present).
     * Safe to call multiple times.
     */
    public static void tearDown() {
        WebDriver d = TL_DRIVER.get();
        if (d != null) {
            try {
                d.quit();
            } finally {
                TL_DRIVER.remove();
            }
        }
    }
}
