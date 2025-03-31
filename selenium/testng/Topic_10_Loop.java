package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class Topic_10_Loop {
    WebDriver driver;
    Random rand;
    Properties props = new Properties();
    FileOutputStream outputStrem;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        driver = new FirefoxDriver();
        rand = new Random();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        String path = projectPath + "\\dataTest\\user.properties";
        outputStrem = new FileOutputStream(path);
    }

    @Test(invocationCount = 3)
    public void Login_05_Register_New_Account() throws IOException {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        String firstName = "Thang",
                middleName = "Chien",
                lastName = "Nguyen",
                emailAddress = getEmaillAddress(),
                password = "Thang@569559";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
//        driver.findElement(By.cssSelector("input#middlename")).sendKeys(middleName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
//        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName +  "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Log Out
        driver.findElement(By.cssSelector("a.skip-account")).click();
//        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
//        sleepInSeconds(3);


        // Login
        driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
//        sleepInSeconds(2);

        driver.findElement(By.id("email")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@title='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName +  "!");
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
//        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);

        // Log Out
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        // Store
        props.setProperty("email", emailAddress);
        props.setProperty("password", password);
        props.store(outputStrem, null);

    }

    @AfterClass
    public void afterClass() throws IOException {
//        props.store(outputStrem, null);
        outputStrem.flush();
        driver.quit();
    }

    public String getEmaillAddress() {
        Random rand = new Random();
        return "thangnguyen" + rand.nextInt(99999) + "@gmail.net";
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String randomUsername() {
        Random rand = new Random();
        return "thangnguyen" + rand.nextInt(99999);
    }
}
