package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * 堆区比例测试<p>&emsp;
 * 1) Xms600M<p>&emsp;
 * 2) Xmx600M<p>&emsp;
 * 3) -XX:NewRatio=2, 设置新生代与老年代比例，默认是2, 代表【新生代 : 老年代】 = 1 : 2;<p>&emsp;
 * 4) -XX:SurvivorRatio=8， 设置伊甸园与幸存者比例，默认是8，如果未关闭自适应机制，则值为6，<p>&emsp;
 * 5) -XX:-UseAdaptiveSizePolicy，关闭自适应;<p>&emsp;
 * 6) -Xmn100M，显式设置新生代的最大内存，若比例设置和该值冲突，则使用该值的设置。
 *
 * @author Jinhua
 * @date 2021/4/3 13:49
 */
public class EdenSurvivorTest {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("do something");
        Thread.sleep(1000000L);
    }
}
