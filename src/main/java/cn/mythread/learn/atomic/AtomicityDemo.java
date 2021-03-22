package cn.mythread.learn.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile原子性测试
 *
 * @author Jinhua
 * @date 2020/8/17 23:22
 */
public class AtomicityDemo {

    /**
     * 用volatile修饰的变量
     */
    private volatile int inc = 0;
    private volatile int inc1 = 0;
    private volatile int inc2 = 0;
    public AtomicInteger inc3 = new AtomicInteger();

    private int x;
    private int y;
    volatile boolean flag;

    private final Lock lock = new ReentrantLock();

    public void increase() {
        inc++;
    }

    /**
     * synchronized关键字实现原子性
     */
    public synchronized void increase1() {
        inc1++;
    }

    /**
     * ReentrantLock实现原子性
     */
    public void increase2() {
        lock.lock();
        try {
            inc2++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * AtomicInteger实现原子性
     */
    public void increase3() {
        inc3.getAndIncrement();
    }

    @Test
    public void testVolatileOrder() {
        // 语句1
        x = 2;
        // 语句2
        int y = 0;
        // 语句3
        flag = true;
        // 语句4
        x = 4;
        // 语句5
        y = -1;
    }

    public static void main(String[] args) {
        final AtomicityDemo test = new AtomicityDemo();
        // 开十个线程
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 50; j++) {
                        test.increase();
                        test.increase1();
                        test.increase2();
                        test.increase3();
                    }
                }
            }.start();
        }

        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        // 主线程输出inc的值
        System.out.println(test.inc);
        System.out.println(test.inc1);
        System.out.println(test.inc2);
        System.out.println(test.inc3);
    }

}
