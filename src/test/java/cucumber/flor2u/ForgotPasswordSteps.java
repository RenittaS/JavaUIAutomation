package cucumber.flor2u;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ForgotPasswordSteps {
    @Given("I am on MainPage")
    public void iAmOnMainPage() {
        new MainPage().openMainPage();
    }

    @When("I click Enter button")
    public void iClickEnterButton() {
        new MainPage().enterLinkClick();
    }

    @And("I click Forgot your password link")
    public void iClickForgotYourPasswordLink() {
        new MainPage().forgotPasswordLinkClick();
    }

    @And("I send {string}")
    public void iFillInEmailField(String email) {
        new ForgotPasswordPage().emailInputFill(email).sendButtonClick();
    }

    @Then("I see new page with success message")
    public void iSeeNewPageWithSuccessMessage() {
        new SendPasswordPage().message.shouldHave(Condition.text("Ссылка на восстановление пароля была отправлена!"));
//                .shouldBe(Condition.exactText("Ссылка на восстановление пароля была отправлена!"));
    }
}
