package ru.flor2u;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Задание 1: Перенести сценарии из своего проекта из Selenium IDE в код проектов.
// Сценарий 2.

public class ForgotPassword {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://flor2u.ru");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Войти')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Войти')]")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(text(), 'Забыл пароль')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Забыл пароль')]")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'recovery-form')]//input[@name='login']")));
        driver.findElement(By.xpath("//div[contains(@class, 'recovery-form')]//input[@name='login']")).
                sendKeys("krasnova.irina@gmail.com");

        driver.findElement(By.xpath("//button[text()='Узнать пароль']")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        String actualString = driver.findElement(By.className("b-success__text")).getText();
        if (actualString.contains("Ссылка на восстановление пароля была отправлена!")){
            System.out.println("Completed!");
        } else System.out.println("Failed :(");

        driver.quit();
    }
}
