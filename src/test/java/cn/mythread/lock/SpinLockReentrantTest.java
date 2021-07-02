package cn.mythread.lock;

import lombok.SneakyThrows;

/**
 * 自旋锁的可重入修改
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 10:59
 */
public class SpinLockReentrantTest implements Runnable {

    private static final SpinLockReentrantTest TEST = new SpinLockReentrantTest();
    private static int a = 0;

    private final SpinLockReentrant lock = new SpinLockReentrant();

    @Override
    @SuppressWarnings("all")
    public void run() {
        try {
            lock.lock();
            lock.lock();
            for (int i = 0; i < 10_000; i++) {
                // 自增操作的非原子性
                a++;
            }
        } finally {
            lock.unLock();
            lock.unLock();
        }

    }

    @SuppressWarnings("all")
    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Thread(TEST);
        Thread t2 = new Thread(TEST);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a = " + a);
    }
}