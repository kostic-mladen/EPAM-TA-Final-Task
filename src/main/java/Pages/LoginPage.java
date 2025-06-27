package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#user-name")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container.error")
    private WebElement errorMessage;

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

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
