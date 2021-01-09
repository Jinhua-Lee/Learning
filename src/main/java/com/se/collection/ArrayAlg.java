package com.se.collection;

/**
 * 数组最大最小对方法实现
 *
 * @author Jinhua
 */
public class ArrayAlg {
    /**
     * 可比较类型的数组中，找出最小和最大元素组成一对的方法
     *
     * @param a   可比较的数组
     * @param <T> 可比较的类型
     * @return 返回找到的最大和最小值组成一对的对象
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (T t : a) {
            if (min.compareTo(t) > 0) {
                min = t;
            } else if (max.compareTo(t) < 0) {
                max = t;
            }
        }
        return new Pair<>(min, max);
    }
}
