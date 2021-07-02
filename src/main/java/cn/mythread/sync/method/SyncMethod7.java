package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 7. 展示不抛出异常前和抛出异常后的对比：<p>&emsp;
 * 一旦抛出异常，第二个线程会立刻进入同步方法，意味着锁已经释放。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 16:32
 */
public class SyncMethod7 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod7 INSTANCE = new SyncMethod7();

    @Override
    public void run() {
        final String firstThread = "Thread-0";
        if (firstThread.equals(Thread.currentThread().getName())) {
            super.methodSyncException();
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
        // 线程1执行抛出异常，立即释放锁，只有线程2完整运行
    }
}
