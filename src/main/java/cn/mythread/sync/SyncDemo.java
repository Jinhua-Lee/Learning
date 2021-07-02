package cn.mythread.sync;

import lombok.SneakyThrows;

/**
 * synchronized关键字使用场景
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 14:43
 */
public class SyncDemo implements Runnable {
    private static final SyncDemo INSTANCE1 = new SyncDemo();
    private static final SyncDemo INSTANCE2 = new SyncDemo();

    @Override
    public void run() {
        method1();
        method2();
        method3();
        method4();
    }

    public synchronized void method1() {
        common();
    }

    public static synchronized void method2() {
        commonStatic();
    }

    public void method3() {
        synchronized (this) {
            common();
        }
    }

    public void method4() {
        synchronized (SyncDemo.class) {
            common();
        }
    }

    public void method5() {
        common();
    }

    public void method6() {
        commonStatic();
    }

    @SneakyThrows
    public void common() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行");
        Thread.sleep(1_000);
        System.out.println("线程 " + Thread.currentThread().getName() + " 执行完毕");
    }

    @SneakyThrows
    public static void commonStatic() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行");
        Thread.sleep(1_000);
        System.out.println("线程 " + Thread.currentThread().getName() + " 执行完毕");
    }

    @SuppressWarnings("all")
    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Thread(INSTANCE1);
        Thread t2 = new Thread(INSTANCE2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("finished");
    }
}
