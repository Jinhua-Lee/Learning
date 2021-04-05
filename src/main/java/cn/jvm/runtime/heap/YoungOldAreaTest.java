package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 测试大对象直接分配到老年代<p>&emsp;
 * 1) -Xms60M -Xmx60M;<p>&emsp;
 * 2) -XX:NewRatio=2 -XX:SurvivorRatio=8;<p>&emsp;
 * 3) -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @date 2021/4/5 21:48
 */
public class YoungOldAreaTest {

    @SneakyThrows
    public static void main(String[] args) {
        // 20M
        byte[] buffer = new byte[1024 * 1024 * 20];
        Thread.sleep(200000L);
    }
}
