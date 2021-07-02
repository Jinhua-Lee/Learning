package cn.mythread.sync;

/**
 * 可重构入特性案例
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 9:46
 */
public class SynchronizedRecursion {
    int a = 0;
    int b = 0;

    private void method1() {
        System.out.println("method1正在执行，a = " + a);
        if (a == 0) {
            a++;
            method1();
        }
        System.out.println("method1执行结束，a = " + a);
    }

    private synchronized void method2() {
        System.out.println("method2正在执行，b = " + b);
        if (b == 0) {
            b++;
            method2();
        }
        System.out.println("method2执行结束，b = " + b);
    }

    public static void main(String[] args) {
        SynchronizedRecursion synchronizedRecursion = new SynchronizedRecursion();
        synchronizedRecursion.method1();
        synchronizedRecursion.method2();
    }
}
