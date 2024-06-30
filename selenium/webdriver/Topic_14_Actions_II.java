package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class Topic_14_Actions_II {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    Actions action;
    String projectPath = System.getProperty("user.dir");
//    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;

        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_08_Drag_and_Drop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        action.dragAndDrop(smallCircle, bigCircle).perform();

        Assert.assertEquals(bigCircle.getText(), "You did great!");

    }
    @Test
    public void TC_09_Drag_and_Drop_HTML5_JQuery() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        sleepInSeconds(2);

        // If site with no JQuery
        String jqueryLibraries = getContentFile(projectPath + "\\dragAndDrop\\jQueryLib.js");
        jsExecutor.executeScript(jqueryLibraries);

        String jqueryDragDropContent = getContentFile(projectPath + "\\dragAndDrop\\dragAndDrop.js");

//        WebElement firstItem = driver.findElement(By.cssSelector("div#column-a>header"));
//        WebElement secondItem = driver.findElement(By.cssSelector("div#column-b>header"));

        jsExecutor.executeScript(jqueryDragDropContent);
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

    }

    @Test
    public void TC_09_Drag_and_Drop_HTML5_Java_Robot() throws InterruptedIOException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        sleepInSeconds(2);

        dragAndDropHTML5ByXpath("div#column-a","div#column-b");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        dragAndDropHTML5ByXpath("div#column-a","div#column-b");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");

    }

    @Test
    public void TC_10_Scroll_To_Element() {
        driver.get("http://live.techpanda.org/index.php/about-magento-demo-store/");

        action.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();

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

    public String getContentFile(String filePath) throws IOException {
        Charset cs = StandardCharsets.UTF_8;
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.cssSelector(sourceLocator));
        WebElement target = driver.findElement(By.cssSelector(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}
