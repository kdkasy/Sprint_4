package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest{
    protected WebDriver driver;

    @Before
    public void setupBrowser(){
        driver = getDriver("chrome");
        driver.get(MainPage.URL);

        setCookie(new Cookie("Cartoshka", "true"));
        setCookie(new Cookie("Cartoshka-legacy", "true"));
        driver.navigate().refresh();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    private void setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    private WebDriver getDriver (String driverType)
        {
            switch (driverType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                case "mozilla":
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                default:
                    throw new IllegalArgumentException("Driver type is not supported");
            }
        }
}

