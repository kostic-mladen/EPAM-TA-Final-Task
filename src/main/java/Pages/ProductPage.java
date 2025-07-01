package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage{

    public ProductPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".header_label .app_logo")
    public WebElement appLogo;
}
