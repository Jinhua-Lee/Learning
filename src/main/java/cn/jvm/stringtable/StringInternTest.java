package cn.jvm.stringtable;

import java.util.HashSet;
import java.util.Set;

/**
 * intern测试<p>&emsp;
 * 1) JDK 7及以前：<p>&emsp;&emsp;
 * -Xms6M -Xmx6M -XX:PermSize=6M -XX:MaxPermSize=6M <p>&emsp;
 * 2) JDK 8+：<p>&emsp;&emsp;
 * -Xms6M -Xmx6M -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M <p>&emsp;
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 16:24
 */
public class StringInternTest {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 使用Set保持着常量池被引用，防止full gc回收常量池行为
        Set<String> stringSet = new HashSet<>();
        // short的取值范围足以让6MB的PermSize或Heap产生OOM
        short i = 0;
        while (true) {
            stringSet.add(String.valueOf(++i).intern());
        }
    }
}
