package Tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.LoggerUtils;

import static Config.Config.driver;

public class LoginTest extends BaseTest {


    @Test(dataProvider = "loginRandomCredentials")
    public void verifyUnsuccessfulLoginWithEmptyCredentials(String username, String password) {
        LoggerUtils.logInfo("Test UC-1: Verifying login form with empty credentials. " + username + ", " + password);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openURL("https://www.saucedemo.com/");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        loginPage.usernameField.clear();
        loginPage.passwordField.clear();

        driver.navigate().refresh();
        loginPage.clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username is required"), "Error message should contain 'Username is required'");

    }
}
