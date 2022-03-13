package pageobject.flor2u;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends BaseView {

    public static final String ENTER_LINK_LOCATOR = "//a[contains(text(), 'Войти')]";
    public static final String FORGOT_PASSWORD_LINK_LOCATOR = "//a[contains(text(), 'Забыл пароль')]";
    public static final String DELIVERY_WINDOW_LINK_LOCATOR = "//a[@class='header__region']";
    public static final String DELIVERY_WINDOW_SEARCH_LOCATOR = "search-field";
    public static final String DELIVERY_WINDOW_SEARCH_INPUT_LOCATOR = "default";
    public static final String DELIVERY_WINDOW_SEARCH_CHOICE_LOCATOR = "//li[@data-option-array-index=\"8\"]";
    public static final String HEADER_AFTER_CHANGE_REGION_LOCATOR = "//h1";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ENTER_LINK_LOCATOR)
    public WebElement enterLink;

    @Step("Клик на <Войти>")
    public MainPage enterLinkClick(){
        enterLink.click();
        return this;
    }

    @FindBy(xpath = FORGOT_PASSWORD_LINK_LOCATOR)
    public WebElement forgotPasswordLink;

    @Step("Клик на <Забыли пароль>")
    public ForgotPasswordPage forgotPasswordLinkClick(){
        forgotPasswordLink.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(ForgotPasswordPage.EMAIL_INPUT_LOCATOR)));
        return new ForgotPasswordPage(driver);
    }

    @FindBy(xpath = DELIVERY_WINDOW_LINK_LOCATOR)
    public WebElement deliveryWindowLink;

    @Step("Клик на <Регион доставки>")
    public MainPage deliveryWindowLinkClick(){
        deliveryWindowLink.click();
        return this;
    }

    @FindBy(className = DELIVERY_WINDOW_SEARCH_LOCATOR)
    public WebElement deliveryWindowSearch;

    @Step("Клик на поле поиска")
    public MainPage deliveryWindowSearchClick() {
        deliveryWindowSearch.click();
        return this;
    }

    @FindBy(className = DELIVERY_WINDOW_SEARCH_INPUT_LOCATOR)
    public WebElement deliveryWindowSearchInput;

    @FindBy(xpath = DELIVERY_WINDOW_SEARCH_CHOICE_LOCATOR)
    public WebElement deliveryWindowSearchChoice;

    @Step("Ввод города")
    public MainPage deliveryWindowSearchInputFill(String city) {
        deliveryWindowSearchInput.sendKeys(city);
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(DELIVERY_WINDOW_SEARCH_CHOICE_LOCATOR)));
        deliveryWindowSearchChoice.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(HEADER_AFTER_CHANGE_REGION_LOCATOR)));

        return new MainPage(driver);
    }

    @FindBy(xpath = HEADER_AFTER_CHANGE_REGION_LOCATOR)
    public WebElement headerAfterChangeRegion;

    @Step("Получаем заголовок страницы")
    public String headerAfterChangeRegionText(){
        String actualString = headerAfterChangeRegion.getText();
        return actualString;
    }

}
