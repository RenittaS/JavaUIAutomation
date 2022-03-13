package com.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.herokuapp.utils.ColorMatcher.hasColor;
import static com.herokuapp.utils.JsUtils.clickWithJs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Duration;


public class CheckSeleniumFunctionsTest {

    public static WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com/";

    @BeforeAll
    public static void registerDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupBrowser(){
        driver = new ChromeDriver();
//      driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    @DisplayName("Drag&Drop")
    void dragAndDrop() {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//a[text()='Drag and Drop']")).click();

        WebElement from = driver.findElement(By.id("column-a"));
        WebElement to = driver.findElement(By.id("column-b"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", from, to);

        String textTo = to.getText();
        if(textTo.equals("A")) {
            System.out.println("PASS: File is dropped to target as expected");
        }else {
            System.out.println("FAIL: File couldn't be dropped to target as expected");
        }
    }

    @Test
    @DisplayName("Check Color in Hamcrest - 2 ways")
    void changeColor() throws InterruptedException {
        driver.get(BASE_URL + "challenging_dom");

        WebElement colorBlockBlue = driver.findElement(By.className("button"));
        assertThat(colorBlockBlue.getCssValue("background-color"), is("rgba(43, 166, 203, 1)"));

        WebElement colorBlockGreen = driver.findElement(By.xpath("//a[@class='button alert']"));
        assertThat(colorBlockGreen, hasColor("rgba(198, 15, 19, 1)"));

        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Use JavaScript Executor")
    void jsExecutorTest() {
        driver.get(BASE_URL + "javascript_alerts");

        WebElement buttonOne = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        clickWithJs(buttonOne, driver);
        driver.switchTo().alert().accept();

        WebElement webElement = driver.findElement(By.id("result"));
        String result = webElement.getText();
        assertThat(result, is("You successfully clicked an alert"));

        WebElement buttonTwo = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        clickWithJs(buttonTwo, driver);
        driver.switchTo().alert().dismiss();

        WebElement webElementTwo = driver.findElement(By.id("result"));
        String resultTwo = webElementTwo.getText();
        assertThat(resultTwo, is("You clicked: Cancel"));

        WebElement buttonThree = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        clickWithJs(buttonThree, driver);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Some text here");
        alert.accept();

        WebElement webElementThree = driver.findElement(By.id("result"));
        String resultThree = webElementThree.getText();
        assertThat(resultThree, is("You entered: Some text here"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
