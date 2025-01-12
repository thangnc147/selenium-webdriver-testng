package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_II {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_05_Random_Popup_In_DOM() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");

        By randomAdPopup = By.cssSelector("div.popmake-content");

        // Hiển thị thì Close rồi action tiếp
        if(driver.findElements(randomAdPopup).size() > 0 && driver.findElements(randomAdPopup).get(0).isDisplayed()) {
            System.out.println("----------GO TO IF----------");
            driver.findElement(By.cssSelector("button.popmake-close")).click();
            Thread.sleep(2000);
        }

        // Không hiển thị thì action tiếp
        System.out.println("----------IGNORE IF----------");
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

    }

    @Test
    public void TC_06_Random_Popup_Not_In_DOM_01() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        By randomAdPopup = By.xpath("//div[@data-title='Newsletter-Books Anime Brief'"
                + " and not(contains(@style, 'display:none'))]");

        // Hiển thị thì Close rồi action tiếp
        if(driver.findElements(randomAdPopup).size() > 0 && driver.findElements(randomAdPopup).get(0).isDisplayed()) {
            System.out.println("----------GO TO IF----------");
            driver.findElement(By.xpath("//a[text()='×']")).click();
            Thread.sleep(2000);
        }

        // Không hiển thị thì action tiếp
        System.out.println("----------IGNORE IF----------");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header>h1.page-title")).isDisplayed());
    }

    @Test
    public void TC_07_Random_Popup_Not_In_DOM_02() throws InterruptedException {
        driver.get("https://dehieu.vn/");

        By randomAdPopup = By.cssSelector("div.modal-content");

        // Hiển thị thì Close rồi action tiếp
        if(driver.findElements(randomAdPopup).size() > 0 && driver.findElements(randomAdPopup).get(0).isDisplayed()) {
            System.out.println("----------GO TO IF----------");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(2000);
        }

        // Không hiển thị thì action tiếp
        System.out.println("----------IGNORE IF----------");
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập dự toán M&E");
        driver.findElement(By.cssSelector("button.header-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("Title"), "Khóa học Lập dự toán M&E");
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
