package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Explicit_Ajax {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Calendar() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
//        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Wait and Verify Calendar element is dispalyed
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        // Wait and verify Text
//        explicitWait.until(ExpectedConditions
//                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Lanel1"), "No Selected Dates to display."));

        // Find Element and verify Text
        WebElement selectedDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Lanel1"));
        Assert.assertEquals(selectedDate.getText(), "No Selected Dates to display.");

        // Wait and Click Element
        explicitWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//td/a[text()='22']"))).click();

        // Wait and verify ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("dic[id$='RadCalendar1']>div.raDiv"))));

        // Wait and verify Text
        explicitWait.until(ExpectedConditions
                .textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Lanel1"), "Thursday, August 22, 2024"));
    }


    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }

    @Test
    public void TC_04_() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));


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
