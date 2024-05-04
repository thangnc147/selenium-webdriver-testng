
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Radio_Checkbox {

    WebDriver driver;
    /*String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");*/

    @BeforeClass
    public void beforeClass() {
        /*if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }*/

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/parent::li/span/input");

        checkToElement(dualZoneCheckbox);
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        uncheckToElement(dualZoneCheckbox);
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }

    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio  = By.xpath("//label[text()='2.0 Petrol, 147kW']/parent::li/span/input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/parent::li/span/input");

        checkToElement(twoPetrolRadio);

        // Verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type=checkbox]"));

        // Chọn hết tất cả checkbox
        for(WebElement checkbox: allCheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();}
        }
        // Verify tất cả checkbox
        for(WebElement checkbox: allCheckbox) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // Chỉ chọn một checkbox/ radio
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type=checkbox]"));

        for(WebElement checkbox: allCheckbox)  {
            if(checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for(WebElement checkbox: allCheckbox) {
            if(checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }

    }

    @Test
    public void TC_05_Custom_Checkbox() {
        driver.get("https://login.ubuntu.com/");

        By notHaveAccountRadio = By.cssSelector("input#id_new_user");
        checkByJavascriptExecutor(notHaveAccountRadio);
        Assert.assertTrue(driver.findElement(notHaveAccountRadio).isSelected());

        By acceptTosCheckbox = By.cssSelector("input#id_accept_tos");
        checkByJavascriptExecutor(acceptTosCheckbox);
        Assert.assertTrue(driver.findElement(acceptTosCheckbox).isSelected());

    }

    @Test
    public void TC_06_Custom_Checkbox_or_Radio() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By radioCanTho = By.xpath("//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div");
        Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"),"false");

        driver.findElement(radioCanTho).click();
        Assert.assertEquals(driver.findElement(radioCanTho).getAttribute("aria-checked"),"true");

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

    public void checkToElement(By byXpath) {
        if(!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    public void uncheckToElement(By byXpath) {
        if(driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    public void checkByJavascriptExecutor(By byCss) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",
                driver.findElement(byCss));
    }

}
