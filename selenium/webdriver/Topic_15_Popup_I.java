package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_15_Popup_I {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fix_Popup_In_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.cssSelector("button.login_")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1")).isDisplayed());

        String  credentials = "automationfc";
        driver.findElement(By.cssSelector("div#modal-login-v1 input#account-input")).sendKeys("credentials");
        driver.findElement(By.cssSelector("div#modal-login-v1 input#password-input")).sendKeys("credentials");
        driver.findElement(By.cssSelector("div#modal-login-v1 button.btn-login-v1")).click();

        WebElement errorMessage = driver.findElement(By.xpath(
                "//div[@id='modal-login-v1']//div[@class='row error-login-panel']"));
        Assert.assertEquals(errorMessage.getText(),"Tài khoản không tồn tại!");

        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//div//h4[text()='Đăng nhập']")).isDisplayed());
    }

    @Test
    public void TC_02_Fix_Popup_In_DOM_02() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login")).isDisplayed());

        String  username = "automation@gmail.com",
                password =  "123456";
        driver.findElement(By.cssSelector("div#k-popup-account-login input#user-login")).sendKeys(username);
        driver.findElement(By.cssSelector("div#k-popup-account-login input#user-password")).sendKeys(password);
        driver.findElement(By.cssSelector("div#k-popup-account-login button#btn-submit-login")).click();
        sleepInSeconds(3);

        WebElement errorMessage = driver.findElement(By.cssSelector("div#k-popup-account-login div#password-form-login-message"));
        Assert.assertEquals(errorMessage.getText(),"Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03_Fix_Popup_Not_In_DOM_01() {
        driver.get("https://tiki.vn/");

        WebElement randomAdPopup = driver.findElement(By.cssSelector("div#VIP_BUNDLE"));
        sleepInSeconds(3);
        if(randomAdPopup.isDisplayed()) {
            driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
        }
//      Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();


        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
//        Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);

    }

    @Test
    public void TC_04_Fix_Popup_Not_In_DOM_02() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Create new account']/parent::div/parent::div")).size(), 0);

    }

    @Test
    public void TC_05_Random_Popup_In_DOM() {

    }

    @Test
    public void TC_06_Random_Popup_In_DOM_01() {

    }

    @Test
    public void TC_07_Random_Popup_In_DOM_02() {

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
