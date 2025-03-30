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
import java.util.regex.Pattern;

public class Topic_24_Explicit_Functions {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Dont_Set() {
        // Wait cho element ko hiển thị ko còn trong HTML (trước đó có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Wait cho element ko hiển thị (còn/ ko còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Wait cho elementy đưược hiển thị (Phải có trong HTML/ có trên UI)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Wait cho Element được phép click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe(""));

        // Wait cho URL của page tuơng đối
        explicitWait.until(ExpectedConditions.urlContains(""));

        // Wait cho URL của page thỏa mãn biểu thức (Regex)
        explicitWait.until(ExpectedConditions.urlMatches(""));

        // Wait cho một đoạn JS trả về kiểu dữ liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue(""));

        // Wait cho Alert có xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Wait cho title của page tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs(""));

        // Wait cho title của page tuơng đối
        explicitWait.until(ExpectedConditions.titleContains(""));

        // Wait thỏa mãn cả 2 điều kiện
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.urlContains(""), ExpectedConditions.titleIs("")));

        // Wait thỏa mãn 1 trong 2 điều kiện
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains(""), ExpectedConditions.titleIs("")));

        // Wait cho Element có xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Wait cho 1 Element có thuộc tính chứa 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "email"));

        // Wait cho 1 Element có thuộc tính bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));

        // Wait cho 1 Element có thuộc tính bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")), "class"));

        // Wait cho 1 Element có thuộc tính ở trong DOM bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "", ""));

        // Wait cho 1 Element có thuộc tính ở trong DOM bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "", ""));

        // Wait cho Emlement đã được chọn thành công (Checkbox/ Radio/ Dropdown)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Wait cho Emlement đã được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));

        // Wait cho Emlement chưa được chọn thành công (
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        // Wait cho frame/ iframe xuán hiện và switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        // Wait cho 1 đoạn lệnh JS được thực thi và không trả về bất kì Exception nào
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].valicationMessage"));

        // Phủ đinhj lại điều kiện
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true)));

        // Wait cho 1 list elêmnents bằng bao nhiêu item
        List<WebElement> allNumberSelected = explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 6));
        Assert.assertEquals(allNumberSelected.size(), 6);

        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 7));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 5));

        // Wait chp số lượng Window/ Tab bằng bbao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe( 5));

        // Wait cho 1 đoạn text bằng tuyệt đối
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("")));

        // Wait cho 1 element hay bị change/ refresh lại/ update lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated( By.cssSelector(""))));
    }


    @Test
    public void TC_02_Implicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hellow World!");
    }

    @Test
    public void TC_03_Explicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSeconds(6);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hellow World!");    }

    @Test
    public void TC_04_Greater_Than() {

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
