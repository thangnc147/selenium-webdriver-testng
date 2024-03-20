package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG() {
        driver = new FirefoxDriver();

        driver.get("https://www.facebook.com/");

        // Trong java có nhiều thư viện để verify dữ liệu
        // TestNG framework (Unit/ Intergration/ UI Automation Test)
        // JUnit4/ TestNg/ JUnit 5/ Hamcrest/ AssertJ/...

        // Kiểu dữ liệu nhận vào là boolean (true/ false)
        // Khi mong muốn dữ liệu trả về đúng thì assertTrue
        Assert.assertTrue(driver.getPageSource().contains("Facebook"));
        // Khi mong muốn dữ liệu trả về sai thì assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Create a New Account"));

            // Các hàm trả về boolean
            // Có tiền tố là isXXX
            // WebElement
        driver.findElement(By.id("email")).isDisplayed();
        driver.findElement(By.id("email")).isEnabled();
        driver.findElement(By.id("email")).isSelected();
        new Select(driver.findElement(By.id("email"))).isMultiple();

        // asserEquals
        // mong đợi kết thực tế đúng với mong đợi (Actual = Expected)
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");


    }
}
