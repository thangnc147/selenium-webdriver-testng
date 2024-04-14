package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Thang", lastName = "Nguyen", email = getEmaillAddress();
    String companyName = "TSP", password = "Thang@569559";

    String  day = "14",
            month = "July",
            year = "1999";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");

    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        sleepInSeconds(3);

        // Input data for Register
        driver.findElement(By.cssSelector("input#gender-male")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        Select  dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay"))),
                monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth"))),
                yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
        dayDropdown.selectByVisibleText("14");
        Assert.assertFalse(dayDropdown.isMultiple());
        Assert.assertEquals(dayDropdown.getOptions().size(),32);
        monthDropdown.selectByVisibleText("July");
        Assert.assertEquals(monthDropdown.getOptions().size(),13);
        yearDropdown.selectByVisibleText("1999");
        Assert.assertEquals(yearDropdown.getOptions().size(),112);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/register");

        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        sleepInSeconds(3);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
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

    public String getEmaillAddress() {
        Random rand = new Random();
        return "thangnguyen" + rand.nextInt(99999) + "@gmail.net";
    }
    public String randomUsername() {
        Random rand = new Random();
        return "thangnguyen" + rand.nextInt(99999);
    }
}
