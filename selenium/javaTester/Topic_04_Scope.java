package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ngoài hàm (phạm vi class)
    // được gọi là biến toàn cục (Global)
    // Có thể dùng cho các hàm nằm trong class này
    WebDriver driver;

    String homepageURL;
    String lastName = "thang";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() {
        // Biến được khai báo trong một hàm
        // Biến cục bộ
        // Chỉ có thể dùng trong phạm vi hàm này

        String homepageURL = "";
        String fullName = "Thang Nguyen";

        // Nếu có 2 biến trùng tên (global/ local) thì hàm sẽ ưu tiên lấy biến cục bộ
        // Biến được gọi mà chưa được khởi tạo sẽ hiện lỗi (chưa có giá trị) (khi complie code)
        driver.get(homepageURL);

        // Nếu muốn lấy giá trị từ biến global
        // Từ khóa this
        // Nếu biến global chưa được khởi tạo sẽ không hiện thị lỗi, nhưng sẽ bị lỗi khi chạy code (level runtime)
        driver.get(this.homepageURL);


    }

    @Test
    public void TC_02() {

    }

    @Test
    public void TC_03() {

    }

    @Test
    public void TC_04() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
