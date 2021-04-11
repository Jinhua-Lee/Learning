package cn.jvm.runtime.methodarea;

import lombok.SneakyThrows;

/**
 * 方法区测试<p>&emsp;
 * Xms参数和Xmx参数不包含方法区
 *
 * @author Jinhua
 * @date 2021/4/11 23:37
 */
public class MethodAreaDemo {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("start...");
        Thread.sleep(100_000L);
        System.out.println("end...");
    }
}
