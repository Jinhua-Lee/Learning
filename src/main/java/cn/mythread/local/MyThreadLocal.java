package cn.mythread.local;

import lombok.SneakyThrows;

/**
 * 线程本地变量的使用。每个线程的修改不影响其他线程
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/14 20:08
 */
public class MyThreadLocal {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        LOCAL.set("主线程");
        // 新建一个线程，分别向里面存线程ID，看主线程能否取出
        Thread t1 = new Thread(() -> {
            LOCAL.set("线程1");
            // 输出：线程1
            System.out.println("线程1获取：" + LOCAL.get());
            LOCAL.remove();
            // 输出：null
            System.out.println("线程1移除后获取：" + LOCAL.get());
        }, "线程1");
        t1.start();
        t1.join();
        // 输出：主线程
        System.out.println("主线程获取：" + LOCAL.get());
        LOCAL.remove();
        // 输出：null
        System.out.println("主线程移除后获取：" + LOCAL.get());
    }
}
