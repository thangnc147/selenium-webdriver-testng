package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Topic_16_Windows_Tab {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Window() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String gitHubWindowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        // Switch qua tab Google
        switchToWindowByID(gitHubWindowID);

        Assert.assertEquals(driver.getTitle(), "Google");

        // Swwitch về lại Github
        String googleWindowID = driver.getWindowHandle();
        switchToWindowByID(googleWindowID);

        // Switch qua tab FACEBOOK
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Đăng nhập Facebook");

//        Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

        // Swwitch về lại Github
        switchToWindowByTitle("Selenium WebDriver");

        // Switch qua tab TIKI
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

//        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // Close all except Github
        closeAllWindowWithoutParent(gitHubWindowID);
        Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");

    }


    @Test
    public void TC_02_Window() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        // Click Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        // Click 'Add to Compare' Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']//ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The product Sony Xperia has been added to comparison list.");


        // Click 'Add to Compare' Samsung
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        // Click Compare
        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        String techpandaMobileWindowID = driver.getWindowHandle();
        switchToWindowByID(techpandaMobileWindowID);
        sleepInSeconds(2);

        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        // CLose tab va chuyen ve Mobile page
        closeAllWindowWithoutParent(techpandaMobileWindowID);

        Assert.assertEquals(driver.getTitle(), "Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        Assert.assertEquals(driver.switchTo().alert().getText(),"Are you sure you would like to remove all products from your comparison?");

    }

    @Test
    public void TC_03_Window() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();

        String mainPage = driver.getWindowHandle();
        switchToWindowByID(mainPage);

        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']//following-sibling::span[@role='alert']")).getText(),
                "This field is required");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']//following-sibling::span[@role='alert']")).getText(),
                "This field is required");

        closeAllWindowWithoutParent(mainPage);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("search");
        driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();

        Assert.assertEquals(driver.getTitle(), "SEARCH | Định nghĩa trong Từ điển tiếng Anh Cambridge");

    }

    @Test
    public void TC_04_Window() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");

        driver.findElement(By.xpath("//a[@data-action='login']")).click();

        String mainPage = driver.getWindowHandle();
        switchToWindowByID(mainPage);

        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),
                "DCE Login Portal");

        closeAllWindowWithoutParent(mainPage);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='sam-wait']//p[@class='sam-wait__message']")).getText(),
                "Authentication was not successful. Please try again.");

        sleepInSeconds(2);
        driver.findElement(By.xpath("//div[@id='sam-wait']//span[text()='Close']//parent::button")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//form[@id='search-form']//input[@id='crit-keyword']")).sendKeys("Data Sience");
        selectItemInDropdown("select#crit-srcdb", "select#crit-srcdb option", "Harvard Summer School 2025");
        selectItemInDropdown("select#crit-summer_school", "select#crit-summer_school option", "Harvard College");
        selectItemInDropdown("select#crit-session", "select#crit-session option", "Full Term");

        driver.findElement(By.xpath("//button[@id='search-button']")).click();

        driver.findElement(By.xpath("//h2[text()='Search Results']")).isDisplayed();

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

    private void switchToWindowByID(String windowID) {
        //Lấy ra hết tất cả ID (tab) của window hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        // DÙng vòng lặp để duyệt qua từng ID
        for (String id : allWindowIDs) {

            // Nếu ID khác tab hiện tại thì switch driver qua tab đó
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    private void switchToWindowByTitle(String expectedPageTitle) throws InterruptedException {
        // Lấy hết toàn bộ các ID của Window/Tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            // Mỗi lần duyệt sẽ cho switch vào trước
            driver.switchTo().window(id);
            Thread.sleep(2000);

            // Get ra title của tab hiện tại
            String pageTitlle = driver.getTitle();

            // Kiểm tra title
            if (pageTitlle.equals(expectedPageTitle)) {
                break;
            }
        }
    }

    private void closeAllWindowWithoutParent(String expectedWindow) throws InterruptedException {
        // Lấy hết toàn bộ các ID của Window/Tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {

            // Nếu ID khác tab hiện tại thì switch driver qua tab đó
            if (!id.equals(expectedWindow)) {
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }
        // Switch vào window duy nhất con lại
        driver.switchTo().window(expectedWindow);

//        if(driver.getWindowHandles().size() == 1) {
//            return true;
//        } else {
//            return false;
//        }
    }

    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        sleepInSeconds(1);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
        sleepInSeconds(1);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
}
