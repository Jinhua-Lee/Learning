package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 进程堆内存大小测试B<p>&emsp;
 * -Xms20m -Xmx20m
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/3/31 16:32
 */
public class HeapSizeB {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("start...");
        Thread.sleep(1000000L);
        System.out.println("end...");
    }
}
