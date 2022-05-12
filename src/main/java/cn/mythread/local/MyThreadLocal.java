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
        LOCAL.set("main");
        // 新建一个线程，分别向里面存线程ID，看主线程能否取出
        Thread t1 = new Thread(() -> {

            // 输出：local
            LOCAL.set("local");
            System.out.println(Thread.currentThread().getName() + "获取：" + LOCAL.get());

            // 输出：null
            LOCAL.remove();
            System.out.println(Thread.currentThread().getName() + "移除后获取：" + LOCAL.get());
        }, "local");
        t1.start();
        t1.join();

        // 输出：main
        System.out.println(Thread.currentThread().getName() + "获取：" + LOCAL.get());

        // 输出：null
        LOCAL.remove();
        System.out.println(Thread.currentThread().getName() + "移除后获取：" + LOCAL.get());
    }
}
