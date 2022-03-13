package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.Duration;

import static com.herokuapp.utils.Login.login;


public class AuthWithoutCookieTest {

    public static WebDriver driver;
    public static String username = "admin";
    public static String password = "admin";

    @BeforeAll
    static void setUpDriverAndLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        login(driver, username, password);
    }

    @Test
    void checkAuth(){
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        String result = driver.findElement(By.xpath("//div[h3]//p")).getText();
        assertThat(result, containsString("Congratulations! You must have the proper credentials."));
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
