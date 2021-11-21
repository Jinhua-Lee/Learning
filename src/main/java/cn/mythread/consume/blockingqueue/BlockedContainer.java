package cn.mythread.consume.blockingqueue;

import cn.mythread.consume.Container;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列实现的容器
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/11/21 16:00
 */
@Slf4j
public class BlockedContainer<T> implements Container<T> {

    public static final int DEFAULT_SIZE = 5;

    private final BlockingQueue<T> queue;

    public BlockedContainer() {
        this.queue = new ArrayBlockingQueue<>(DEFAULT_SIZE);
    }

    public BlockedContainer(int size) {
        this.queue = new ArrayBlockingQueue<>(size);
    }

    @Override
    public T take() {
        try {
            return queue.poll(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public void put(T t) {
        boolean offer = false;
        try {
            offer = this.queue.offer(t, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("failed to put element [{}], it was interrupted and case = {}", t, e);
        }

        if (offer) {
            log.info("succeed to put element [{}]", t);
        } else {
            log.warn("failed to put element [{}]", t);
        }
    }
}
