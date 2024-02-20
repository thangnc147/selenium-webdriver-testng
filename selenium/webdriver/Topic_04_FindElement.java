package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_FindElement {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        driver.get("http://live.techpanda.org/index.php");

         // Click vào My Account ở trên Header
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        String successMessageText = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        // Samsung Galaxy was added to your shopping cart.
        Assert.assertEquals("Samsung Galaxy was added to your shopping cart.");
    }

    @Test
    public void TC_02() {

    }

//    @Test
//    public void TC_03 {
//
//    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
