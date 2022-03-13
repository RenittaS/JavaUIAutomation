package ru.flor2u;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckFlor2uFunctionsTest {
    private static WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://flor2u.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Test
    public void changeRegion() {
        driver.get(BASE_URL);
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='header__region']")));
        driver.findElement
                (By.xpath("//a[@class='header__region']")).click();
        driver.findElement
                (By.className("search-field")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.className("default")));
        driver.findElement
                (By.className("default")).sendKeys("Балашиха");
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//li[@data-option-array-index=\"8\"]")));
        driver.findElement
                (By.xpath("//li[@data-option-array-index=\"8\"]")).click();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        String actualString = driver.findElement
                (By.xpath("//h1")).getText();
        Assertions.assertEquals("Доставка цветов в Балашихе", actualString);
}

    @Test
    public void forgotPassword(){
        driver.get(BASE_URL);
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(text(), 'Войти')]")));
        driver.findElement
                (By.xpath("//a[contains(text(), 'Войти')]")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(text(), 'Забыл пароль')]")));
        driver.findElement
                (By.xpath("//a[contains(text(), 'Забыл пароль')]")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'recovery-form')]//input[@name='login']")));
        driver.findElement
                        (By.xpath("//div[contains(@class, 'recovery-form')]//input[@name='login']")).
                sendKeys("krasnova.irina@gmail.com");

        driver.findElement
                (By.xpath("//button[text()='Узнать пароль']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//h3")));
        String actualMessage = driver.findElement
                (By.xpath("//h3")).getText();
        Assertions.assertTrue(actualMessage.contains("Ссылка на восстановление пароля была отправлена!"));
    }

    @Test
    void addToBasket(){
        driver.get(BASE_URL);
        Actions actions = new Actions(driver);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[1][@class=\"header__menu-item \"]/a[contains(text(), 'Цветы')]")));
        WebElement headerMenuFlower = driver.findElement
                (By.xpath("//div[1][@class=\"header__menu-item \"]/a[contains(text(), 'Цветы')]"));
        actions.moveToElement(headerMenuFlower).perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.linkText("Ромашками")));
        driver.findElement
                (By.linkText("Ромашками")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[1][@class=\"home__block product _gift\"]/a[@class='product__show']")));
        WebElement firstItemInCatalog = driver.findElement
                (By.xpath("//div[1][@class=\"home__block product _gift\"]/a[@class='product__show']"));
        actions.moveToElement(firstItemInCatalog).perform();
        firstItemInCatalog.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[@class='fancybox-inner']//h1")));
        String itemName = driver.findElement
                (By.xpath("//div[@class='fancybox-inner']//h1")).getText().trim();
        System.out.println("Наименование товара в каталоге: " + itemName);
        driver.findElement
                (By.xpath("//a[@class='btn _green detail__buy']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[@class='basket-name-blk']//div[contains(@class, 'basket__item-name')]")));
        String basketName = driver.findElement
                (By.xpath("//div[@class='basket-name-blk']//div[contains(@class, 'basket__item-name')]")).getText();
        System.out.println("Наименование товара в корзине: " + basketName);
        Assertions.assertTrue(basketName.contains(itemName));
    }

    @AfterEach
    void closeWindow(){
        driver.quit();
    }
}
