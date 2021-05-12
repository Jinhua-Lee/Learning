package cn.jvm.gc;

/**
 * GC日志文件位置测试：<p>&emsp;
 * 在 JDK 7 和 JDK 8 中分别执行。<p>
 * 参数列表：<p>&emsp;
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/12 22:55
 */
public class GcLogAllocation {

    private static final int MB = 1024 * 1024;

    public static void main(String[] args) {
        testAllocation();
    }

    @SuppressWarnings("all")
    public static void testAllocation() {
        byte[] bytes1, bytes2, bytes3, bytes4;
        bytes1 = new byte[2 * MB];
        bytes2 = new byte[2 * MB];
        bytes3 = new byte[2 * MB];
        bytes4 = new byte[4 * MB];
    }
}
