package cn.mythread.lock.status;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁，对象头信息<p>
 * 个人理解：<p>&emsp;
 * 1. 实例化方式影响偏向线程ID相关对象头的设置，不会影响对象锁状态。<p>&emsp;&emsp;
 * a. 类加载机制实例化对象，不会设置偏向线程ID；<p>&emsp;&emsp;
 * b. 锁的状态更改，一定是由于访问synchronized代码块。<p>&emsp;
 * 2. 个人理解轻量级锁获取和保持的方式：<p>&emsp;&emsp;
 * a. 关闭偏向锁功能，访问synchronized块使得对象直接将无锁升级为轻量级锁；<p>&emsp;&emsp;
 * b. 串行访问synchronized块，每次判断对象的锁都是释放状态。<p>&emsp;&emsp;
 * c. 并发访问synchronized块，自旋次数小于默认次数，保持为轻量级锁状态。<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/5 16:36
 */
public class LightWeightLock {

    private static Object object;

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Thread.sleep(5_000L);
        object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        Thread thread = new Thread(LightWeightLock::lock);
        thread.start();
        // 让其执行完再接着执行主线程，防止上面的线程没有执行完成而形成线程争用而升级为重量级锁
        // 此处模拟情况2：串行访问synchronized块导致升级轻量级锁
        thread.join();
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
