package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_18_JS_Executor_I {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    WebDriverWait explicitWait;

    String email;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;

        email = "thang14.07.99+" + new Random().nextInt(99999) + "@gmail.com";
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Techpanda() throws InterruptedException {
        navigateToUrlByJS("http://live.techpanda.org/");
        Thread.sleep(3000);

        Assert.assertEquals(getDomain(), "live.techpanda.org");
        Assert.assertEquals(getURL(), "http://live.techpanda.org/");

        clickXpathElement("//a[text()='Mobile']");

        clickXpathElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        clickXpathElement("//a[text()='Customer Service']");

        scrollToXpathElementByJS("//input[@id='newsletter']");
        setAttributeToXpathElementByJS("//input[@id='newsletter']","value", email);

        clickXpathElement("//button[@title='Subscribe']");

        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("http://www.facebook.com/");

    }


    @Test
    public void TC_02_Rode() throws InterruptedException {
        driver.get("https://warranty.rode.com/login");

//        getElementValidationMessage("//button[@type='submit']");
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        // Empty and submit
        loginButton.click();
        sleepInSeconds(2);

        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");

        // Invalid Email and submit
            // Case 1
        String invalidEmail = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmail);
        loginButton.click();
        sleepInSeconds(2);

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if (driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmailMessage, "Please include an '@' in the email address. " + invalidEmailMessage + " is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

            // Case 2
        // Invalid Email and submit
        invalidEmail = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmail);
        loginButton.click();
        sleepInSeconds(2);

        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");
        if (driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmailMessage, "Please enter a part following '@'. " + invalidEmailMessage + " is incomplete.");
        } else {
            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
        }

            // Case 3
//        // Invalid Email and submit
//        invalidEmail = "aaa@aaa.";
//        driver.findElement(By.xpath("//input[@id='email']")).clear();
//        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmail);
//        loginButton.click();
//
//        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");
//        if (driver.toString().contains("ChromeDriver")) {
//            Assert.assertEquals(invalidEmailMessage, "'.' is used at a wrong position in " + invalidEmailMessage + ".");
//        } else {
//            Assert.assertEquals(invalidEmailMessage, "Please enter an email address.");
//        }

        // Valid Email and submit
        String validEmail = "aaa@aaa.aa";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
        loginButton.click();
        sleepInSeconds(2);

        String invalidPasswordMessage = getElementValidationMessage("//input[@id='password']");
        Assert.assertEquals(invalidPasswordMessage, "Please enter an password.");
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

    public WebElement getElement(String xPathLocator) {
        return driver.findElement(By.xpath(xPathLocator));
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public String getDomain() {
        return (String) jsExecutor.executeScript("return document.domain;");
    }

    public String getURL() {
        return (String) jsExecutor.executeScript("return document.URL;");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(2);
    }

    public void clickXpathElement(String xpathElement) {
        jsExecutor.executeScript("arguments[0].click();", getElement(xpathElement));
        sleepInSeconds(2);

    }

    public void scrollToXpathElementByJS(String xpathElement) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getElement(xpathElement));
    }

    public void setAttributeToXpathElementByJS(String xpathElement, String attributeName,String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "' )", getElement(xpathElement));
    }

    public String getElementValidationMessage(String xpathElement) {
        return (String) jsExecutor.executeScript("return argument[0].validationMessage;", getElement(xpathElement));

    }



}
