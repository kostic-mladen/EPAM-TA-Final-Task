package Data;

import org.testng.annotations.DataProvider;

public class DataProviderTests {

    // Define DataProvider for UC-1 with empty credentials
    @DataProvider(name = "loginRandomCredentials")
    public Object[][] loginCredentials() {
        return new Object[][] {
                {"RandomUsername", "RandomPass"}  // Type any credentials (this will be cleared later)
        };
    }
}
