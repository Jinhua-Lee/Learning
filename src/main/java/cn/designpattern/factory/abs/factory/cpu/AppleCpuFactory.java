/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/cpu/AppleCpuFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.abs.component.cpu.AppleCpu;
import cn.designpattern.factory.abs.component.cpu.Cpu;

/**
 * 抽象部件A的工厂的实现 -> Apple的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 22:57
 */
public class AppleCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return new AppleCpu();
    }
}
