package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Topic_06_WebBrowser_Commands_02 {

    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver(); //**
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Page_URL() {

    }

    @Test
    public void TC_02_Page_Title() {

    }

    @Test
    public void TC_03_Page_Navigation() {

    }

    @Test
    public void TC_04_Page_Source() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
