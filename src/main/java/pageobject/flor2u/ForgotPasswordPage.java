package pageobject.flor2u;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BaseView {

    public static final String EMAIL_INPUT_LOCATOR = "//div[contains(@class, 'recovery-form')]//input[@name='login']";
    public static final String SEND_PASSWORD_BUTTON_LOCATOR = "//button[text()='Узнать пароль']";

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = EMAIL_INPUT_LOCATOR)
    public WebElement emailInput;

    public ForgotPasswordPage emailInputFill(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @FindBy(xpath = SEND_PASSWORD_BUTTON_LOCATOR)
    public WebElement sendButton;

    public void sendButtonClick(){
        sendButton.click();
        new SendPasswordPage(driver);
    }
}