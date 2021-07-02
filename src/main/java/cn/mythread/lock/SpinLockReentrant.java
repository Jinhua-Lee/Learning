package cn.mythread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 可重入自旋锁的实现
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 10:58
 */
public class SpinLockReentrant {
    /**
     * 保存获取锁的线程
     */
    private final AtomicReference<Thread> owner = new AtomicReference<>();
    private int count;

    @SuppressWarnings("all")
    public void lock() {
        Thread current = Thread.currentThread();
        // 若当前线程已经获取到锁，则不用执行自旋等待
        if (current == owner.get()) {
            count++;
            return;
        }
        while (!owner.compareAndSet(null, current)) {
        }
    }

    public void unLock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count--;
                return;
            }
            // 锁的次数为0，则当前占有锁的线程释放
            owner.compareAndSet(current, null);
        }
    }
}
