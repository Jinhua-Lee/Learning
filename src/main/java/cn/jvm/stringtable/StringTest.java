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

    @SuppressWarnings("all")
    private void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            // 创建10w个StringBuilder，及10w个String
            src += "a";
        }
    }

    @SuppressWarnings("all")
    private void method2(int highLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            sb.append("a");
        }
    }

    /**
     * 查看字节码<p>&emsp;
     * 1) new String("ab") 创建了几个对象？<p>&emsp;&emsp;
     * 2个：<p>&emsp;&emsp;
     * ①new关键字在堆空间创建的对象；<p>&emsp;&emsp;
     * ②字符串常量池中的对象，字节码指令ldc。<p>&emsp;
     * 2) new String("a") + new String("b")创建了几个对象？<p>&emsp;&emsp;
     * 6个：<p>&emsp;&emsp;
     * ① 【+】拼接，new StringBuilder(); <p>&emsp;&emsp;
     * ② new String("a"); <p>&emsp;&emsp;
     * ③ 常量池中的 “a”。 <p>&emsp;&emsp;
     * ② new String("b"); <p>&emsp;&emsp;
     * ③ 常量池中的 “b”。 <p><p>&emsp;&emsp;
     * 深入剖析：<p>&emsp;&emsp;
     * ⑥ StringBuilder#toString();
     */
    @Test
    public void test5() {
        String str1 = new String("ab");
        String str2 = new String("a") + new String("b");
    }

    /**
     * intern的使用，不同版本结果不同（JDK 7将字符串常量池移至堆空间）<p>&emsp;
     * 1) JDK 6及以前；
     * 2) JDK 7+：
     */
    @Test
    @SuppressWarnings("all")
    public void test7() {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        // JDK 6 -> false
        // JDK 7+ -> false
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        // JDK 6 -> false
        // jDK 7+ -> true
        System.out.println(s3 == s4);
    }
}
