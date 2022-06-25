package cn.mythread.state;

import java.util.concurrent.TimeUnit;

/**
 * 【睡眠线程中断】
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 下午8:07
 */
public class SleepInterrupt {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        Thread th = new Thread(() -> {
            try {
                // sleep方法会出现异常
                System.out.println("1. 进入run方法.");
                TimeUnit.SECONDS.sleep(1L);
                System.out.println("2. 已经完成了休眠.");
            } catch (InterruptedException e) {
                System.out.println("3. 休眠被提前终止.");
                // 返回方法调用处
                return;
            }
            System.out.println("4. run方法正常结束.");
        }, "自定义线程");
        th.start();
        try {
            // 保证让th线程进入sleep状态.
            TimeUnit.MILLISECONDS.sleep(100L);
        } catch (InterruptedException ignored) {
        }
        // 主线程调用interrupt(), th线程的sleep方法会出现异常
        th.interrupt();
    }
}