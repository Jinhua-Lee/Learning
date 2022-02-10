package com.se.shutdownhook;

/**
 * shutDownHook的使用，在程序退出前做一些事。<p>
 * 以下情况适用：<p>&emsp;
 * 1. 程序正常退出。<p>&emsp;
 * 2. 使用System.exit()。<p>&emsp;
 * 3. 终端Ctrl + C。<p>&emsp;
 * 4. jvm所在操作系统关闭。<p>&emsp;
 * 5. OOM宕机。<p>&emsp;
 * 6. 使用kill [pid] 杀死进程<p>&emsp;
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/2/10 16:20
 */
public class ShutdownHookTest {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(
                // 就算异常退出也会执行
                new Thread(
                        () -> System.out.println("This is hook demo ...")
                ));

        int i = 0;
        // 这里报错
        int j = 10 / i;
        System.out.println("j = " + j);
    }
}
