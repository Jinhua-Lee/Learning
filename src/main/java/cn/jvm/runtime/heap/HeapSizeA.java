package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 进程堆内存大小测试A<p>&emsp;
 * -Xms10m -Xmx10m
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/3/31 16:32
 */
public class HeapSizeA {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("start...");
        Thread.sleep(1000000L);
        System.out.println("end...");
    }
}
