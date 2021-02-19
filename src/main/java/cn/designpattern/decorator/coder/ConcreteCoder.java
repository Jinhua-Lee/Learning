/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/decorator/scratch/ConcreteCoder.java
 * LastModified:    2021/2/19 下午10:09
 */

package cn.designpattern.decorator.coder;

/**
 * 基本功能实现
 *
 * @author Jinhua
 * @date 2021/2/19 22:09
 */
public class ConcreteCoder implements Coder {

    @Override
    public void eat() {
        System.out.println("程序员吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("程序员睡觉");
    }

    @Override
    public void code() {
        System.out.println("程序员写代码");
    }
}
