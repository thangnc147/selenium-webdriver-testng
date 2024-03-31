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

public class Topic_07_WebElement_Commands_02 {

    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        // Access web
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement mail = driver.findElement(By.cssSelector("input#mail"));
        WebElement under18 = driver.findElement(By.cssSelector("input#under_18"));
        WebElement education = driver.findElement(By.cssSelector("textarea#edu"));
        WebElement nameUnder5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        // Verify element displayed
        Assert.assertTrue(mail.isDisplayed());
        Assert.assertTrue(under18.isDisplayed());
        Assert.assertTrue(education.isDisplayed());
        // Verify element not displayed
        Assert.assertFalse(nameUnder5.isDisplayed());

        // Note
        if (mail.isDisplayed()) {
            mail.sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");
        } else {
            System.out.println("Email Textbox is not displayed");
        }

        if (under18.isDisplayed()) {
            under18.sendKeys("Automation Testing");
            System.out.println("Under 18 Radio is displayed");
        } else {
            System.out.println("Under 18 Radio is not displayed");
        }

        if (education.isDisplayed()) {
            education.click();
            System.out.println("Education TextArea is displayed");
        } else {
            System.out.println("Education TextArea is not displayed");
        }

        if (nameUnder5.isDisplayed()) {
            System.out.println("Name: User 5 is displayed");
        } else {
            System.out.println("Name: User 5 is not displayed");
        }
    }

    @Test
    public void TC_02_Enable() {
        // Access web
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement mail = driver.findElement(By.cssSelector("input#mail"));
        WebElement under18 = driver.findElement(By.cssSelector("input#under_18"));
        WebElement education = driver.findElement(By.cssSelector("textarea#edu"));
        WebElement nameUnder5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        WebElement jobRole1 = driver.findElement(By.cssSelector("select#job1"));
        WebElement interestsDevelopment = driver.findElement(By.cssSelector("input#development"));
        WebElement slider1 = driver.findElement(By.cssSelector("input#slider-1"));

        WebElement password = driver.findElement(By.cssSelector("input#disable_password"));
        WebElement ageRadioButton = driver.findElement(By.cssSelector("input#radio-disabled"));
        WebElement biography = driver.findElement(By.cssSelector("textarea#bio"));
        WebElement jobRole03 = driver.findElement(By.cssSelector("select#job3"));
        WebElement interestsCheckbox = driver.findElement(By.cssSelector("input#check-disbaled"));
        WebElement slider2 = driver.findElement(By.cssSelector("input#slider-2"));


        // Verify elements enable
        Assert.assertTrue(mail.isEnabled());
        Assert.assertTrue(under18.isEnabled());
        Assert.assertTrue(education.isEnabled());
        Assert.assertTrue(nameUnder5.isEnabled());
        Assert.assertTrue(jobRole1.isEnabled());
        Assert.assertTrue(interestsDevelopment.isEnabled());
        Assert.assertTrue(slider1.isEnabled());

        // Verify elements disable
        Assert.assertFalse(password.isEnabled());
        Assert.assertFalse(ageRadioButton.isEnabled());
        Assert.assertFalse(biography.isEnabled());
        Assert.assertFalse(jobRole03.isEnabled());
        Assert.assertFalse(interestsCheckbox.isEnabled());
        Assert.assertFalse(slider2.isEnabled());

        // Cosole log and verify result (3 example)
        if (mail.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is not disabled");
        }

        if (under18.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is not disabled");
        }

        if (password.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is disabled");
        }

    }

    @Test
    public void TC_03_Selected() {
        // Access web
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Click on Age (Under 18) button/ "Languages: Java" checkbox
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();
        sleepInSeconds(2);

        // Verify element is Selected
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());

        // Click "Languages: Java" checkbox to uncheck
        driver.findElement(By.cssSelector("input#java")).click();
        sleepInSeconds(2);

        // Verify "Languages: Java" checkbox has been unchecked
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());

    }

    @Test
    public void TC_04_MailChimp() {
        // Access web
        driver.get("https://login.mailchimp.com/signup/");

        // Input data into Email field
        driver.findElement(By.cssSelector("input#email")).sendKeys("thangnguyen.tsp@gmail.com");
//        Assert.assertEquals(driver.findElement(By.cssSelector("input#new_username")).getAttribute("value"), "thangnguyen.tsp@gmail.com");

        // validation for password field
        WebElement password = driver.findElement(By.cssSelector("input#new_password"));
        // Case 1 - Number
        password.clear();
        password.sendKeys("123");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Case 2 - Lowercase
        password.clear();
        password.sendKeys("abc");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Case 3 - Uppercase
        password.clear();
        password.sendKeys("ABC");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Case 4 - Special Char
        password.clear();
        password.sendKeys("!@#");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        // Case 5 - Max Lenghth
        password.clear();
        password.sendKeys("morethaneight");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());


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
