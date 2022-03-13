package pageobject.flor2u;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendPasswordPage extends BaseView {

    public static final String TEXT_SUCCESS_LOCATOR = "b-success__text";

    public SendPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = TEXT_SUCCESS_LOCATOR)
    public WebElement message;
}