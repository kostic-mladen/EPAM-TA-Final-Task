package Tests;

import Pages.LoginPage;
import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.LoggerUtils;

import static Config.Config.driver;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginRandomCredentials")
    public void verifyErrorMessageClearingUsernameAndPasswordField(String username, String password) {
        LoggerUtils.logInfo("Test UC-1: Test Login form with empty credentials. " + username + ", " + password);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openURL("https://www.saucedemo.com/");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        loginPage.clearUsername();
        loginPage.clearPassword();

        driver.navigate().refresh();
        loginPage.performClick(loginPage.loginButton);


        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username is required"), "Error message should contain 'Username is required'");

    }

    @Test(dataProvider = "loginRandomCredentials")
    public void verifyErrorMessageClearingOnlyPasswordField(String username, String password) {
        LoggerUtils.logInfo("Test UC-2: Test Login form with credentials by passing Username. " + username + ", " + password);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openURL("https://www.saucedemo.com/");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        loginPage.clearPassword();

        driver.navigate().refresh();
        loginPage.enterUsername(username);
        loginPage.performClick(loginPage.loginButton);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Password is required"), "Error message should contain 'Password is required'");

    }

    @Test(dataProvider = "loginValidCredentials")
    public void verifySuccessfulLogin(String username, String password) {
        LoggerUtils.logInfo("Test UC-3: Test Login form with credentials by passing Username and Password " + username + ", " + password);

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        loginPage.openURL("https://www.saucedemo.com/");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        loginPage.performClick(loginPage.loginButton);
        LoggerUtils.logInfo("Login Successful ");

        String logoText = productPage.appLogo.getText();
        Assert.assertEquals(logoText, "Swag Labs", "The text on the logo should be 'Swag Labs'");

    }
}
