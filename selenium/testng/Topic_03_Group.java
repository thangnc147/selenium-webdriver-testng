package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Group {
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = "Regression")
    public void TC_01() {

    }

    @Test(groups = {"Regression", "Group"})
    public void TC_02() {

    }

    @Test(groups = {"Regression", "Product"})
    public void TC_03() {

    }

    @AfterClass
    public void afterClass() {

    }

}
