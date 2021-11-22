package cn.mythread.consume.lock;

import cn.mythread.consume.Container;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock Condition实现生产者、消费者模式
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2021/11/22 22:49
 */
@Slf4j
public class LockContainer<T> implements Container<T> {

    // 锁及条件
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    // 容器队列及大小
    private final Queue<T> queue;
    private final int fullSize;

    {
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public LockContainer(int size) {
        this.queue = new LinkedList<>();
        this.fullSize = size;
    }

    @Override
    public T take() {
        lock.lock();
        try {
            try {
                while (queue.isEmpty()) {
                    log.info("the queue is empty!");
                    // 空：阻塞消费者线程
                    notEmpty.await();
                }
            } catch (InterruptedException ignored) {
            }
            T poll = queue.poll();
            log.info("[consume] take element {} from queue.", poll);
            // 取出后，则不空了，唤醒生产的线程
            notFull.signal();
            return poll;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(T t) {
        lock.lock();
        try {
            try {
                while (queue.size() == fullSize) {
                    log.info("the queue is full!");
                    // 满：阻塞生产线程
                    notFull.await();
                }
            } catch (InterruptedException ignored) {
            }
            queue.offer(t);
            log.info("[produce] put element {} into the queue.", t);
            // 可以放元素，则不空了，唤醒消费线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
