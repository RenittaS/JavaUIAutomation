package cucumber.flor2u;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {

    public static final String EMAIL_INPUT_LOCATOR = "//div[contains(@class, 'recovery-form')]//input[@name='login']";
    public static final String SEND_PASSWORD_BUTTON_LOCATOR = "//button[text()='Узнать пароль']";

    private SelenideElement emailInput = $(By.xpath(EMAIL_INPUT_LOCATOR));

    @Step("Ввести пароль")
    public ForgotPasswordPage emailInputFill(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    private SelenideElement sendButton = $(By.xpath(SEND_PASSWORD_BUTTON_LOCATOR));

    @Step("Нажать кнопку Узнать пароль")
    public SendPasswordPage sendButtonClick(){
        sendButton.click();
        return page(SendPasswordPage.class);
    }
}