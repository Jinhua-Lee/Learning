package com.se.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 方法跟踪
 *
 * @author Jinhua
 */
@AllArgsConstructor
public class TraceHandler implements InvocationHandler {

    private final Object target;

    /**
     * 运用反射，代理对象的方法执行
     *
     * @param proxy 被代理的对象
     * @param m     被代理的方法
     * @param args  被代理的方法入参
     * @return 被代理方法的返回结果
     * @throws Throwable 代理过程发生异常
     */
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // 打印方法执行的方法体
        System.out.print(target + "." + m.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        return m.invoke(target, args);
    }

    public static void main(String[] args) {
        // 被代理对象数组
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            // 代理方法为 Object#compareTo 方法
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;
        // 二分查找需要用到比较方法
        int result = Arrays.binarySearch(elements, key);

        if (result > 0) {
            System.out.println(elements[result]);
        }
    }
}
