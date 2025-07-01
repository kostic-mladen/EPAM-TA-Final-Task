package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import Utils.LoggerUtils;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Open URL method with logging and waiting for page to load
    public void openURL(String url) {
        LoggerUtils.logInfo("Opening URL: " + url);
        driver.get(url);
    }
}
