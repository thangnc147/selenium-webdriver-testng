package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Actions {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    Actions action;
    String projectPath = System.getProperty("user.dir");
//    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        action.moveToElement(ageTextbox).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

  /*  @Test
    public void TC_02_Hover_to_Element() {
        driver.get("http://www.myntra.com/");

        WebElement kidsButton = driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"));
        action.moveToElement(kidsButton).perform();

        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
//        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#breadcrumbs-crumb")).getText(),"Kids Home Bath");
    }*/

    @Test
    public void TC_03_Hover_to_Element() {
        driver.get("https://www.fahasa.com/");

//        WebElement kidsButton = driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"));

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
//        sleepInSeconds(2);
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']"))).perform();
//        sleepInSeconds(2);
        action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Phương Tiện Khác']"))).click().perform();
//        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Ô Tô']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Phương Tiện Khác']")).isDisplayed());

    }
    @Test
    public void TC_04_Click_and_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();

        List<WebElement> allSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allSelectedNumber.size(), 4);

    }
    @Test
    public void TC_05_Click_and_Select_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 20);

        action.keyDown(Keys.CONTROL).perform();
        action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(11)).perform();
        action.keyUp(Keys.CONTROL).perform();

        List<WebElement> allSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allSelectedNumber.size(), 4);

    }
    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

//        action.scrollToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        action.doubleClick(doubleClickButton).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("p#demo")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");


    }
    @Test
    public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement rightClickButton = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        action.contextClick(rightClickButton).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']")).isDisplayed());

        WebElement quitButton = driver.findElement(By.xpath("//span[text()='Quit']"));
        action.moveToElement(quitButton).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());

        action.click(quitButton).perform();
        sleepInSeconds(3);
        driver.switchTo().alert().accept();
        sleepInSeconds(3);
        

        Assert.assertFalse(quitButton.isDisplayed());

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
