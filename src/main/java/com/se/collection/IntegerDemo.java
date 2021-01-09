package com.se.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 运用静态代理模式
 * 测试在数据较多的情况下
 * 自动装箱和不自动装箱的时间消耗对比
 *
 * @author Jinhua
 */
public class IntegerDemo {
    public static void main(String[] args) {
        Obj obj = new Obj();
        Pro pro = new Pro(obj);
        // 返回Integer类型，不用装箱
        long s1 = pro.add(Integer.valueOf("1"));
        // 返回int类型，自动装箱
        long s2 = pro.add(Integer.parseInt("1"));
        // 装箱一次，不用从String转换
        long s3 = pro.add(1);

        System.out.println("valueOf:	" + s1);
        System.out.println("parseInt:	" + s2);
        System.out.println("int:	" + s3);
    }
}

/**
 * 被代理对象要执行的方法
 */
interface Demo {
    /**
     * 定义添加元素的函数
     * @param element 待添加元素
     * @return 添加的时间
     */
    long add(Integer element);
}

/**
 * 被代理对象
 */
class Obj implements Demo {

    /**
     * 待测试数组
     */
    private List<Integer> list;

    public Obj() {
        list = new ArrayList<>();
    }

    /**
     * 添加元素的方法
     * @param element 待添加的元素
     * @return 执行时间
     */
    @Override
    public long add(Integer element) {
        final int eleSize = 10000000;
        for (int i = 0; i < eleSize; i++) {
            list.add(element);
        }
        return 0L;
    }
}

/**
 * 代理对象
 */
class Pro implements Demo {

    /**
     * 被代理对象
     */
    private final Obj obj;

    public Pro(Obj obj) {
        this.obj = obj;
    }

    /**
     * 重写，以进行增强
     * @param element 待添加元素
     * @return 执行时间
     */
    @Override
    public long add(Integer element) {
        long t1 = System.currentTimeMillis();
        obj.add(element);
        long t2 = System.currentTimeMillis();
        return t2 - t1;
    }
}