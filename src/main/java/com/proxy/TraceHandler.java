package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + m.getName() + "(");
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
        ProxyTest.testProxy();
    }
}
