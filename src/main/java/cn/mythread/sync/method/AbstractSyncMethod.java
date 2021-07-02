package cn.mythread.sync.method;

import lombok.SneakyThrows;

/**
 * 方法定义抽象
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/2 15:03
 */
public abstract class AbstractSyncMethod {

    @SneakyThrows
    protected synchronized void methodSync() {
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法正在运行");
        Thread.sleep(3_000);
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法运行结束");
    }

    @SneakyThrows
    protected synchronized void methodSync2() {
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法2正在运行");
        Thread.sleep(3_000);
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法2运行结束");
    }

    @SneakyThrows
    @SuppressWarnings("all")
    protected synchronized void methodSyncException() {
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法2正在运行");
        Thread.sleep(3_000);
        // 异常抛出
        int i = 1 / 0;
        System.out.println("线程" + Thread.currentThread().getName() + "同步方法2运行结束");
    }

    @SneakyThrows
    protected void method() {
        System.out.println("线程" + Thread.currentThread().getName() + "普通方法正在运行");
        Thread.sleep(3_000);
        System.out.println("线程" + Thread.currentThread().getName() + "普通方法运行结束");
    }

    @SneakyThrows
    protected static synchronized void methodSyncStatic() {
        System.out.println("线程" + Thread.currentThread().getName() + "静态方法正在运行");
        Thread.sleep(3_000);
        System.out.println("线程" + Thread.currentThread().getName() + "静态方法运行结束");
    }
}
