package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_Textarea {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.xpath("//input[@title='Password']")).clear();

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")),"This is a required field.");
    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.xpath("//input[@title='Password']")).clear();

        driver.findElement(By.id("email")).sendKeys("1324@132.1234");
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-validate-email-email")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")),"Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.xpath("//input[@title='Password']")).clear();

        driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("132");

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")),"Please enter 6 or more characters without leading or trailing spaces.");

    }
    @Test
    public void Login_04_Incorrect_Email_Or_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.xpath("//input[@title='Password']")).clear();

        driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("132123456");

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='error-msg']//span")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.xpath("//input[@title='Password']")).clear();

        driver.findElement(By.id("email")).sendKeys("automationfc@gmail.net");
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456789");

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='error-msg']//span")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

    }
    @Test
    public void Login_05_Register_New_Account() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String firstName = "Thang", middleName = "Chien", lastName = "Nguyen", emailAddress = getEmaillAddress(), password = "Thang@569559";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
//        driver.findElement(By.cssSelector("input#middlename")).sendKeys(middleName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName +  "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Log Out
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSeconds(3);


        // Login
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.id("email")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName +  "!");
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);
    }
    @Test
    public void Testcase_02_Exercise_Textbox_Textarea() {
        // Step 1
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        sleepInSeconds(3);

        // Step 2
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        sleepInSeconds(3);

        // Step 3
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        sleepInSeconds(3);

        // Step 4
        driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li")).click();
        sleepInSeconds(3);

        // Step 5
        String  firstName = "Thang", middleName = "Chien", lastName = "Nguyen";
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);

        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div/input")).getAttribute("_value");

        driver.findElement(By.xpath("//input[@type='checkbox']//following-sibling::span")).click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSeconds(3);

        String  username = randomUsername(), password = "Thang@569559";
        driver.findElement(By.xpath("//label[text()='Username']/parent::div//following-sibling::div/input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div//following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div//following-sibling::div/input")).sendKeys(password);
        sleepInSeconds(3);

        // Step 6
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();
        sleepInSeconds(3);

        // Step 7
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("_value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).getAttribute("_value"), middleName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("_value"), lastName);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div/input")).getAttribute("_value"), employeeID);
        sleepInSeconds(3);

        // Step 8
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        sleepInSeconds(3);

        // Step 9
        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
        sleepInSeconds(3);

        // Step 10
        String phoneNumber = "0333888555", comments = "ABCabc";
        driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div//following-sibling::div/textarea")).sendKeys(comments);

        driver.findElement(By.xpath("//button[text()=' Save ']")).click();
        sleepInSeconds(3);

        // Step 11
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();

        // Step 12
//        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).getAttribute("_value"), phoneNumber);
//        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div//following-sibling::div/textarea")).getAttribute("_value"), comments);

        // Step 13
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();

        // Step 14
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        sleepInSeconds(3);

        // Step 15
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

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
