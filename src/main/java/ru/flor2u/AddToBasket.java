package ru.flor2u;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Задание 1: Перенести сценарии из своего проекта из Selenium IDE в код проектов.
// Сценарий 3.

public class AddToBasket {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://flor2u.ru/");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[1][@class=\"header__menu-item \"]/a[contains(text(), 'Цветы')]")));
        WebElement headerMenuFlower = driver.findElement
                (By.xpath("//div[1][@class=\"header__menu-item \"]/a[contains(text(), 'Цветы')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(headerMenuFlower).perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ромашками")));
        driver.findElement(By.linkText("Ромашками")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[1][@class=\"home__block product _gift\"]/a[@class='product__show']")));
        WebElement firstItemInCatalog = driver.findElement
                (By.xpath("//div[1][@class=\"home__block product _gift\"]/a[@class='product__show']"));
        actions.moveToElement(firstItemInCatalog).perform();
        firstItemInCatalog.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String itemName = driver.findElement(By.xpath("//div[@class='fancybox-inner']//h1")).getText();
        System.out.println("Наименование товара в каталоге: " + itemName);
        driver.findElement(By.xpath("//a[@class='btn _green detail__buy']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[@class='basket-name-blk']//div[contains(@class, 'basket__item-name')]")));
        String basketName = driver.findElement
                (By.xpath("//div[@class='basket-name-blk']//div[contains(@class, 'basket__item-name')]")).getText();
        System.out.println("Наименование товара в корзине: " + basketName);

        if (basketName.contains(itemName)){
            System.out.println("Completed!");
        } else System.out.println("Failed :(");
        driver.quit();
    }
}
