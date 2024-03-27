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

public class Topic_06_WebBrowser_Commands_01 {

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
        driver = new FirefoxDriver(); //**
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Driver() throws MalformedURLException {
        // Mở ra 1 page URL bất kì
        driver.get("https://www.facebook.com/"); // **

        // Nếu như chỉ có một tab thì tính năng tương tự quit
        // Nếu như có nhiều tab thì chỉ đóng tab đang active
        driver.close();  // **

        // Đóng browser (không care bao nhiêu tab)
        driver.quit(); // **

        // 2 hàm này bị ảnh hưởng timeout của implicitWait
        // findElement và finElements


        // Nó sẽ đi tìm với loại By nào trả về 1 element nếu như được tìm thấy (WebElement)
        // Không được tìm thấy: Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng chỉ lấy 1 (Thao tác với cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); // **

        // Nó sẽ đi tìm với loại By nào trả về danh sách các element nếu như được tìm thấy (List WebElement)
        List<WebElement> checboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); // **
        checboxes.get(1).click();

        driver.findElement(By.cssSelector("button#login")).click();

        // Lấy ra thong tin của URL hiện tại

        // Lấy ra URL
        String loginURL = driver.getCurrentUrl(); // *
        // Lấy ra page source (HTML/ Css/ Js)
        driver.getPageSource();  // 0

        // Lấy ra title của page hiện tại
        driver.getTitle(); // 0

        // Lấy ra ID của cửa sổ hiện tại (tab of browser)
        // handle Window/ tab
        driver.getWindowHandle(); // *
        driver.getWindowHandles(); // *

        // handel cookies
        driver.manage().getCookies(); // *
        // Get log từ devtool
        driver.manage().logs().get(LogType.BROWSER); // *
        // .timeout - Áp dụng cho việc tìm element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // **
        // pageLoadTimeout - Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // 0
        // scriptTimeout - Set trước khi dùng thư viện JavascripExecutor
        // Inject một đoạn code JS vào browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30)); // 0
            // Selenium 4 trở lên mới có các hàm sau
        driver.manage().timeouts().getImplicitWaitTimeout(); // 0
        driver.manage().timeouts().getPageLoadTimeout(); // 0
        driver.manage().timeouts().getScriptTimeout(); // 0

        // handle window
        // Chạy full màn hình
        driver.manage().window().fullscreen(); // 0
        driver.manage().window().maximize(); // **
        driver.manage().window().minimize(); // 0
        // Test giao diện GUI
        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1920, 1080)); // 0
        // Set cho browser ở vị trí nào so với độ phân giải màn hình
        driver.manage().window().setPosition(new Point(0, 0)); // 0

        // Điều hướng trang web
        driver.navigate().back(); // 0
        driver.navigate().forward(); // 0
        driver.navigate().refresh(); // 0
            // Thao tác với history của web page (back/ forward)
        driver.navigate().to("https://www.facebook.com/"); // 0
        driver.navigate().to(new URL("https://www.facebook.com/")); // 0

        // Alert/ Window (Tab)/ Frame (iFrame) // *
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // handle Window/ tab // *
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iFrame) // *
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("012354");
        driver.switchTo().frame(driver.findElement(By.id("email")));

        // Switch về HTML chứa frame trước đó // *
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó // *
        driver.switchTo().parentFrame();


        // Nếu chỉ dùng một lần thì không cần khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        // Nếu có thể dùng lại nhiều lần,
        Assert.assertEquals(loginURL, "https://www.facebook.com/");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
