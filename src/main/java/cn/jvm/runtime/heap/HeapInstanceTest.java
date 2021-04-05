package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * JVM内存分配的例子，通过Java VisualVM查看内存情况<p>&emsp;
 * 1) 设置内存：-Xms600M -Xmx600M;<p>&emsp;
 * 2) 观察Eden, S0, S1, Old区;
 *
 * @author Jinhua
 * @date 2021/4/5 14:00
 */
public class HeapInstanceTest {

    private final byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    @SneakyThrows
    public static void main(String[] args) {
        List<HeapInstanceTest> instances = new ArrayList<>();
        while (true) {
            instances.add(new HeapInstanceTest());
            Thread.sleep(10L);
        }
    }
}
