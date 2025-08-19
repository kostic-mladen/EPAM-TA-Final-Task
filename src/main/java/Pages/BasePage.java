package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import Utils.LoggerUtils;

/**
 * Base class for all Page Objects.
 * - Holds WebDriver reference
 * - Initializes PageFactory elements
 * - Provides minimal common helpers
 */
public class BasePage {

    protected final WebDriver driver;

    /**
     * Initialize PageFactory elements for subclasses.
     * @param driver thread-local driver from Config
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to a URL and log the action.
     */
    public void openURL(String url) {
        LoggerUtils.logInfo("Opening URL: " + url);
        driver.get(url);
    }
}
