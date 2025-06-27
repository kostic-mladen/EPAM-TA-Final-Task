package Data;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Config.Config;
import org.testng.Assert;
import Utils.LoggerUtils;

public class DataProviderTests {

    // Define DataProvider for UC-1 with empty credentials
    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][] {
                {"someusername", "somepassword"}  // Type any credentials (this will be cleared later)
        };
    }

    // UC-1 Test: Login with empty credentials
    @Test(dataProvider = "loginCredentials")
    public void testLoginWithEmptyCredentials(String username, String password) {
        LoggerUtils.logInfo("Test UC-1: Verifying login form with empty credentials.");

        LoginPage loginPage = new LoginPage(Config.driver);

        // Open URL before entering credentials
        loginPage.openURL("https://www.saucedemo.com/");

        // Enter random credentials
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Clear inputs (both username and password fields)
        loginPage.clearInputs();
        LoggerUtils.logInfo("Cleared the input fields.");

        // Click the Login button
        loginPage.clickLoginButton();

        // Assert that the correct error message is shown
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Username is required", "Error message should be 'Username is required'.");
    }
}
