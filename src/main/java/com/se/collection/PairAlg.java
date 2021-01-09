package com.se.collection;

/**
 * 一对对象的静态方法
 *
 * @author Jinhua
 */
public class PairAlg {

    /**
     * 一对 object 是否由空置
     *
     * @param p 一对 object
     * @return 是否有空值
     */
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    /**
     * 交换一对对象的对象中的两个值
     *
     * @param p   一对对象
     * @param <T> 对象类型
     */
    public static <T> void swap(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}
