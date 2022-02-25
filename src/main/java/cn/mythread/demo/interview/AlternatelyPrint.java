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
    private static void waitNotify() {
        Thread wOdd = new Thread(new WaitNotifyImpl(), "奇数线程");
        Thread wEven = new Thread(new WaitNotifyImpl(), "偶数线程");

        // 只能外部操作，来控制两个线程的顺序
        wOdd.start();
        TimeUnit.MILLISECONDS.sleep(3L);
        wEven.start();
    }

    @SneakyThrows
    private static void exchanger() {
        ExchangerImpl.NumberExchanger<Integer> numExchanger = new ExchangerImpl.NumberExchanger<>(1);
        Thread ex1 = new Thread(new ExchangerImpl(numExchanger), "ex1");
        Thread ex2 = new Thread(new ExchangerImpl(numExchanger), "ex2");
        ex1.start();
        TimeUnit.MILLISECONDS.sleep(3L);
        ex2.start();
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
    private static final Integer num = 100;

    @Override
    public void run() {
        while (count <= num) {
            synchronized (num) {
                System.out.println(Thread.currentThread().getName() + " print count = " + count++);
                num.notify();
                // 该条件，保证线程能够正常结束
                if (count <= num) {
                    try {
                        num.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }
}

/**
 * Exchanger是两个线程交换空间：
 * 执行到exchange方法时候，两个线程必须在此处完成数据交换，各自的线程才会进行下去。
 */
class ExchangerImpl implements Runnable {

    private final NumberExchanger<Integer> numExchanger;

    private Integer numLocal;

    public ExchangerImpl(NumberExchanger<Integer> numExchanger) {
        this.numExchanger = numExchanger;
    }

    @Override
    @SneakyThrows
    public void run() {
        Integer numLocal;
        // TODO: 2022/2/25 仍然有问题，还需调试
        // 1. 如果一个线程拿到的数据是null，则直接递出去
        // 2. 如果不是空值，则打印并自增，再递出去
        while ((numLocal = numExchanger.exchange(numExchanger.data++)) <= 100) {
            System.out.println(Thread.currentThread().getName() + " print count = " + numLocal);
        }
    }

    static class NumberExchanger<T> extends Exchanger<T> {
        T data;

        public NumberExchanger(T data) {
            this.data = data;
        }
    }
}
