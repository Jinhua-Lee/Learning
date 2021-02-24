/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/cpu/IntelCpuFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.cpu.IntelCpu;

/**
 * 抽象产品A的工厂的实现 -> Intel的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 23:01
 */
public class IntelCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }
}
