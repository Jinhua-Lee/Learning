package cn.mythread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不可重入的普通自旋锁实现
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 11:10
 */
public class SpinLock {

    /**
     * 保存获取到锁的线程
     */
    private final AtomicReference<Thread> owner = new AtomicReference<>();

    @SuppressWarnings("all")
    public void lock() {
        Thread current = Thread.currentThread();
        // 自旋等待
        while (!owner.compareAndSet(null, current)) {
        }
    }

    public void unLock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }
}
