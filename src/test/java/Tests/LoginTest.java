package Tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.LoggerUtils;
import Config.Config;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test(dataProvider = "loginRandomCredentials")
    public void testLogin(String username, String password) {
        LoggerUtils.logInfo("Running test with credentials: " + username + ", " + password);

        LoginPage loginPage = new LoginPage(Config.driver);

        // Open URL before entering credentials
        loginPage.openURL("https://www.saucedemo.com/");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Validate error message for empty or invalid credentials
        if (username.isEmpty() || password.isEmpty()) {
            Assert.assertEquals(loginPage.getErrorMessage(), "Username is required", "Error message should be 'Username is required'");
        } else {
            // Here you can add your validation for successful login
            // Example: check if a certain element is visible after login
            // Assert.assertTrue(loginPage.isLoggedInSuccessfully());
        }
    }
}
