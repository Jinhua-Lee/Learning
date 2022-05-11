package cn.jvm.gc.ref;

import lombok.SneakyThrows;

/**
 * 引用关系测试<p>
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @date 2021/5/2 14:49
 */
public class StrongRef {

    @SneakyThrows
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = sb1;
        sb1 = null;
        System.gc();
        // 延迟3s，保证gc操作能执行
        Thread.sleep(3_000L);
    }
}
