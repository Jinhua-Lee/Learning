package cn.jvm.stringtable;

import org.junit.Test;

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

    @Test
    public void test4() {
        System.out.println(Math.pow(2, 31) - 1);
        System.out.println(Long.MAX_VALUE);
    }
}
