package cn.jvm.stringtable;

/**
 * 字符串常量池测试，不存放两个完全相同的实例<p>&emsp;
 * 通过断点调试，查看memory中的字符串常量个数
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 17:02
 */
public class StringPoolTest {
    public static void main(String[] args) {
        // 2980
        System.out.println("1");
        // 2981
        System.out.println("2");
        // 2982
        System.out.println("3");

        // 2983
        System.out.println("1");
        System.out.println("2");
        // 2983
        System.out.println("3");
    }
}
