/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/se/inner/anonymous/Person.java
 * LastModified:    2021/2/18 下午9:55
 */

package com.se.inner.anonymous;

/**
 * 匿名内部类模拟
 * <p>
 * 方法是【抽象类或接口】的虚方法，给出临时实现来创建对象
 *
 * @author Jinhua
 * @date 2021/2/18 21:55
 */
public abstract class Person {

    /**
     * 吃的方法
     */
    public abstract void eat();

    public static void main(String[] args) {
        // 匿名内部类对象
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("人吃饭");
            }
        };
        p.eat();
    }
}
