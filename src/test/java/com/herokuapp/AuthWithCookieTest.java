package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.herokuapp.utils.Login.readCookiesFromFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.Duration;
import static com.herokuapp.utils.Login.login3;

//Также есть способ забирать куку с помощью API запроса (подключать библиотеку OkHTTP и Jackson, чтобы распарсить
// JSON ответ с нужной кукой)

public class AuthWithCookieTest {

    public static WebDriver driver;

    @BeforeAll
    public static void registerDriverAndWriteCookie() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        login3(driver);
        driver.close();
    }

    @BeforeEach
    public void registerDriverUpdate(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    //Тест не рабочий, куки передаются (или сохраняются) не корректно
    //На реальном проекте нужно спрашивать у разработчиков, какую куку нужно сохранить (см.следующий тест)
    void authWithSavedCookie() throws InterruptedException {
        driver.get("https://www.phptravels.net");
        System.out.println("Печатаем куки из теста" + readCookiesFromFile());
        for(Cookie cookie : readCookiesFromFile())
        {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.get("https://www.phptravels.net/account/dashboard");
        String result = driver.findElement(By.className("author__meta")).getText();
        assertThat(result, containsString("Welcome Back"));
    }

    @Test
    void loginToDiaryWithCookie() {
        driver.get("https://diary.ru");
        Cookie authCookie = new Cookie("_identity_", "3b6b10fc845b514d13ea637db19de46a6adbe7d11b9e49f95dc9edb2292a0977a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A48%3A%22%5B3564034%2C%22esT26loTHw1Uv-SZc6gjn00Kj3njs5Xj%22%2C900%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();
        String result = driver.findElement(By.xpath("//*[@class=\"username\"]/a")).getText();
        assertThat(result, containsString("renittaS"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
