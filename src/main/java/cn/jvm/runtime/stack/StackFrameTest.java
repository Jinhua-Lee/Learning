package cn.jvm.runtime.stack;

/**
 * 栈帧StackFrame测试<p>&emsp;
 *      1. 栈的测试<p>&emsp;
 *      2. 方法返回方式的测试：<p>&emsp;&emsp;
 *          正常返回return和异常抛出结束，都会使得 Current Frame 出栈<p>&emsp;
 * @author Jinhua
 * @date 2020/10/19 21:02
 */
public class StackFrameTest {

    public static void main(String[] args) {
        try {
            method1();
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        }
        System.out.println("main() 正常结束！");
    }

    private static void method1() {
        System.out.println("method1() 执行开始...");
        method2();
        System.out.println(10 / 0);
        System.out.println("method1() 执行结束...");
    }

    private static int method2() {
        System.out.println("method2() 执行开始...");
        int i = 10;
        int m = (int) method3();
        System.out.println("method2() 即将结束...");
        return i + m;
    }

    private static double method3() {
        System.out.println("method3() 执行开始...");
        double j = 20d;
        System.out.println("j = " + j);
        System.out.println("method3() 即将结束...");
        return j;
    }
}
