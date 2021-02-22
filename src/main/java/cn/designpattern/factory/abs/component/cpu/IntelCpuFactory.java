/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/cpu/IntelCpuFactory.java
 * LastModified:    2021/2/22 下午11:10
 */

package cn.designpattern.factory.abs.component.cpu;

/**
 * 抽象CPU工厂的实现 -> Intel的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 23:01
 */
public class IntelCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return () -> "Intel CPU";
    }
}
