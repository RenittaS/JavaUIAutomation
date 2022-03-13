package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Duration;
import java.util.Set;

//Реальный список куки на сайте отличается и меняется. Тест должен падать на проверке записи заданной куки.

public class CookiesSimpleTutorialTest {
    public static WebDriver driver;
    public static String navUrl;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        navUrl = "https://www.baeldung.com/";
    }

    @Test
    @DisplayName("Проверка наличия записей cookies в драйвере")
    public void whenNavigate_thenCookiesExist() {
        driver.navigate().to(navUrl);
        Set cookies = driver.manage().getCookies();
        assertThat(cookies, notNullValue());
    }

    @Test
    @DisplayName("Проверка записи заданной cookie")
    public void whenNavigate_thenLpCookieIsHasCorrectValue() {
        driver.navigate().to(navUrl);
        Cookie lpCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        assertThat(lpCookie.getValue(), containsString("vid%253De41b7b28641e4405becb362b6a203500%2526pageViews%253D20%2526sessionPageCount%253D2%2526lastVisitedAt%253D1646133501101%2526weeklySessionCount%253D6%2526lastSessionAt%253D1646132852838"));
    }

    @Test
    @DisplayName("Проверка параметров заданной cookie")
    public void whenNavigate_thenLpCookieHasCorrectProps() {
        driver.navigate().to(navUrl);
        Cookie lpCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        assertThat(lpCookie.getDomain(), equalTo(".baeldung.com"));
        assertThat(lpCookie.getPath(), equalTo("/"));
        assertThat(lpCookie.getExpiry(), is(not(nullValue())));
        assertThat(lpCookie.isSecure(), equalTo(false));
        assertThat(lpCookie.isHttpOnly(), equalTo(false));
    }

    @Test
    @DisplayName("Добавление cookie")
    public void whenAddingCookie_thenItIsPresent() {
        driver.navigate().to(navUrl);
        Cookie cookie = new Cookie("foo", "bar");
        driver.manage().addCookie(cookie);
        Cookie driverCookie = driver.manage().getCookieNamed("foo");
        assertThat(driverCookie.getValue(), equalTo("bar"));
    }

    @Test
    @DisplayName("Удаление cookie")
    public void whenDeletingCookie_thenItIsAbsent() {
        driver.navigate().to(navUrl);
        Cookie lpCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        assertThat(lpCookie, is(not(nullValue())));
        driver.manage().deleteCookie(lpCookie);
        Cookie deletedCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        assertThat(deletedCookie, is(nullValue()));
    }

    @Test
    @DisplayName("Переопределение cookie через удаление")
    public void whenOverridingCookie_thenItIsUpdated() {
        driver.navigate().to(navUrl);
        Cookie lpCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        driver.manage().deleteCookie(lpCookie);
        Cookie newLpCookie = new Cookie("_drip_client_9539554", "foo");
        driver.manage().addCookie(newLpCookie);
        Cookie overriddenCookie = driver.manage().getCookieNamed("_drip_client_9539554");
        assertThat(overriddenCookie.getValue(), equalTo("foo"));
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }

}
