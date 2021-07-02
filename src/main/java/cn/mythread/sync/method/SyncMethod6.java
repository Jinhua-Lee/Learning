package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 6. 同时访问静态synchronized和非静态synchronized方法，<p>&emsp;
 * 非静态方法不受影响，并行执行
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 16:23
 */
public class SyncMethod6 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod6 INSTANCE = new SyncMethod6();

    @Override
    public void run() {
        final String firstThread = "Thread-0";
        if (firstThread.equals(Thread.currentThread().getName())) {
            methodSyncStatic();
        } else {
            super.methodSync();
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
        // 非静态方法可以并行执行
        // (线程1静态开始 / 线程2同步开始) -> (线程2同步结束 / 线程1静态结束)
    }
}
