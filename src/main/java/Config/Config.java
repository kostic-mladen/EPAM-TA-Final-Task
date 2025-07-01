package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    public static WebDriver driver;

    // Enum for Browser Types
    public enum Browser {
        CHROME, FIREFOX
    }

    // Setup browser and WebDriver
    public static void setUp(String browser, boolean isHeadless) {
        try {
            Browser browserType = Browser.valueOf(browser.toUpperCase());
            logger.info("Starting browser setup for: {}", browserType);

            switch (browserType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless", "--disable-gpu");
                        logger.info("Running Chrome in headless mode.");
                    } else {
                        logger.info("Running Chrome in non-headless mode.");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    logger.info("Chrome browser initialized.");
                    break;

                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("--headless", "--disable-gpu");
                        logger.info("Running Firefox in headless mode.");
                    } else {
                        logger.info("Running Firefox in non-headless mode.");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    logger.info("Firefox browser initialized.");
                    break;

                default:
                    logger.error("Unsupported browser: {}", browser);
                    throw new IllegalArgumentException("Browser type not supported: " + browser);
            }

            driver.manage().window().maximize();
            logger.info("Browser window maximized.");
        } catch (IllegalArgumentException e) {
            logger.error("Error initializing browser: {}", e.getMessage());
            throw e; // Rethrow to make the test fail
        }
    }

    // Close the driver after tests
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser session ended.");
        } else {
            logger.warn("WebDriver is already null, could not quit the session.");
        }
    }
}
