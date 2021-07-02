package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 3. 多个线程访问的synchronized的静态方法。<p>&emsp;
 * 线程安全，无法并行运行
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 16:01
 */
public class SyncMethod3 extends AbstractSyncMethod implements Runnable {

    private static final SyncMethod3 INSTANCE = new SyncMethod3();

    @Override
    public void run() {
        methodSyncStatic();
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
