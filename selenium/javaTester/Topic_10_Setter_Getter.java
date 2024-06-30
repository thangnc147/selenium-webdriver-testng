package javaTester;

import org.testng.annotations.Test;

public class Topic_10_Setter_Getter {

    private String fullName;
    @Test
    public void testGetterSetter() {
        setFullName("Thang Nguyen 01");
        System.out.println(getFullName());

        setFullName("Thang Nguyen 02");
        System.out.println(getFullName());

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
