package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic_08_Parameter {

    static String fullNameFlobal = "Thang Nguyen";

    public static void main(String[] args) {
        setFullName("Tam Kim");
    }

    public static void setFullName(String fullName) {
        fullNameFlobal = fullName;
    }
}
