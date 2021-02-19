/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/decorator/scratch/SeniorCoder.java
 * LastModified:    2021/2/19 下午10:14
 */

package cn.designpattern.decorator.coder;

/**
 * 资深程序员 -> 增强功能的定义
 *
 * @author Jinhua
 * @date 2021/2/19 22:14
 */
public abstract class SeniorCoder implements Coder {

    /**
     * 普通程序员
     */
    private final Coder coder;

    public SeniorCoder(Coder coder) {
        this.coder = coder;
    }

    @Override
    public void eat() {
        coder.eat();
    }

    @Override
    public void sleep() {
        coder.sleep();
    }

    @Override
    public void code() {
        coder.code();
    }

    public static void main(String[] args) {
        Coder coder = new ConcreteCoder();
        coder.eat();
        coder.sleep();
        coder.code();

        System.out.println("\n\n==========变强后==========");
        SeniorCoder seniorCoder = new SeniorJavaCoder(coder);
        seniorCoder.eat();
        seniorCoder.sleep();
        seniorCoder.code();
    }

}
