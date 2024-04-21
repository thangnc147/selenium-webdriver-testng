
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Radio_Checkbox {

    WebDriver driver;
    /*String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");*/

    @BeforeClass
    public void beforeClass() {
        /*if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }*/

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        //Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);

        // Verify button đã được enable
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy ra mã màu của button
        String registerBackgroundColorRGB =  registerButton.getCssValue("background-color");
        System.out.println("Background Color RGB = " + registerBackgroundColorRGB);

        Color registerBackgroundColor = Color.fromString(registerBackgroundColorRGB);

        // Convert qua kiểu hexa
        String registerBackgroundColorHexa = registerBackgroundColor.asHex();
        System.out.println("Background Color Hexa = " + registerBackgroundColorHexa);


        Assert.assertEquals(registerBackgroundColorHexa, "#ef5a00");


    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        // Chuyển qua tab Đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button is disabled
        Assert.assertFalse(loginButton.isEnabled());

        // Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        // Nhập Email/ Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dam@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(2);

        // Verify login button is enabled
        Assert.assertTrue(loginButton.isEnabled());

        // Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
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
