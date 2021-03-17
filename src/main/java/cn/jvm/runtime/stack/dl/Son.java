/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/dl/Child.java
 * LastModified:    2020/10/25 下午10:50
 */

package cn.jvm.runtime.stack.dl;

/**
 * 虚方法和非虚方法的测试
 *
 * @author Jinhua
 * @date 2020/10/25 22:03
 */
public class Son extends Father {

    public Son() {
        super();
    }

    public Son(int age) {
        this();
    }

    public static void showStatic(String str) {
        System.out.println("Son的static方法入参为： " + str);
    }

    private void showPrivate(String str) {
        System.out.println("Son的private方法入参为： " + str);
    }

    public void show() {
        // invokeStatic -> 静态方法
        showStatic("child static");

        // invokeStatic -> 静态方法
        super.showStatic("Call Super");

        // invokeSpecial -> 私有方法
        showPrivate("call private");

        // invokeSpecial -> 父类方法
        super.showCommon();

        /*
         * invokeVirtual -> 此方法声明有final，不能被重写，也认定为非虚方法，
         *                  未加super，所以虚拟机认定为虚方法
         */
        showFinal();

        // invokeSpecial -> 父类方法，认定为special
        super.showFinal();

        // invokeVirtual -> 同上，因为未显式加super，被虚拟机认定为虚方法
        showCommon();

        // invoke virtual -> 虚方法，需要实例对象
        info();

        MethodInterface method = null;

        // invokeInterface -> 针对接口的实现方法
        method.methodA();
    }

    public void info() {

    }
}

/**
 * 父类
 */
class Father {

    public Father() {
        System.out.println("Father的空参构造器...");
    }

    public static void showStatic(String str) {
        System.out.println("Father的static方法入参为： " + str);
    }

    public final void showFinal() {
        System.out.println("Father的final方法showFinal()...");
    }

    public void showCommon() {
        System.out.println("普通方法...");
    }
}

/**
 * 定义方法
 */
interface MethodInterface {
    /**
     * 接口方法测试
     */
    void methodA();
}
