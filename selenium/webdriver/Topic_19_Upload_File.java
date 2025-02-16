package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
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
import java.util.List;
import java.util.Set;

public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir") + "\\uploadFiles\\";
    String image01 = "Image01.png";
    String image02 = "Image02.png";
    String image03 = "Image03.png";

    String image01Path = projectPath + image01;
    String image02Path = projectPath + image02;
    String image03Path = projectPath + image03 ;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputButton = By.xpath("//span[text()='Add files...']/following-sibling::input");

        // Load file
        driver.findElement(inputButton).sendKeys(image01Path);
        Thread.sleep(2000);
        driver.findElement(inputButton).sendKeys(image02Path);
        Thread.sleep(2000);
        driver.findElement(inputButton).sendKeys(image03Path);
        Thread.sleep(2000);

        // Verify loaded files
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image03 + "']")).isDisplayed());

        // Cick Upload each file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify Uploaded files
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image03 + "']")).isDisplayed());

    }


    @Test
    public void TC_02_Multiple_Files() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputButton = By.xpath("//span[text()='Add files...']/following-sibling::input");

        // Load file
        driver.findElement(inputButton).sendKeys(image01Path + "\n" + image02Path + "\n" + image03Path);
        Thread.sleep(2000);
//        driver.findElement(inputButton).sendKeys(image02Path);
//        Thread.sleep(2000);
//        driver.findElement(inputButton).sendKeys(image03Path);
//        Thread.sleep(2000);

        // Verify loaded files
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//p[@class='name' and text()='" + image03 + "']")).isDisplayed());
        System.out.println("Verify loaded files success");

        // Cick Upload each file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify uploaded files
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image01 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image02 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + image03 + "']")).isDisplayed());
        System.out.println("Verify uploaded files success");



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
