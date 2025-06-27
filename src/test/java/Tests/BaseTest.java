package Tests;

import Config.Config;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import Utils.LoggerUtils;

public class BaseTest {

    @BeforeClass
    @Parameters({"browser", "headless"})
    public void setUpClass(String browser, String headless) {
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser parameter cannot be null or empty.");
        }

        boolean isHeadless = Boolean.parseBoolean(headless);  // Convert headless parameter to boolean

        // Log the browser initialization details
        LoggerUtils.logInfo("Initializing browser: " + browser + " in " + (isHeadless ? "headless" : "non-headless") + " mode.");

        // Setup the browser and initialize WebDriver
        Config.setUp(browser, isHeadless);
    }

    @AfterClass
    public void tearDownClass() {
        // Log browser session teardown
        LoggerUtils.logInfo("Closing browser session.");
        Config.tearDown();
    }
}
