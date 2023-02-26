package cn.mythread.state;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * 【卡丁车过桥】演示<p>&emsp;
 * interrupt()用于提前结束非活跃状态<p>
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/6/25 22:59
 */
@Slf4j
public class KartBridgeInterruptDemo {

    @SuppressWarnings(value = "all")
    public static void main(String[] args) throws InterruptedException {

        Thread kartTwo = new Thread(() -> {
            printTimeAndThread("准备过桥");
            printTimeAndThread("发现【卡丁1号】正在过桥，开始等待");
            try {
                // 睡1min用于等待kartOne做完某件事，但是可以由kartOne调用interrupt【提前结束】睡眠状态
                TimeUnit.MILLISECONDS.sleep(60_000L);
            } catch (InterruptedException ignored) {
                printTimeAndThread("开始过桥");
            }
            printTimeAndThread("过桥完成");
        }, "卡丁2号");

        Thread kartOne = new Thread(() -> {
            printTimeAndThread("准备过桥");
            long millisec = new SecureRandom().nextLong() + 1_000L;
            try {
                TimeUnit.MILLISECONDS.sleep(millisec);
            } catch (InterruptedException ignored) {
            }
            printTimeAndThread("过桥完成");
            // 叫醒kartTwo做事，不用一直睡
            kartTwo.interrupt();
        }, "卡丁1号");

        kartOne.start();
        kartTwo.start();
    }

    private static void printTimeAndThread(String msg) {
        log.info("thread = {}, message = {}", Thread.currentThread().getName(), msg);
    }
}
