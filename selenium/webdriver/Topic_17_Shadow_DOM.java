package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
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

public class Topic_17_Shadow_DOM {
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
        driver.get("https://automationfc.github.io/shadow-dom/");

        driver.findElement(By.xpath("//a[text()='scroll.html']"));

        // Element cha chứa shadow host
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));
        // Lấy ra Element Shadow root
        SearchContext firstShadow =  shadowHostParent.getShadowRoot();

        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(), "some text");

        WebElement shadowHostChildren =  firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));

        SearchContext secondShadow =  shadowHostChildren.getShadowRoot();

        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");


    }


    @Test
    public void TC_02_Window() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        sleepInSeconds(3);

        // Element cha chứa shadow host
        WebElement shadowHostParent = driver.findElement(By.xpath("//book-app[@apptitle='BOOKS']"));
        // Lấy ra Element Shadow root
        SearchContext firstShadow =  shadowHostParent.getShadowRoot();
        sleepInSeconds(3);
        SearchContext secondShadow = firstShadow.findElement(By.cssSelector("book-input-decorator")).getShadowRoot();
        sleepInSeconds(3);

        firstShadow.findElement(By.cssSelector("input#input")).sendKeys("Harry Porter");
        secondShadow.findElement(By.cssSelector("div.icon")).click();
        sleepInSeconds(3);

        SearchContext searchShadow = firstShadow.findElement(By.cssSelector("book-explore")).getShadowRoot();

//        WebElement shadowSearchItem = searchShadow.findElement(By.cssSelector("ul>li:nth-of-type(1)>book-item"));
//        SearchContext searchItemShadow = shadowSearchItem.getShadowRoot();
//        sleepInSeconds(3);

//        Assert.assertEquals(searchItemShadow.findElement(By.cssSelector("h2.title")).getText(), "Edward Garrett");
        List<WebElement> links = searchShadow.findElements(By.cssSelector("ul>li>book-item"));
        for (int i = 1; i < links.size(); i++)
        {
            WebElement shadowSearchItem = searchShadow.findElement(By.cssSelector("ul>li:nth-of-type(" + i + ")>book-item"));
            SearchContext searchItemShadow = shadowSearchItem.getShadowRoot();
            System.out.println("Book " + i + " is: " + searchItemShadow.findElement(By.cssSelector("h2.title")).getText());
        }


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
