package cn.mythread.lock.status;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

/**
 * 重量级锁，对象头信息
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/5 18:57
 */
public class HeavyWeightLock {

    private static Object object;

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Thread.sleep(5_000L);
        object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        new Thread(HeavyWeightLock::lock).start();
        // 此处主线程和new线程存在竞争锁的情况
        // 此处模拟，并发访问，自旋次数超过默认次数导致升级重量级锁
        lock();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    @SuppressWarnings("all")
    private static void lock() {
        synchronized (object) {
            // 打印调用线程的信息
            System.out.println(Thread.currentThread().getId() + "----" + Thread.currentThread().getName());
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
