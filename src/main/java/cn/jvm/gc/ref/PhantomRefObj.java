package cn.jvm.gc.ref;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用测试
 *
 * @author Jinhua
 * @date 2021/5/2 16:25
 */
public class PhantomRefObj {

    /**
     * 当前类对象
     */
    public static PhantomRefObj pRefObj;

    /**
     * 引用队列
     */
    static ReferenceQueue<PhantomRefObj> pRefQueue = null;

    /**
     * 检查队列的线程
     */
    public static class CheckQueue extends Thread {

        @Override
        @SuppressWarnings("all")
        public void run() {
            while (true) {
                if (pRefQueue != null) {
                    PhantomReference<PhantomRefObj> pObj = null;
                    try {
                        pObj = (PhantomReference<PhantomRefObj>) pRefQueue.remove();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    if (pObj != null) {
                        System.out.println("追踪垃圾回收过程成功！");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize方法被调用");
        pRefObj = this;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread check = new CheckQueue();
        // 设置守护线程，仅剩守护线程时候，守护线程也会结束
        check.setDaemon(true);
        check.start();

        pRefObj = new PhantomRefObj();
        pRefQueue = new ReferenceQueue<>();
        PhantomReference<PhantomRefObj> phantomRef = new PhantomReference<>(pRefObj, pRefQueue);

        // 不可获取虚引用中的对象
        System.out.println("phantomRef.get() = " + phantomRef.get());

        // 去除强引用
        pRefObj = null;
        // 第一次gc，对象可以复活，无法回收该对象
        System.gc();
        Thread.sleep(1_000L);
        String msg = pRefObj == null ? "pRefObj is null." : "pRefObj is available.";
        System.out.println(msg);

        // 去除强引用
        pRefObj = null;
        // 第二次gc，可以执行回收
        System.gc();
        Thread.sleep(1_000L);
        msg = pRefObj == null ? "pRefObj is null." : "pRefObj is available.";
        System.out.println(msg);
    }
}
