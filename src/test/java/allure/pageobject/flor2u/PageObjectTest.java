package allure.pageobject.flor2u;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.flor2u.MainPage;
import pageobject.flor2u.SendPasswordPage;

public class PageObjectTest extends BaseTest {

    private static final String BASE_URL = "https://flor2u.ru";
    public static final String EMAIL = "krasnova.irina@gmail.com";
    public static final String CITY = "Балашиха";

//    @Test
//    @Description("Изменение региона")
//    void changeRegionTest() {
//        driver.get(BASE_URL);
//        String actualString = new MainPage(driver)
//                .deliveryWindowLinkClick()
//                .deliveryWindowSearchClick()
//                .deliveryWindowSearchInputFill(CITY).headerAfterChangeRegionText();
//
//        Assertions.assertEquals("Доставка цветов в Балашихе", actualString);
//    }

    @Test
    @Description("Восстановление пароля")
    void forgotPasswordTest() {
        driver.get(BASE_URL);
        new MainPage(driver)
                .enterLinkClick()
                .forgotPasswordLinkClick()
                .emailInputFill(EMAIL)
                .sendButtonClick();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.className(SendPasswordPage.TEXT_SUCCESS_LOCATOR)));
        String actualMessage = (new SendPasswordPage(driver).message).getText();
        Assertions.assertTrue(actualMessage.contains("Ссылка на восстановление пароля была отправлена!"));
    }




}
