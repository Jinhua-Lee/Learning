/*
 * Copyright (c)    2020/8/15 下午3:58.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/dynamicproxy/MyInvocationHandler.java
 * LastModified:    2019/10/8 下午11:23
 */

package cn.designpattern.proxy.dynamic;

import cn.designpattern.proxy.ProductService;
import cn.designpattern.proxy.ProductServiceImpl;

import java.lang.reflect.*;

/**
 * 动态代理类模拟
 *
 * @author Jinhua
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 被代理对象的引用
     */
    private final Object target;

    /**
     * 拿到被代理对象
     *
     * @param target 被代理对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 产生代理对象的方法
     *
     * @param proxy  代理对象
     * @param method 原有要增强的方法
     * @param args   方法的参数
     * @return 返回动态代理类对象
     * @throws Throwable 反射可能抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 添加了代理方法
        if ("addPro".equals(method.getName())) {
            System.out.println("开启事务……");
            Object result = method.invoke(target, args);
            System.out.println("提交事务……");
            return result;
        } else {    // 未添加代理方法
            return method.invoke(target, args);
        }
    }

    public static void main(String[] args) {
        // 普通对象产生
        ProductService ps = new ProductServiceImpl();
        InvocationHandler ih2 = new MyInvocationHandler(ps);
        // 产生代理对象
        ProductService proxy = (ProductService) Proxy.newProxyInstance(
                ps.getClass().getClassLoader(),
                ps.getClass().getInterfaces(),
                ih2
        );
        proxy.addPro();
        System.out.println("---------");
        proxy.delPro();
    }
}
