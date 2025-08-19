package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the SauceDemo login page.
 * Encapsulates element access behind intent-revealing methods.
 */
public class LoginPage extends BasePage {

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
    }

    /** Type username (clears first for safety). */
    public void typeUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /** Type password (clears first for safety). */
    public void typePassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /** Click the Login button (with a small move-to for stability). */
    public void clickLogin() {
        new Actions(driver).moveToElement(loginButton).click().perform();
    }

    /** Clear username field. */
    public void clearUsername() {
        usernameField.clear();
    }

    /** Clear password field. */
    public void clearPassword() {
        passwordField.clear();
    }

    /** Get the error message text if present/visible. */
    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
