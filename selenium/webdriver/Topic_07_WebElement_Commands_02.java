package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Commands_02 {

    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        // Access web
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement mail = driver.findElement(By.cssSelector("input#mail"));
        WebElement under18 = driver.findElement(By.cssSelector("input#under_18"));
        WebElement education = driver.findElement(By.cssSelector("textarea#edu"));
        WebElement nameUnder5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        // Verify element displayed
        Assert.assertTrue(mail.isDisplayed());
        Assert.assertTrue(under18.isDisplayed());
        Assert.assertTrue(education.isDisplayed());
        // Verify element not displayed
        Assert.assertFalse(nameUnder5.isDisplayed());

        // Note
        if (mail.isDisplayed()) {
            mail.sendKeys("Automation Testing");
            System.out.print("Email Textbox is displayed" + '\n');
        } else {
            System.out.print("Email Textbox is not displayed" + '\n');
        }

        if (under18.isDisplayed()) {
            under18.sendKeys("Automation Testing");
            System.out.print("Under 18 Radio is displayed" + '\n');
        } else {
            System.out.print("Under 18 Radio is not displayed" + '\n');
        }

        if (education.isDisplayed()) {
            education.click();
            System.out.print("Education TextArea is displayed" + '\n');
        } else {
            System.out.print("Education TextArea is not displayed" + '\n');
        }

        if (nameUnder5.isDisplayed()) {
            System.out.print("Name: User 5 is displayed" + '\n');
        } else {
            System.out.print("Name: User 5 is not displayed" + '\n');
        }
    }

    @Test
    public void TC_02_Enable() {
        // Access web
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement mail = driver.findElement(By.cssSelector("input#mail"));
        WebElement under18 = driver.findElement(By.cssSelector("input#under_18"));
        WebElement education = driver.findElement(By.cssSelector("textarea#edu"));
        WebElement nameUnder5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        WebElement jobRole1 = driver.findElement(By.cssSelector("select#job1"));
        WebElement interestsDevelopment = driver.findElement(By.cssSelector("input#development"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));

        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));
        WebElement slider1 = driver.findElement(By.cssSelector("slider-1"));


        // Verify elements enable
        Assert.assertTrue(mail.isDisplayed());
        // Verify elements disable

        // Cosole log and verify result


    }

    @Test
    public void TC_03_Selected() {

    }

    @Test
    public void TC_04_MailChimp() {


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public  void sleepInSeconds (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
