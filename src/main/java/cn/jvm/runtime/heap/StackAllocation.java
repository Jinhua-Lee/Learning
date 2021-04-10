package cn.jvm.runtime.heap;

import lombok.SneakyThrows;
import org.springframework.util.StopWatch;

/**
 * 栈上分配举例<p>&emsp;
 * 1) 内存配置项：-Xms1024M -Xmx1024M; <p>&emsp;
 * 2) 逃逸分析项开关，默认是开启的：-XX:+DoEscapeAnalysis; 通过此开关状态，通过Visual VM对比堆中对象的个数;
 *
 * @author Jinhua
 * @date 2021/4/10 22:23
 */
public class StackAllocation {

    @SneakyThrows
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int objNum = 10_000_000;
        for (int i = 0; i < objNum; i++) {
            alloc();
        }
        // 查看执行时间
        // a. 未开启逃逸分析：128ms
        // b. 开启逃逸分析：4ms
        stopWatch.stop();
        System.out.println("花费时间为： " + stopWatch.getTotalTimeMillis() + " ms");
        // 方便查看内存中对象个数，睡2min
        Thread.sleep(120_000);

    }

    private static void alloc() {
        // 未发生逃逸
        User user = new User();
    }

    static class User {
    }
}
