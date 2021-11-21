package cn.mythread.consume.semaphore;

import cn.mythread.consume.Container;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
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

    private final Semaphore fullCount = new Semaphore(0);
    private final Semaphore emptyCount;
    private final Semaphore isUse = new Semaphore(1);

    private final Queue<T> productQueue;

    public SemaphoreContainer(int capacity) {
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

}
