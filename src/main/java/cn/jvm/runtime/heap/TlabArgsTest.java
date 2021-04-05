package cn.jvm.runtime.heap;

import lombok.SneakyThrows;

/**
 * TLAB参数测试<p>&emsp;
 * 1) 开关项（默认是开启的）：-XX:+UseTLAB;<p>&emsp;
 * 2) 占比（略）： -XX:TLABWasteTargetPercent
 *
 * @author Jinhua
 * @date 2021/4/6 0:09
 */
public class TlabArgsTest {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("测试TLAB开关和占比");
        Thread.sleep(20000000L);
    }
}
