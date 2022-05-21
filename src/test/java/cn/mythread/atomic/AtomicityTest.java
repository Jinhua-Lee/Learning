package cn.mythread.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 【原子性】相关测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/21 11:19
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AtomicityTest {

    /*
     * 用volatile修饰的变量
     */
    private volatile int inc = 0;
    private volatile int incSync = 0;
    private volatile int incLock = 0;
    public AtomicInteger incAtomic = new AtomicInteger();

    private final Lock lock = new ReentrantLock();

    @Test
    @DisplayName("测试多种方法下volatile是否保证原子性")
    public void testAtomicMethods() {
        // 开十个线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    increase();
                    increaseSync();
                    increaseByLock();
                    increaseAtomic();
                }
            }).start();
        }

        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        // 主线程输出inc的值，多次运行可以发现inc的值有偏小的情况
        // 结论：volatile并不保证原子性
        Assertions.assertNotEquals(500, inc, "volatile方式，结果等于了500");

        Assertions.assertEquals(500, incSync, "sync方式，结果不为500");
        Assertions.assertEquals(500, incLock, "lock方式，结果不为500");
        Assertions.assertEquals(500, incAtomic.get(), "atomic类方式，结果不为500");
    }

    @SuppressWarnings("all")
    private void increase() {
        inc++;
    }

    private synchronized void increaseSync() {
        incSync++;
    }

    @SuppressWarnings("all")
    private void increaseByLock() {
        lock.lock();
        try {
            incLock++;
        } finally {
            lock.unlock();
        }
    }

    private void increaseAtomic() {
        incAtomic.getAndIncrement();
    }
}
