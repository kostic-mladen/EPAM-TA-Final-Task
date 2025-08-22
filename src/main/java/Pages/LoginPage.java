package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the SauceDemo login page.
 * Encapsulates element access behind intent-revealing methods.
 */
public class LoginPage extends BasePage {

    public WebDriverWait wait;

    // Keeping elements private encourages using methods instead of direct access
    @FindBy(css = "#user-name")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String text = "required";


    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    /** Clear username and password fields. */
    public void clearWithKeys(WebElement element) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    /** Type username (clears first for safety). */
    public void typeUsername(String username) {
        usernameField.clear();
        new Actions(driver).moveToElement(usernameField).click().perform();
        usernameField.sendKeys(username);
    }

    /** Type password (clears first for safety). */
    public void typePassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        new Actions(driver).moveToElement(usernameField).click().perform();
    }

    /** Click the Login button (with a small move-to for stability). */
    public void clickLogin() {
        new Actions(driver).moveToElement(loginButton).click().perform();
    }

    /** Get the error message text if present/visible. */
    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
