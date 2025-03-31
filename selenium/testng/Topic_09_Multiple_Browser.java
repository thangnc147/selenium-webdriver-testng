package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Topic_09_Multiple_Browser {
    WebDriver driver;
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");

    String username = "selenium_11_01@gmail.com";
    String password =  "111111";
    String domainUrl;
    @BeforeClass
    @Parameters({"server", "browser"})
    public void beforeClass(String serverName, String browserName) {
        // If-Else
        if (serverName.equalsIgnoreCase("Dev")) {
            domainUrl = "dev.techpanda.org";
        } else if (serverName.equalsIgnoreCase("QA")) {
            domainUrl = "qa.techpanda.org";
        } else  if (serverName.equalsIgnoreCase("LIVE")){
            domainUrl = "live.techpanda.org";
        } else {
            throw new RuntimeException("Server is not Valid.");
        }

        // Switch-Case
        switch (browserName) {
            case ("Chrome"):
                driver = new ChromeDriver();
                break;
            case ("Edge"):
                driver = new EdgeDriver();
                break;
            case ("FireFox"):
                driver = new FirefoxDriver();
                break;
            default:
                new RuntimeException("Browser is not Valid.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_LoginToSystem() throws InterruptedException {
        driver.get("http://" + domainUrl + "/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
