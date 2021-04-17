package cn.jvm.runtime.methodarea;

import lombok.SneakyThrows;

/**
 * 静态引用（类变量）对应的【对象实体】始终都存放于堆空间<p>&emsp;
 * 1) JDK 7参数<p>&emsp;&emsp;
 * -Xms200m -Xmx200m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails<p>&emsp;
 * 2) JDK 8参数<p>&emsp;&emsp;
 * -Xms200m -Xmx200m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @date 2021/4/17 15:17
 */
public class StaticFieldsLocation {

    private static byte[] arr = new byte[1024 * 1024 * 100];

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(StaticFieldsLocation.arr);
        Thread.sleep(120_000);
    }
}
