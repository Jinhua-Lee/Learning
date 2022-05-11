package cn.mythread.demo.movieticket;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 电影售票窗口 Runnable 接口实现
 *
 * @author Jinhua
 */
public class MovieTicketRun implements Runnable {
    private static int number = 100;

    @SneakyThrows
    @Override
    public void run() {
        synchronized (this) {
            while (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 售票中，还剩： " + (--number) + " 张票");
                TimeUnit.MILLISECONDS.sleep(10L);
            }
        }
    }

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        MovieTicketRun mt = new MovieTicketRun();

        Thread t1 = new Thread(mt, "窗口1");
        Thread t2 = new Thread(mt, "窗口2");
        Thread t3 = new Thread(mt, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}
