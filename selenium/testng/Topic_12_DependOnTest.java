package testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_DependOnTest {
    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01_Create_New_Product() {
        System.out.println("TC_01_Create_New_Product");
        Assert.fail();
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_02_View_Product() {
        System.out.println("TC_02_View_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_03_Update_Product() {
        System.out.println("TC_03_Update_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Move_Product() {
        System.out.println("TC_04_Move_Product");
    }

    @Test
    public void TC_05_Delete_Product() {
        System.out.println("TC_05_Delete_Product");
    }

    @AfterClass
    public void afterClass() {

    }

}
