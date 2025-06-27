package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

public class LoginPage extends BasePage {

    @FindBy(css = "#user-name")
    public WebElement usernameField;

    @FindBy(css = "#password")
    public WebElement passwordField;

    @FindBy(css = "#login-button")
    public WebElement loginButton;

    @FindBy(css = ".error-message-container")
    public WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);  // Call BasePage constructor to initialize driver
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clearInputs() {
        usernameField.clear();
        passwordField.clear();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
    public void clickLoginButton() {

        if (Objects.requireNonNull(usernameField.getAttribute("value")).isEmpty() && Objects.requireNonNull(passwordField.getAttribute("value")).isEmpty()) {
            loginButton.click();
        }
    }
}
