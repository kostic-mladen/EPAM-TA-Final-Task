package Tests;

import Pages.ProductPage;
import Utils.LoggerUtils;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * Login-related tests for SauceDemo.
 * @Factory lets running this class directly execute on Chrome + Firefox.
 * BaseTest handles navigation to the base URL before each test.
 */
public class LoginTest extends BaseTest {

    public LoginTest(String browser, boolean headless) { super(browser, headless); }

    @Factory
    public static Object[] browsersFactory() {
        return new Object[]{
                new LoginTest("chrome", true),
                new LoginTest("firefox", true)
        };
    }

    @Test(dataProvider = "loginRandomCredentials")
    public void verifyErrorMessageClearingUsernameAndPasswordField(String username, String password) {
        LoggerUtils.logInfo("UC-1: Empty credentials -> expect 'Username is required'");

        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clearWithKeys(loginPage.getUsernameField());
        loginPage.clearWithKeys(loginPage.getPasswordField());
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username is required"),
                "Error message should contain 'Username is required'");
    }

    @Test(dataProvider = "loginRandomCredentials")
    public void verifyErrorMessageClearingOnlyPasswordField(String username, String password) {
        LoggerUtils.logInfo("UC-2: Missing password -> expect 'Password is required'");


        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clearWithKeys(loginPage.getPasswordField());
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Password is required"),
                "Error message should contain 'Password is required'");
    }

    @Test(dataProvider = "loginValidCredentials")
    public void verifySuccessfulLogin(String username, String password) {
        LoggerUtils.logInfo("UC-3: Valid login -> expect 'Swag Labs' header");

        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLogin();
        LoggerUtils.logInfo("Login Successful");

        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertEquals(productPage.getLogoText(), "Swag Labs",
                "The header logo text should be 'Swag Labs'");
    }
}
