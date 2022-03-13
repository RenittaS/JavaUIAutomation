package cucumber.flor2u;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SendPasswordPage {

    public static final String TEXT_SUCCESS_LOCATOR = "b-success__text";

    public SelenideElement message = $(By.className(TEXT_SUCCESS_LOCATOR));
}