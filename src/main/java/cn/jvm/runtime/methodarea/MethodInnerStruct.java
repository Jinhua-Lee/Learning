package cn.jvm.runtime.methodarea;

import java.io.Serializable;

/**
 * 方法区内部结构
 *
 * @author Jinhua
 * @date 2021/4/14 0:02
 */
public class MethodInnerStruct extends MethodAreaDemo implements Comparable<String>, Serializable {
    /**
     * 域（Field）
     */
    public int num = 10;
    private static final String STR = "测试方法区内部结构";

    // 构造器

    /**
     * 方法
     */
    public void test1() {
        int count = 20;
        System.out.println("count = " + count);
    }

    public static int test2(int div) {
        int result = 0;
        try {
            int value = 30;
            result = value / div;
        } catch (Exception ignored) {
        }
        return result;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
