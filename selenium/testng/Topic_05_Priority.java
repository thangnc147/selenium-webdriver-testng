package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    @Test(groups = "Regression", priority = 3)
    public void shouldBeRegirsterFailWithInvalidEmail() {
        System.out.println("shouldBeRegirsterFailWithInvalidEmail");
    }

    @Test(groups = {"Regression", "Group"}, priority = 2)
    public void shouldBeLoginPass() {
        System.out.println("shouldBeLoginPass");

    }

    @Test(groups = {"Regression", "Product"}, priority = 1)
    public void shouldBeLoginFail() {
        System.out.println("shouldBeLoginFail");

    }
}
