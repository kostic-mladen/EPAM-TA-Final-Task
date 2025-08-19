package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the product listing page after successful login.
 */
public class ProductPage extends BasePage {

    public ProductPage (WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header_label .app_logo")
    private WebElement appLogo;

    /** Return the header logo text ("Swag Labs"). */
    public String getLogoText() {
        return appLogo.getText();
    }
}
