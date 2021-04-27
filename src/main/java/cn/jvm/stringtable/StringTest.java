package cn.jvm.stringtable;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * 字符串测试，不可变性
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 11:41
 */
public class StringTest {

    /**
     * 字符串常量池，不会有两个完全相同的字符串对象
     */
    @Test
    @SuppressWarnings("all")
    public void test1() {
        String s1 = "abc";
        String s2 = "abc";
        // 比较地址 true
        System.out.println(s1 == s2);
        s1 = "hello";
        // false
        System.out.println(s1 == s2);

        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * 测试字符串拼接
     */
    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        // 新建一个 "abcdef" 对象，并且让s2指向它
        s2 += "def";
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace("a", "m");
        // s1本身不变
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * StringBuilder和String的拼接执行效率，for循环测试<p>&emsp;
     * 1) append()方式，自始至终只会创建一个StringBuilder对象；<p>&emsp;
     * 2) String拼接方式，创建多个StringBuilder对象，及多个String对象，占用内存对象。每次循环，进来的变量，下一次就不再使用。<p><p>
     * 改进空间：<p>
     * 1) 若基本确定长度上限，则建议使用指定容量构造器{@link StringBuilder#StringBuilder(int)}，以减小扩容
     */
    @Test
    public void test4() {
        StopWatch sw1 = new StopWatch();
        sw1.start();
        method1(100_000);
        sw1.stop();
        // 9500ms
        System.out.println("花费时间：" + sw1.getTotalTimeMillis());

        StopWatch sw2 = new StopWatch();
        sw2.start();
        method2(100_000);
        sw2.stop();
        // 2ms
        System.out.println("花费时间：" + sw2.getTotalTimeMillis());
    }

    private void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            // 创建10w个StringBuilder，及10w个String
            src += "a";
        }
    }

    private void method2(int highLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            sb.append("a");
        }
    }
}
