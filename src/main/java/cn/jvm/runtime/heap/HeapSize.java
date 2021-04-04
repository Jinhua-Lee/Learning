package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 进程堆内存大小测试，调试时候允许多实例<p>&emsp;
 * 1) -Xms10m -Xmx10m
 * 2) -Xms20m -Xmx20m
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/3/31 16:32
 */
public class HeapSize {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("start...");
        Thread.sleep(1000000L);
        System.out.println("end...");
    }
}
