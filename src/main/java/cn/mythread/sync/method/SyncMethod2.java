package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 2. 多个线程访问多个对象的同步方法。<p>&emsp;
 * 线程之间互不影响
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 15:55
 */
public class SyncMethod2 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod2 INSTANCE1 = new SyncMethod2();
    private static final SyncMethod2 INSTANCE2 = new SyncMethod2();

    @Override
    public void run() {
        super.methodSync();
    }

    @SuppressWarnings("all")
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(INSTANCE1);
        Thread thread2 = new Thread(INSTANCE2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("finished");
        // 预期结果：
        // a. 线程1运行 -> 线程2运行 -> 线程1结束 -> 线程2结束
        // b. 线程2运行 -> 线程1运行 -> 线程2结束 -> 线程1结束
    }
}
