package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
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

        // Wait for the page to load completely (example: waiting for login button)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#login-button"))); // Adjust for actual element
    }
}
