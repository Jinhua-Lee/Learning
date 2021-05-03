package cn.jvm.gc;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * 垃圾回收器设置使用<p>&emsp;
 * 1) 老年代和新生代组合Serial GC
 * <p>&emsp;&emsp;
 * -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 * <p>&emsp;
 * 2) 新生代使用ParNew GC
 * <p>&emsp;&emsp;
 * -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 * <p>&emsp;
 * 3) Parallel GC，新生代和老年代互相激活（设置新生代，老年代也会默认采用该Parallel收集器）
 * <p>&emsp;&emsp;
 * -XX:+UseParallelGC -XX:+UseParallelOldGC
 *
 * @author Jinhua
 * @date 2021/5/3 18:25
 */
public class GcUse {

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        List<byte[]> bytesList = new ArrayList<>();
        while (true) {
            bytesList.add(new byte[100]);
            Thread.sleep(10L);
        }
    }
}
