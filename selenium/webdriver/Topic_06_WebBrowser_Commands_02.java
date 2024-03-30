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
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Page_URL() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Page_Title() {
        // Access Page
        driver.get("http://live.techpanda.org/");

        // Click 'My Account' button
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        // Verify page Title
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        // Click 'Create Account' button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        // Verify page Title
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Page_Navigation() {
        // Access Page
        driver.get("http://live.techpanda.org/");

        // Click 'My Account' button
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Click 'Create Account' button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        // Verify page URL
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        // Back to Login page
        driver.navigate().back();
        sleepInSeconds(2);
        // Verify page URL
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        // Forward to Register Page
        driver.navigate().forward();
        sleepInSeconds(2);
        // Verify page Title
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source() {
        // Access Page
        driver.get("http://live.techpanda.org/");

        // Click 'My Account' button
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        // Verify page contain "Login or Create an Account"
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        // CLick CREATE AN ACCOUNT button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        // Verify page contain "Create an Account"
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

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
