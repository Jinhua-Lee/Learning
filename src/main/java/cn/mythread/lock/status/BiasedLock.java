package cn.mythread.lock.status;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁，对象头信息
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/5 16:12
 */
public class BiasedLock {
    private static Object object;

    @SneakyThrows
    public static void main(String[] args) {
        // 偏向锁会有一个延迟，程序刚启动的三四秒内不会出现偏向锁
        Thread.sleep(5_000L);
        object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        lock();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    @SuppressWarnings("all")
    private static void lock() {
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
