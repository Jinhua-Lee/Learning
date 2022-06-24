package cn.mythread.state;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * 模拟【中断Interrupt】
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/24 上午10:32
 */
public class SimulateInterruption {

    @SneakyThrows
    public static void main(String[] args) {
        Thread mediator = new Thread(new Mediator());
        mediator.start();
    }

    private static class Mediator implements Runnable {

        private final SpineRun run = new SpineRun();

        @SneakyThrows
        @Override
        public void run() {
            Thread th = new Thread(run, "th线程");

            th.start();
            // 线程由os时间片分配调度
            TimeUnit.SECONDS.sleep(2L);

            // 1. 中断线程
            run.interrupted = true;
            System.out.println("setTrue");
            TimeUnit.SECONDS.sleep(2L);

            // 2. 清除interrupted状态
            run.interrupted = false;
            System.out.println("setFalse");

            synchronized (run) {
                run.notifyAll();
            }
        }
    }

    @Getter
    @Setter
    private static class SpineRun implements Runnable {

        private volatile boolean interrupted = false;

        @SneakyThrows
        @Override
        public synchronized void run() {
            // 空转
            while (!interrupted) {
                System.out.println("now = " + LocalTime.now());
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException ignored) {
                }
            }
            notify();
            wait();
            System.out.println("被唤醒了，继续跑");
        }
    }
}


