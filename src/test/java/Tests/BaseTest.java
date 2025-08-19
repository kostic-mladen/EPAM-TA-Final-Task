package Tests;

import Config.Config;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Base test class:
 * - Ensures a clean driver per test method (setup/teardown)
 * - Navigates to base URL before each test (centralized)
 * - Supports both @Factory-driven and XML-driven runs
 */
public class BaseTest {

    // Values supplied by @Factory
    protected String injectedBrowser;
    protected Boolean injectedHeadless;

    // Common page object(s) available to all tests
    protected LoginPage loginPage;

    public BaseTest() {}
    public BaseTest(String browser, boolean headless) {
        this.injectedBrowser = browser;
        this.injectedHeadless = headless;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void ensureDriverReady(@Optional("chrome") String browserParam,
                                  @Optional("true") String headlessParam) {

        String browser = (injectedBrowser != null) ? injectedBrowser : browserParam;
        boolean headless = (injectedHeadless != null) ? injectedHeadless : Boolean.parseBoolean(headlessParam);

        // If no driver bound to this thread, create it
        try {
            Config.getDriver();
        } catch (IllegalStateException e) {
            LoggerUtils.logInfo("Initializing browser: " + browser + " | mode: " + (headless ? "headless" : "headed"));
            Config.setUp(browser, headless);
        }

        // Fresh session per test
        getDriver().manage().deleteAllCookies();

        // Create common page(s) and navigate to base URL once, centrally
        loginPage = new LoginPage(getDriver());
        String baseUrl = ConfigReader.getBaseUrl();
        LoggerUtils.logInfo("Navigating to base URL: " + baseUrl);
        loginPage.openURL(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LoggerUtils.logInfo("Closing browser session.");
        Config.tearDown();
    }

    protected WebDriver getDriver() {
        return Config.getDriver();
    }

    /* ---------- Data Providers ---------- */

    @DataProvider
    public static Object[][] loginRandomCredentials() {
        return new Object[][]{{"RandomUser", "RandomPass"}};
    }

    @DataProvider
    public static Object[][] loginValidCredentials() {
        return new Object[][]{{"standard_user", "secret_sauce"}};
    }
}
