package cn.mythread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试Executor的位运算保存【状态 + 线程数】的方法
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/6/29 15:30
 * @see java.util.concurrent.ThreadPoolExecutor
 */
public class MyExecutorTest {
    // 【状态3bit + 线程数29bit】
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    // 29
    private static final int COUNT_BITS = Integer.SIZE - 3;
    // 00011111 ... ... 11111111，低位全为1
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // Integer类型32位，状态在高3位存储
    // 11100000 ... ... 00000000
    private static final int RUNNING = -1 << COUNT_BITS;
    // 00000000 ... ... 00000000
    private static final int SHUTDOWN = 0;
    // 00100000 ... ... 00000000
    private static final int STOP = 1 << COUNT_BITS;
    // 01000000 ... ... 00000000
    private static final int TIDYING = 2 << COUNT_BITS;
    // 01100000 ... ... 00000000
    private static final int TERMINATED = 3 << COUNT_BITS;

    // 运行状态解码，CAPACITY低位全为1，高位全为0，将其取反并做&操作，即是取高位
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    // 计算工作线程数解码，CAPACITY低位全为1，高位全为0，与其做&操作，即是取低位
    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    // 将状态和工作线程数组合为int型（编码）
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    // 是否运行状态
    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    @Test
    public void testCapacity() {
        // 输出29个1
        System.out.println("binary: " + Integer.toBinaryString(CAPACITY));
        // 线程池容量最大为：2^29-1
        System.out.println("decimal: " + CAPACITY);
    }

    @Test
    public void test() {
        System.out.println("binary runState: " + Integer.toBinaryString(runStateOf(RUNNING)));
        System.out.println("binary workCount: " + Integer.toBinaryString(workerCountOf(RUNNING)));

        // 编码解释为【终止状态 + 15个线程】
        int rw1 = 0b0110_0000_0000_0000_0000_0000_0000_1111;
        System.out.println("binary rw1 state: " + Integer.toBinaryString(runStateOf(rw1)));
        boolean terminated = runStateOf(rw1) == TERMINATED;
        System.out.println(terminated);
        System.out.println("binary rw1 count: " + Integer.toBinaryString(workerCountOf(rw1)));
        System.out.println("rw1 count: " + workerCountOf(rw1));
    }
}
