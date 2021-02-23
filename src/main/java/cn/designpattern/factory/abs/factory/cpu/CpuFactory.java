/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/cpu/CpuFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.abs.component.cpu.Cpu;

/**
 * 抽象部件A的工厂 -> CPU工厂
 *
 * @author Jinhua
 * @date 2021/2/22 22:51
 */
public interface CpuFactory {

    /**
     * 生产CPU的方法
     *
     * @return CPU对象
     */
    Cpu createCpu();
}
