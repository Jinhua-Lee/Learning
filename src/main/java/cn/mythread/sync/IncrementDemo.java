package cn.mythread.sync;

import lombok.SneakyThrows;

/**
 * synchronized关键字使用案例
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 10:22
 */
public class IncrementDemo implements Runnable {

    private static final IncrementDemo INCREMENT_DEMO = new IncrementDemo();

    private static int a = 0;

    @Override
    @SuppressWarnings("all")
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            a++;
        }
    }

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Thread t1 = new Thread(INCREMENT_DEMO);
        Thread t2 = new Thread(INCREMENT_DEMO);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a = " + a);
    }
}
