package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_21_Wait_FindElement {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_FinElement() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");

        // 1 - Nếu tìm thấy duy nhất 1 Element
            // Trả về đúng Element đó
            // Không cần chờ hết timeout của implicit
        driver.findElement(By.cssSelector("input#FirstName"));

        // 2 - Nếu tìm thấy nhiều hơn 1 Element
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing");

        // 3 - Nếu không tìm thấy Element nào
            // Mới vào kiếm element nhwng không thấy
            // Tìm lại mà thấy thì ko cần chờ hết thời gian còn lại
            // Hết thời gian thì sẽ fail
            // Show lỗi: NoSuchSelection
        driver.findElement(By.cssSelector("input[type='textsss']"));


    }


    @Test
    public void TC_02_FinElements() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");

        List<WebElement> elements = null;

        // 1 - Nếu tìm thấy duy nhất 1 Element
            // Trả về đúng 1 cái
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        // 2 - Nếu tìm thấy nhiều hơn 1 Element
            // Trả về toàn bộ element matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        // 3 - Nếu không tìm thấy Element nào
            /* Nếu hết time thì:
            - Trả về list element = 0
            - Không fail testcase   */
        elements = driver.findElements(By.cssSelector("input[type='textsss']"));
        System.out.println(elements.size());

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
