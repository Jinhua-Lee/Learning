/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/decorator/coder/SeniorJavaCoder.java
 * LastModified:    2021/2/19 下午10:20
 */

package cn.designpattern.decorator.coder;

/**
 * 资深Java程序员 -> 增强功能的实现
 *
 * @author Jinhua
 * @date 2021/2/19 22:20
 */
public class SeniorJavaCoder extends SeniorCoder {

    public SeniorJavaCoder(Coder coder) {
        super(coder);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("资深Java程序员，吃饭都在想代码");
    }

    @Override
    public void sleep() {
        super.sleep();
        System.out.println("资深Java程序员，睡觉都在想设计");
    }

    @Override
    public void code() {
        super.code();
        System.out.println("资深Java程序员，代码都有洁癖");
    }
}
