package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Register {

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

    String username, password;

    @Test
    public void TC_01_Register() {
        // Truy cập - https://demo.guru99.com/
        driver.get("https://demo.guru99.com/");

        //Nhập Email bất kì
        driver.findElement(By.name("emailid")).sendKeys("thangnguyen.tsp@gmail.com");
        driver.findElement(By.name("btnLogin")).click();


        // Click Submit button
        // Get cái User/ Password lưu vào một phần
         username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
         password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }

    @Test
    public void TC_02_Login() {
        // Truy cập trang login - https://demo.guru99.com/v4/
        driver.get("https://demo.guru99.com/v4/");


        // Nhập Username/ password ở màn hình Register
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);

        // Click Login
        driver.findElement(By.name("btnLogin")).click();

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
