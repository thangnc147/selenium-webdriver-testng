package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Css_Exercises {

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
    public void Register_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
        String phoneErrorTxt = driver.findElement(By.id("txtPhone-error")).getText();

        // Verify
        Assert.assertEquals(nameErrorTxt, "Vui lòng nhập họ tên");
        Assert.assertEquals(emailErrorTxt, "Vui lòng nhập email");
        Assert.assertEquals(emailConfirmErrorTxt, "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(passwordErrorTxt, "Vui lòng nhập mật khẩu");
        Assert.assertEquals(passwordConfirmErrorTxt, "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(phoneErrorTxt, "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Leo Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("Leo@Leo@Leo");
        driver.findElement(By.id("txtCEmail")).sendKeys("Leo@Leo@Leo");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0999888555");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
//        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
//        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
//        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
//        String phoneErrorTxt = driver.findElement(By.id("txtPhone-error")).getText();

        // Verify
        Assert.assertEquals(emailErrorTxt, "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(emailConfirmErrorTxt, "Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Leo Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("thangnguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0999888555");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
//        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
//        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
//        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
//        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
//        String phoneErrorTxt = driver.findElement(By.id("txtPhone-error")).getText();

        // Verify
        Assert.assertEquals(emailConfirmErrorTxt, "Email nhập lại không đúng");

    }

    @Test
    public void Register_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Leo Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345");
        driver.findElement(By.id("txtPhone")).sendKeys("0999888555");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
//        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
//        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
//        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
//        String phoneErrorTxt = driver.findElement(By.id("txtPhone-error")).getText();

        // Verify
        Assert.assertEquals(passwordErrorTxt, "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(passwordConfirmErrorTxt, "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_05_Invalid_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Leo Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0999888555");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
//        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
//        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
//        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
//        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
//        String phoneErrorTxt = driver.findElement(By.id("txtPhone-error")).getText();

        // Verify
        Assert.assertEquals(passwordConfirmErrorTxt, "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Leo Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0333825");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        // Call Varients
//        String nameErrorTxt = driver.findElement(By.id("txtFirstname-error")).getText();
//        String emailErrorTxt = driver.findElement(By.id("txtEmail-error")).getText();
//        String emailConfirmErrorTxt = driver.findElement(By.id("txtCEmail-error")).getText();
//        String passwordErrorTxt = driver.findElement(By.id("txtPassword-error")).getText();
//        String passwordConfirmErrorTxt = driver.findElement(By.id("txtCPassword-error")).getText();
        String phoneErrorTxt1 = driver.findElement(By.id("txtPhone-error")).getText();

        // Step 04 - Verify error message
        Assert.assertEquals(phoneErrorTxt1, "Số điện thoại phải từ 10-11 số.");

        // Step 05 - Enter invalid Phone number
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("123456");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        String phoneErrorTxt2 = driver.findElement(By.id("txtPhone-error")).getText();

        // Step 06 + 07 - Verify error message
        Assert.assertEquals(phoneErrorTxt2,"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
