package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

import static com.herokuapp.utils.Login.login2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class Guru99AuthWithCookieTest {

    public static WebDriver driver;
    public static Set<Cookie> allCookies;

    @BeforeAll
    public static void registerDriverAndWriteCookie(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        allCookies = login2(driver);
        System.out.println(allCookies);
        driver.close();
    }

    @BeforeEach
    public void registerDriverUpdate(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    void authWithSavedCookie() {
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");
        for(Cookie cookie : allCookies)
        {
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
        driver.get("http://demo.guru99.com/test/cookie/selenium_cookie.php");
        String element = driver.findElement(By.xpath("//h2")).getText();

        assertThat(element, is("You are logged In"));
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }


}
