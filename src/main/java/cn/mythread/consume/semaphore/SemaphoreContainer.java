package cn.mythread.consume.semaphore;

import cn.mythread.consume.Container;
import cn.mythread.consume.IntegerConsumer;
import cn.mythread.consume.IntegerProducer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * 以信号量方式实现容器
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/14 15:18
 */
@Slf4j
public class SemaphoreContainer<T> implements Container<T> {
    private final int capacity;
    private final Semaphore fullCount = new Semaphore(0);
    private final Semaphore emptyCount;
    private final Semaphore isUse = new Semaphore(1);

    private final Queue<T> productQueue;

    public SemaphoreContainer(int capacity) {
        this.capacity = capacity;
        this.emptyCount = new Semaphore(capacity);
        this.productQueue = new LinkedList<>();
    }

    @Override
    public void put(T t) {
        try {
            this.emptyCount.acquire();
            this.isUse.acquire();
            this.productQueue.offer(t);
            log.info("element {} is added to container. ", t);
        } catch (InterruptedException ignored) {
        }finally {
            this.isUse.release();
            this.fullCount.release();
        }
    }

    @Override
    public T take() {
        try {
            this.fullCount.acquire();
            this.isUse.acquire();
            T t = this.productQueue.poll();
            log.info("element {} is taken from container. ", t);
            return t;
        } catch (InterruptedException ignored) {
            return null;
        }finally {
            this.isUse.release();
            this.emptyCount.release();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        SemaphoreContainer<Integer> container = new SemaphoreContainer<>(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 两个生产者 + 三个消费者
        Future<?> p1 = executor.submit(new IntegerProducer(container));
        Future<?> p2 = executor.submit(new IntegerProducer(container));
        Future<?> c1 = executor.submit(new IntegerConsumer(container));
        Future<?> c2 = executor.submit(new IntegerConsumer(container));
        Future<?> c3 = executor.submit(new IntegerConsumer(container));

        p1.get();
        p2.get();
        c1.get();
        c2.get();
        c3.get();

        Thread.currentThread().join();
    }
}
