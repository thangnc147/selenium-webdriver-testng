package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java - 2 Nhóm

    // I - Kiểu nguyên liệu nguyên thủy (Primitive Type)
    // 8 loại:

    // Số nguyên: byte - short - int - long
        // Không có phần thập phân: nhân viên 1 cty/ học sinh trong 1 lớp/....
    byte bNumber = 40;
    short sNumber = 5000;
    int iNumber = 123456789;
    long lNumber = 1234567891;

    // thực: float - double
        // Có phần thập phân: điểm số/ lương/ ...
    float fNumber = 9.99f;
    double dNumber = 35.61616d;

    // Kí tự: char
    char c1 = 'M';
    char c2 = '&';

    // Logic: boolean
    boolean aboolean = true;


    // II - Kiểu dữ liệu tham chiếu (Reference Type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.id("Login")));
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    // Interface
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    // Object
    Object name = "Automation FC";

    // Array
    int[] studentAge = {15, 20 , 25};
    String[] studentName = {"Leo", "Barry", "AuThur"};

    // Colleaction: List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<>();


    // String

    // Khai báo biến để lưu trữ dữ liệu:
    // Access Modifier: Phạm vi truy cập
    // Kiểu dữ liệu
    // Tên biến
    // Giá trị của biến
    public int studentNumber = 200;
}
