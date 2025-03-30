package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_27_Mix {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Wait voi Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input@email")));

        // Wait voi Implicit
        driver.findElement(By.cssSelector("input@email"));
    }


    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Wait Implicit
        driver.findElement(By.cssSelector("input@emailaaa"));
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Wait voi Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input@emailaaa")));
    }

    @Test
    public void TC_04_Greater_Than() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        System.out.println("Start = " + getDAteTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input@email")));
        System.out.println("End = " + getDAteTimeNow());


//        driver.findElement(By.cssSelector("input@email"));
    }

    public String getDAteTimeNow() {
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
