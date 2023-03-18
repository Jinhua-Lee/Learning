package cn.mythread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池泄露模拟
 *
 * @author Jinhua-Lee
 */
@Slf4j
public class ThreadPoolLeak {

    private static final Integer THREAD_NUM = 100;

    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < THREAD_NUM; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
        // 等待线程池中的任务完成
        boolean success = executor.awaitTermination(10, TimeUnit.SECONDS);
        log.info("Successfully? success={}", success);
    }
}