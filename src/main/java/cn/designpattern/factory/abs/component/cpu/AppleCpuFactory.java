/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/cpu/AppleCpuFactory.java
 * LastModified:    2021/2/22 下午11:06
 */

package cn.designpattern.factory.abs.component.cpu;

/**
 * 抽象CPU工厂的实现 -> Apple的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 22:57
 */
public class AppleCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return () -> "Apple CPU";
    }
}
