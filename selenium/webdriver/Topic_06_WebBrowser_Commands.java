package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {

    // Các câu lệnh để thao tác với Browser
    // driver.
    WebDriver driver;

    // Các câu lệnh để thao tác với Element
    // element.
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        // Muốn dùng đươc thì phải khởi tạo
        // Nếu không khởi tạo sẽ gặp lỗi: NullPointerException
        driver = new FirefoxDriver();
        System.out.println(driver);
        // FirefoxDriver: firefox on windows (17ff11c4-1223-4a92-ac88-d76bbfa159dc)

        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new InternetExplorerDriver();
        // driver = new OperaDriver(); // Selenium 4 ngừng support
        // driv = new HTMLUnit(); // Headless Browser
        // Từ 2016: Chrome/ Firefox cũng đã support chạy dạng Headless browser
        // Headless: Crawl data (DA)/ Dev FE

        // Selemium ver 3 (viết như dưới)
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // Selenium ver 4 (viết như dưới)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        // Mở ra 1 page URL bất kì
        driver.get("https://www.facebook.com/");
        driver.get("https://www.facebook.com/lite/");
        driver.get("http://live.techpanda.org/index.php/mobile.html");

        // Nếu như chỉ có một tab thì tính năng tương tự quit
        // Nếu như có nhiều tab thì chỉ đóng tab đang active
        driver.close();

        // Đóng browser (không care bao nhiêu tab)
        driver.quit();

        // 2 hàm này bị ảnh hưởng timeout của implicitWait
        // findElement và finElements


        // Nó sẽ đi tìm với loại By nào trả về 1 element nếu như được tìm thấy (WebElement)
        // Không được tìm thấy: Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (Thao tác với cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        // Nó sẽ đi tìm với loại By nào trả về danh sách các element nếu như được tìm thấy (List WebElement)
        List<WebElement> checboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checboxes.get(1).click();

        driver.findElement(By.cssSelector("button#login")).click();

        tring loginURL = driver.getCurrentUrl();
        driver.getPageSource();
        driver.getTitle();
        driver.getWindowHandle();
        driver.getWindowHandles();

        // Nếu chỉ dùng một lần thì không cần khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        // Nếu có thể dùng lại nhiều lần,
        Assert.assertEquals(loginURL, "https://www.facebook.com/");


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
