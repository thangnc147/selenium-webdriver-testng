package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // Java Builtin (Java cung cấp sẵn - lấy ra sử dụng trực tiếp)
    // Java Libraries (Do 1 cá nhân/ tôt chức họ tự viết)

    public static void main(String[] args) {
        Random rand = new Random();

        System.out.println(rand.nextInt(10));
        System.out.println(rand.nextInt(10));
        System.out.println(rand.nextInt(10));
    }
}
