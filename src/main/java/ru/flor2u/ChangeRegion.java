package ru.flor2u;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Задание 1: Перенести сценарии из своего проекта из Selenium IDE в код проектов.
// Сценарий 1.

public class ChangeRegion {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://flor2u.ru");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='header__region']")));
        driver.findElement(By.xpath("//a[@class='header__region']")).click();
        driver.findElement(By.className("search-field")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("default")));
        driver.findElement(By.className("default")).sendKeys("Балашиха");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-option-array-index=\"8\"]")));
        driver.findElement(By.xpath("//li[@data-option-array-index=\"8\"]")).click();

        TimeUnit.SECONDS.sleep(5);
        String actualString = driver.findElement(By.xpath("//h1")).getText();
        if (actualString.contains("Доставка цветов в Балашихе")){
            System.out.println("Completed!");
        } else System.out.println("Failed :(");
        driver.quit();
    }
}
