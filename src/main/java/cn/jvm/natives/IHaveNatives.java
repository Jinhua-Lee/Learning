/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/natives/IHaveNatives.java
 * LastModified:    2020/10/29 下午10:41
 */

package cn.jvm.natives;

/**
 * 本地方法定义的演示类。
 *      1. native修饰的方法都没有方法体。
 *      2. native和abstract关键字不能同用。
 *          a. native的方法实现体不是Java语言，但不表示没有。
 *          b. abstract方法是 虚方法 / 抽象方法。
 * @author Jinhua
 * @date 2020/10/29 22:41
 */
public class IHaveNatives {

    public native void Native1(int x);

    native static public long Native2();

    native synchronized private float Native3(Object o);

    native void Native4(int[] array) throws Exception;
}
