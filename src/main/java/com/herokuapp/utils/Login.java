package com.herokuapp.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Login {
    public static Set<Cookie> allCookies;

    public static void login (WebDriver driver, String username, String password) {
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        String url = driver.getCurrentUrl().replaceAll("http://", "");
        String URL = "http://" + username  + ":" + password + "@" + url;
        driver.get(URL);
        allCookies = driver.manage().getCookies();
    }

    public static Set<Cookie> login2 (WebDriver driver) {
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");
        driver.findElement(By.name("username")).sendKeys("abc123");
        driver.findElement(By.name("password")).sendKeys("123xyz");
        driver.findElement(By.name("submit")).click();
        allCookies = driver.manage().getCookies();
        System.out.println(allCookies);
        return allCookies;
    }

    public static void login3 (WebDriver driver) throws InterruptedException {
        driver.get("https://www.phptravels.net/login");
        driver.findElement(By.name("email")).sendKeys("user@phptravels.com");
        driver.findElement(By.name("password")).sendKeys("demouser");
        driver.findElement(By.xpath("//span[text()=\"Login\"]")).click();
        Thread.sleep(3000);
        writeCookiesToFile(driver);
    }

    public static void writeCookiesToFile(WebDriver driver) {
        File file = new File("Cookies.data");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write((cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - "+ e.getLocalizedMessage());
        }
    }

    public static Set<Cookie> readCookiesFromFile() {
        Cookie cookie;
        Set<Cookie> setCookies = new HashSet();
        try {
            File file = new File("Cookies.data");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String date= str.nextToken();
                    if (!(date).equals("null")) {
                        expiry = new Date(System.currentTimeMillis()*2);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                    setCookies.add(cookie);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - "+ ex.getLocalizedMessage());
        }
        return setCookies;
    }
}
