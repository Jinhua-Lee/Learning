package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 5. 访问同一个对象的不同的同步方法<p>&emsp;
 * 线程安全，无法并行运行
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 16:17
 */
public class SyncMethod5 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod5 INSTANCE = new SyncMethod5();

    @Override
    public void run() {
        final String firstThread = "Thread-0";
        if (firstThread.equals(Thread.currentThread().getName())) {
            super.methodSync();
        } else {
            super.methodSync2();
        }
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
        // 线程1开始同步方法1 -> 线程1结束同步方法1 -> 线程2开始同步方法2 -> 线程2结束同步方法2
    }
}
