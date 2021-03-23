package cn.jvm.runtime.stack;

/**
 * 演示栈的StackOverflow
 *
 * @author Jinhua
 * @date 2020/10/19 20:29
 */
public class StackErrorTest {

    private static int count = 1;

    public static void main(String[] args) {

        /*
         * 1. 默认情况，count = 5885 时候抛异常；
         * 2. 设置栈大小，Xss = 256k时，count = 1928 时候抛异常
         */
        System.out.println("count = " + count++);
        main(args);
    }
}
