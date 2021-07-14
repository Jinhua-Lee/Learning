package cn.mythread.local;

import lombok.SneakyThrows;

/**
 * 线程本地变量测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/14 20:56
 */
public class MyThreadLocal {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 主线程设置，新建线程设置并移除，观察是否受影响
        LOCAL.set(Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {
            LOCAL.set(Thread.currentThread().getName());
            // 输出：线程1
            System.out.println(Thread.currentThread().getName() + "获取：" + LOCAL.get());
            LOCAL.remove();
            // null
            System.out.println(Thread.currentThread().getName() + "移除后获取：" + LOCAL.get());
        }, "线程1");
        t1.start();
        // t1线程结束后开始执行获取和移除
        t1.join();

        // 输出：main
        System.out.println(Thread.currentThread().getName() + "获取：" + LOCAL.get());
        LOCAL.remove();
        // null
        System.out.println(Thread.currentThread().getName() + "移除后获取：" + LOCAL.get());
    }
}
