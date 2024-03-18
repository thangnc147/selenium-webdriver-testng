package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Run_More_Brower {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Run_On_Chrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://wwww.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://wwww.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_03_Run_On_Edge() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://wwww.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_04_Run_On_Safari() {
        if (osName.contains("Mac")) {
            driver = new SafariDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://wwww.facebook.com/");
            driver.quit();
        }
    }

}
