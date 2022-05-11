package cn.jvm.gc;

/**
 * 循环引用问题探究<p>&emsp;
 * -XX:+PrintGCDetails <p><p>
 * 操作：打开或关闭{@link System#gc()} 方法；<p>
 * 结果：GC信息，内存占用不同；<p>
 * 结论：Java未使用引用计数算法；<p>
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/29 21:04
 */
public class RefCountGc {
    /**
     * 唯一作用：占内存
     */
    private final byte[] bigSize = new byte[5 * 2024 * 1024];

    RefCountGc reference = null;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        RefCountGc r1 = new RefCountGc();
        RefCountGc r2 = new RefCountGc();

        // 互相引用
        r1.reference = r2;
        r2.reference = r1;
        // 释放外部引用
        r1 = null;
        r2 = null;
        // 打开或关闭gc方法，观察是否会执行GC
        System.gc();
    }
}
