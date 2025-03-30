package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Always_Run {
    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

    }

}
