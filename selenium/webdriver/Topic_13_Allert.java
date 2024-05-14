
package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_13_Allert {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");
    String username = "admin";
    String password = "admin";

    /*String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");*/

    @BeforeClass
    public void beforeClass() {
        /*if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }*/

        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS Confirm");

        driver.switchTo().alert().dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS prompt");

        String promptInputText = "Thang Test";
        driver.switchTo().alert().sendKeys(promptInputText);
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + promptInputText);


    }

    @Test
    public void TC_04_Authenticate_Alert() throws IOException {

        String  username = "admin",
                password = "admin";

        // Cách 1: truyền thẳng username/ password vào URL
//        driver.get("http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Thao tác khi access từ 1 trang trước đó
        driver.get("http://the-internet.herokuapp.com/");

        String authenLinkURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
            // http://the-internet.herokuapp.com/basic_auth

        driver.get(getAuthenAlertByURL(authenLinkURL, username, password));

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());


    }

    @Test
    public void TC_04_Authenticate_Alert_AutoIT() throws IOException {

        // Cách 2: Chạy trên Window (Auto IT)
        // Thực thi đoạn code AutoIT để chờ Alert xuất hiện
        Runtime.getRuntime().exec(new String[] { projectLocation + "\\autoIT\\authen_firefox.exe", username, password});

        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(5);

        Assert.assertTrue(driver.findElement(
                By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());


    }

    /*@Test
    public void TC_05_Authentication_Selenium_4x() {
        // Cách 3:
        // Thư viện Allert không sử dụng cho Authentication Allert được
        // Chrome Devtool Prôtcol (CDP) - Chrome/ Edge (Chronium)

        // Get devtool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s",username,password).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Headẻ
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("http://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(
                By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }*/


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

    public String getAuthenAlertByURL(String url, String username, String password) {
        String[] authenArray = url.split("//");
        return authenArray[0]+ "//" + username + ":" + password + "@" + authenArray[1];
    }

}
