/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/cpu/IntelCpu.java
 * LastModified:    2021/2/23 下午8:15
 */

package cn.designpattern.factory.abs.component.cpu;

/**
 * 抽象部件A的实现 -> Intel CPU
 *
 * @author Jinhua
 * @date 2021/2/23 20:15
 */
public class IntelCpu implements Cpu {
    @Override
    public String getName() {
        return "Intel CPU";
    }
}