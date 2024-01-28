package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class   Topic_02_Selenium_Locator {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ID() {
        // Tìm element có id là FirstName
        // System.out.println(driver.findElement(By.id("FirstName")));
        driver.findElement(By.id("FirstName")).sendKeys("Thang Nguyen");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagname(){
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkedText(){
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_LinkedText(){
        // Độ chính xác không cao. (Giống search kiểu link)
        driver.findElement(By.partialLinkText("vendor account"));
//        driver.findElement(By.partialLinkText("ount"));
//        driver.findElement(By.partialLinkText("vendor"));
    }

    @Test
    public void TC_07_Css(){
        // Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));
        // Css với Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));
        // Css với tagname
        driver.findElement(By.cssSelector("div"));
        // Css với Link
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));
        // Css với partial Link
        driver.findElement(By.cssSelector("a[href*='addresses']"));
//        driver.findElement(By.cssSelector("div[href^='addresses']"));
//        driver.findElement(By.cssSelector("div[href$='addresses']"));

        // Css có thể tìm handle cả 6 loại trên
    }

    @Test
    public void TC_08_XPath(){
        // XPath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        // XPath với Class
        driver.findElement(By.xpath("//div[@class='page-title']"));
        // XPath với tagname
        driver.findElement(By.xpath("//div"));
        // XPath với Link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
        // XPath với partial Link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));

        // XPath có thể tìm handle tất cả các loại trên
    }

    @Test
    public void TC_09_(){

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
