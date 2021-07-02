package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 1. 多线程访问同一个对象的同步方法。<p>&emsp;
 * 线程安全，无法并行运行
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 14:59
 */
public class SyncMethod1 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod1 INSTANCE = new SyncMethod1();

    @Override
    public void run() {
        super.methodSync();
    }

    @SuppressWarnings("all")
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(INSTANCE);
        Thread thread2 = new Thread(INSTANCE);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("finished");
        // 预期结果：
        // 线程1运行 -> 线程1结束 -> 线程2运行 -> 线程2结束
    }
}
