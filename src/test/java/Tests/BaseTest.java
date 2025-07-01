package Tests;

import Config.Config;
import org.testng.annotations.*;
import Utils.LoggerUtils;

import static Config.Config.driver;

public class BaseTest {

    @BeforeClass
    @Parameters({"browser", "headless"})
    public void setUpClass(@Optional("chrome") String browser, @Optional("true") String headless) {
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser parameter cannot be null or empty.");
        }

        boolean isHeadless = Boolean.parseBoolean(headless);  // Convert headless parameter to boolean

        // Log the browser initialization details
        LoggerUtils.logInfo("Initializing browser: " + browser + " in " + (isHeadless ? "headless" : "non-headless") + " mode.");

        // set up the browser and initialize WebDriver
        Config.setUp(browser, isHeadless);
        // Clears all cookies in the current browser session
        driver.manage().deleteAllCookies();

    }


    @DataProvider
    public static Object[][] loginRandomCredentials() {
        return new Object[][]{
                {"RandomUser", "RandomPass"}
        };
    }

    @DataProvider
    public static Object[][] loginValidCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }


    @AfterClass
    public void tearDownClass() {
        // Log browser session teardown
        LoggerUtils.logInfo("Closing browser session.");
        // Clears all cookies in the current browser session
        driver.manage().deleteAllCookies();
        Config.tearDown();
    }
}
