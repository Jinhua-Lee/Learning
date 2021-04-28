package cn.jvm.stringtable;

/**
 * String的垃圾回收测试<p>&emsp;
 * -Xms15M -Xmx15M -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/28 11:13
 */
public class StringGc {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 通过调整num值，查看对象个数
        int num = 100;
        for (int i = 0; i < num; i++) {
            String.valueOf(i).intern();
        }
    }
}
