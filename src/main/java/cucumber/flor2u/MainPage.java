package cucumber.flor2u;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


public class MainPage {

    public static final String ENTER_LINK_LOCATOR = "//a[contains(text(), 'Войти')]";
    public static final String FORGOT_PASSWORD_LINK_LOCATOR = "//a[contains(text(), 'Забыл пароль')]";
    public static final String DELIVERY_WINDOW_LINK_LOCATOR = "//a[@class='header__region']";
    public static final String DELIVERY_WINDOW_SEARCH_LOCATOR = "search-field";
    public static final String DELIVERY_WINDOW_SEARCH_INPUT_LOCATOR = "default";
    public static final String DELIVERY_WINDOW_SEARCH_CHOICE_LOCATOR = "//li[@data-option-array-index=\"8\"]";
    public static final String HEADER_AFTER_CHANGE_REGION_LOCATOR = "//h1";

    @Step("Открыть главную страницу")
    public MainPage openMainPage(){
        open("https://flor2u.ru/");
        return this;
    }

    private SelenideElement enterLink = $(By.xpath(ENTER_LINK_LOCATOR));

    @Step("Клик на <Войти>")
    public MainPage enterLinkClick(){
        enterLink.click();
        return this;
    }

    private SelenideElement forgotPasswordLink = $(By.xpath(FORGOT_PASSWORD_LINK_LOCATOR));

    @Step("Клик на <Забыли пароль>")
    public ForgotPasswordPage forgotPasswordLinkClick(){
        forgotPasswordLink.click();
        return page(ForgotPasswordPage.class);
    }

    private SelenideElement deliveryWindowLink = $(By.xpath(DELIVERY_WINDOW_LINK_LOCATOR));

    @Step("Клик на <Регион доставки>")
    public MainPage deliveryWindowLinkClick(){
        deliveryWindowLink.click();
        return this;
    }

    private SelenideElement deliveryWindowSearch = $(By.xpath(DELIVERY_WINDOW_SEARCH_LOCATOR));

    @Step("Клик на поле поиска")
    public MainPage deliveryWindowSearchClick() {
        deliveryWindowSearch.click();
        return this;
    }

    private SelenideElement deliveryWindowSearchInput = $(By.className(DELIVERY_WINDOW_SEARCH_INPUT_LOCATOR));

    private SelenideElement deliveryWindowSearchChoice = $(By.xpath(DELIVERY_WINDOW_SEARCH_CHOICE_LOCATOR));

    @Step("Ввод города")
    public MainPage deliveryWindowSearchInputFill(String city) {
        deliveryWindowSearchInput.sendKeys(city);
        deliveryWindowSearchChoice.click();
        return page(MainPage.class);
    }

    private SelenideElement headerAfterChangeRegion = $(By.xpath(HEADER_AFTER_CHANGE_REGION_LOCATOR));

    @Step("Получаем заголовок страницы")
    public String headerAfterChangeRegionText(){
        String actualString = headerAfterChangeRegion.getText();
        return actualString;
    }

}
