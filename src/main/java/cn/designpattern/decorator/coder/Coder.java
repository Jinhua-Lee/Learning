/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/decorator/scratch/Coder.java
 * LastModified:    2021/2/19 下午10:07
 */

package cn.designpattern.decorator.coder;

/**
 * 程序员所做的事 -> 基本功能定义
 *
 * @author Jinhua
 * @date 2021/2/19 22:07
 */
public interface Coder {

    /**
     * 吃
     */
    void eat();

    /**
     * 睡
     */
    void sleep();

    /**
     * 写代码
     */
    void code();

}
