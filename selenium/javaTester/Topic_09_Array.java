package javaTester;

public class Topic_09_Array {

    int[] studentAge = {15, 18, 12};

    public static void main(String [] args) {
        String[] studentName = new String[5];
        studentName[0] = "Thang Nguyen";
        studentName[1] = "Kim Tam";
        studentName[2] = "Hieu Huynh";
        studentName[3] = "Ly Nguyen";
        studentName[4] = "Ralph";

        for (int i = 0; i < studentName.length; i++) {
            System.out.println(studentName[i]);
        }
    }
}
