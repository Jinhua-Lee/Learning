package cn.mythread.demo.interview;

import lombok.SneakyThrows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * 线程交替打印1~100的整数
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/2/24 23:26
 */
public class AlternatelyPrint {

    @SneakyThrows
    public static void main(String[] args) {
//        waitNotify();
        exchanger();
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private static void waitNotify() {
        Thread wOdd = new Thread(new WaitNotifyImpl(), "奇数线程");
        Thread wEven = new Thread(new WaitNotifyImpl(), "偶数线程");

        // 只能外部操作，来控制两个线程的顺序
        wOdd.start();
        TimeUnit.MILLISECONDS.sleep(3L);
        wEven.start();
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private static void exchanger() {
        Exchanger<Integer> numExchanger = new Exchanger<>();
        Thread odd = new Thread(new ExchangerImpl(numExchanger, 1), "odd");
        Thread even = new Thread(new ExchangerImpl(numExchanger, null), "even");
        odd.start();
        TimeUnit.MILLISECONDS.sleep(3L);
        even.start();
    }
}

/**
 * 对象通知模型，达到交替输出的效果
 */
class WaitNotifyImpl implements Runnable {

    private static Integer count = 1;
    /**
     * 1. 终止的条件
     * 2. 由于是不变的final对象，所以同时将它当作锁
     */
    private static final Integer NUM = 100;

    @Override
    public void run() {
        while (count <= NUM) {
            synchronized (NUM) {
                System.out.println(Thread.currentThread().getName() + " print count = " + count++);
                NUM.notify();
                // 该条件，保证线程能够正常结束
                if (count <= NUM) {
                    try {
                        NUM.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }
}

/**
 * Exchanger是两个线程交换空间，执行到exchange方法时候：<p>&emsp;
 * 交出数据的线程会被阻塞，直到另一个线程与它进行交换。
 */
class ExchangerImpl implements Runnable {

    private final Exchanger<Integer> numExchanger;

    /**
     * 当次待操作的值
     */
    private Integer numLocal;

    /**
     * 缓存上一次操作的数据，用于判断结束条件
     */
    private Integer lastReceived;

    private static final int NUM = 100;

    public ExchangerImpl(Exchanger<Integer> numExchanger, Integer numLocal) {
        this.numExchanger = numExchanger;
        this.numLocal = numLocal;
    }

    @Override
    @SneakyThrows
    public void run() {
        // 1. 如果一个线程本地的数据是null, 且小于，则直接递出去
        // 2. 如果本地的不是空值，则打印并自增，再递出去
        while (this.numLocal == null || this.numLocal <= NUM) {
            // 处理odd线程的退出
            boolean oddOver = this.lastReceived != null && this.lastReceived == NUM;
            if (oddOver) {
                break;
            }
            if (this.numLocal != null) {
                System.out.println(Thread.currentThread().getName() + " print count = " + numLocal++);
                // 处理even线程的退出
                if (this.numLocal == NUM + 1) {
                    break;
                }
            }
            // 最后保存时候，一定要先将上次的值做缓存
            this.lastReceived = this.numLocal;
            this.numLocal = numExchanger.exchange(this.numLocal);
        }
    }
}
