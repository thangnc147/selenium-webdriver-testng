package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands_01 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        /*Chiếm 70-80% số lượng hàm thực tế
        getText
        getCssValue
        getAttribute
        isDisplayed
        isSelected
        isEnable
        click
        sendKeys
        clear
        */

      /*  //HTML Element: Textbox, TextArea, Dropdown, checkbox, field,...
        // tìm và trả về một Element
        driver.findElement(By.id(""));

        // Tìm và tương tác với Element
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys();

        // Tìm và lưu nó vào 1 biến WebElement
        // Khi có dùng biến này từ 2 lần trở lên
        WebElement fullNameTextbox = driver.findElement(By.id("")); */

        // Dùng để xóa dữ liệu trong 1 field cho phép nhập
        // Thường được dùng trươc khi sendKeys()
        driver.findElement(By.id("")).clear(); // *

        // Dùng để nhập dữ liệu trong 1 field cho phép nhập
        driver.findElement(By.id("")).sendKeys(""); // **

        // Dùng để click vào element
        driver.findElement(By.id("")).click(); // **

        // Dùng để tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname")); //có thể dùng Xpath/Css để làm nhanh hơn
        driver.findElement(By.cssSelector("form#form-validate input#firstname")); // 0

        // Trả về nhiều Element khớp với điều kiện
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));

        // Dùng để verify element đã được chọn (checkbõ/ radio button/ dropdown)
        // driver.findElement(By.id("")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());  // *

        // Dropdown (default/ custom)
        Select select = new Select(driver.findElement(By.id(""))); // 0

        // Dùng để verify element có hiển thị hay không
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());  // **
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed()); // **

        // Dùng để verify 1 Element có được thao tác lên hay không (không phải read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); // *
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled()); // *

        // HTML Element

        driver.findElement(By.id("")).getAttribute("title"); // *

        // Tab Acceibility/ Properties trong Element // *
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURL");
        driver.findElement(By.id("")).getDomProperty("outerHTML");
        // Tab Styles trong Elements (GUI)
        driver.findElement(By.id("")).getCssValue("font-size"); // *

        // Vị trí của Element so với độ phân giải màn hình // 0
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // Chiều cao + Chiều rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize(); // 0

        // Location + Size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect(); // 0
            // Location
        Point namePoint = nameTextboxRect.getPoint(); // 0
            // Size
        Dimension nameSize = nameTextboxRect.getDimension(); // 0
        nameTextboxRect.getHeight();
        nameTextboxRect.getWidth();

        // Shadow element (Học tại JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        // từ id/ class/ name/ css/ xpath có thể truy ngược lại HTML (lấy ra tag name)
        driver.findElement(By.cssSelector("#firstname")).getTagName(); // --> input

        driver.findElement(By.cssSelector("address.copyright")).getText(); // **

        // Chụp hình bị lỗi
        // BYTES
        // FILE (lưu thành 1 hình có kích thước vào ổ cứng )
        // BASE64 (hình dạng text)
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64); // *
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.FILE); // 0
        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BYTES); // 0

        // Form (element nào là thẻ form hoặc nằm trong thẻ form mới được)
        // Hành vi giống như phím Enter
        // Register/ Login/ Search
        driver.findElement(By.cssSelector("address.copyright")).submit();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
