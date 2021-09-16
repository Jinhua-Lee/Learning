package cn.mythread.consume.obj;

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
import java.util.concurrent.TimeUnit;

/**
 * 生产者、消费者的容器
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 22:32
 */
@Slf4j
public class ObjImplContainer<T> implements Container<T> {

    /**
     * 存放产品的容器
     */
    private final Queue<T> productQueue;

    /**
     * 先实现固定大小的
     */
    private final int fullSize;

    public ObjImplContainer(int fullSize) {
        if (fullSize <= 0) {
            throw new IllegalArgumentException("容器容量大小不合法");
        }
        this.productQueue = new LinkedList<>();
        this.fullSize = fullSize;
    }

    /**
     * 取元素的方法
     *
     * @return 取出的元素
     */
    @Override
    @SneakyThrows
    public synchronized T take() {
        TimeUnit.SECONDS.sleep(3);
        T element;
        boolean flag = false;
        // 空的时候，也是阻塞状态
        while (this.productQueue.peek() == null) {
            flag = true;
            log.info("There is no element in container, waiting...");
            wait();
        }
        if (flag ) {
            log.info("consumer waked up!!");
        }
        element = this.productQueue.poll();
        log.info("element {} is taken from container.", element);
        // 唤醒所有阻塞的线程（）
        notifyAll();
        return element;
    }

    /**
     * 放置元素的方法
     *
     * @param element 待放置的元素
     */
    @Override
    @SneakyThrows
    public synchronized void put(T element) {
        // 便于调试，加入sleep，平时不能将sleep放入同步块
        TimeUnit.MILLISECONDS.sleep(3);
        boolean flag = false;
        // 满的时候，处于阻塞状态
        while (productQueue.size() == this.fullSize) {
            flag = true;
            log.info("container is full, waiting...");
            wait();
        }
        if (flag ) {
            log.info("producer waked up!!");
        }
        log.info("element {} is added to container. ", element);
        productQueue.offer(element);
        // 唤醒所有阻塞的线程（有元素了，可以继续消费了）
        notifyAll();
    }

    @SneakyThrows
    public static void main(String[] args) {
        Container<Integer> container = new ObjImplContainer<>(5);

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
