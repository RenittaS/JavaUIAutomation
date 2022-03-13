package pageobject.flor2u;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseView {
    WebDriver driver;
    WebDriverWait webDriverWait;

    //Вызов статического метода PageFactory.initElements(driver, this) - он так пишется всегда - обеспечивает
    // инициализацию элементов WebElements, указанных через аннотацию @FindBy (т.е. заменяет конструкцию
    // driver.findElement(By.id("result_textview"). Причем несмотря на возможно большое количество @FindBy они
    // инициализируются ровно в тот момент, когда к ним конкретно обращаются (а не все сразу).
    public BaseView(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
}