
package com.mythread.movieticket;

/**
 * 电影售票窗 继承 Thread 类实现
 *
 * @author Jinhua
 */
public class MovieTicketThread extends Thread {
    private static int number = 100;

    @Override
    public void run() {
        synchronized (this) {
            while (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 售票中，还剩： " + (--number) + " 张票");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MovieTicketThread mt1 = new MovieTicketThread();
        MovieTicketThread mt2 = new MovieTicketThread();
        MovieTicketThread mt3 = new MovieTicketThread();

        mt1.setName("窗口1");
        mt2.setName("窗口2");
        mt3.setName("窗口3");

        mt1.start();
        mt2.start();
        mt3.start();
    }
}
