package com.herokuapp.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtils {

    //использовать, когда на странице сложно добраться до к-л элемента, закрывается всплывающими окнама, баннерами и
    // т.д.)
    public static void clickWithJs (WebElement button, WebDriver driver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", button);
    }

}
