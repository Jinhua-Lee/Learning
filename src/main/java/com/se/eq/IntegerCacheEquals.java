package com.se.eq;

/**
 * Integer为例的包装类型缓存
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/12 1:00
 */
public class IntegerCacheEquals {

    public static void main(String[] args) {
        wrapperEquals();
    }

    /**
     * 测试包装类equals方法和相等判断
     */
    @SuppressWarnings("all")
    public static void wrapperEquals() {
        Integer i1 = 127;
        Integer i2 = 127;
        // true
        System.out.println("(i1 == i2) = " + (i1 == i2));
        // true
        System.out.println("i1.equals(i2) = " + i1.equals(i2));

        Integer i3 = 128;
        Integer i4 = 128;
        // false
        System.out.println("(i3 == i4) = " + (i3 == i4));
        // true
        System.out.println("i3.equals(i4) = " + i3.equals(i4));
    }
}
