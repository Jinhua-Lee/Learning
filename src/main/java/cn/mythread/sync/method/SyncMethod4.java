package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 4. 同时访问同步方法与非同步方法，<p>&emsp;
 * 非同步方法不受影响，可以并行访问。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 16:05
 */
public class SyncMethod4 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod4 INSTANCE = new SyncMethod4();

    @Override
    public void run() {
        final String firstThread = "Thread-0";
        if (firstThread.equals(Thread.currentThread().getName())) {
            super.methodSync();
        } else {
            super.method();
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
        // 同步方法是串行访问的，普通方法可以并行执行
    }
}
