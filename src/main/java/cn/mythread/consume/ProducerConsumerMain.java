package cn.mythread.consume;

import cn.mythread.consume.blockingqueue.BlockedContainer;
import cn.mythread.consume.lock.LockContainer;
import cn.mythread.consume.obj.ObjImplContainer;
import cn.mythread.consume.semaphore.SemaphoreContainer;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/11/21 16:30
 */
public class ProducerConsumerMain {

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Container<Integer> container = ContainerType.LOCK_CONDITION.container(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 两个生产者 + 三个消费者，每个Runnable负责持续生产或消费
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

    enum ContainerType {

        /**
         * 容器实现类枚举
         */
        OBJ_IMPL {
            @Override
            public <T> Container<T> container(int size) {
                return new ObjImplContainer<>(size);
            }
        },
        SEMAPHORE {
            @Override
            public <T> Container<T> container(int size) {
                return new SemaphoreContainer<>(size);
            }
        },
        BLOCKING_QUEUE {
            @Override
            public <T> Container<T> container(int size) {
                return new BlockedContainer<>(size);
            }
        },
        LOCK_CONDITION {
            @Override
            public <T> Container<T> container(int size) {
                return new LockContainer<>(size);
            }
        };

        public abstract <T> Container<T> container(int size);
    }
}
