/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/WindowsWidgetFactory.java
 * LastModified:    2021/2/23 下午10:24
 */

package cn.designpattern.factory.abs.factory;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.cpu.IntelCpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.component.graphicscard.NvidiaGraphicsCard;
import cn.designpattern.factory.abs.factory.cpu.AppleCpuFactory;
import cn.designpattern.factory.abs.factory.cpu.CpuFactory;
import cn.designpattern.factory.abs.factory.graphics.AmdGraphicsCardFactory;
import cn.designpattern.factory.abs.factory.graphics.GraphicsCardFactory;

/**
 * 抽象工厂实现 -> Windows部件工厂
 *
 * @author Jinhua
 * @date 2021/2/23 22:24
 */
public class WindowsWidgetFactory implements WidgetFactory {

    /**
     * 依赖CPU工厂
     */
    private CpuFactory cpuFactory;

    /**
     * 依赖显卡工厂
     */
    private GraphicsCardFactory gcFactory;

    /**
     * 默认构造
     * 主要是指定选用的CPU和显卡的工厂
     */
    public WindowsWidgetFactory() {
        this.cpuFactory = new AppleCpuFactory();
        this.gcFactory = new AmdGraphicsCardFactory();
    }

    public WindowsWidgetFactory(CpuFactory cpuFactory, GraphicsCardFactory gcFactory) {
        this.cpuFactory = cpuFactory;
        this.gcFactory = gcFactory;
    }

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return new NvidiaGraphicsCard();
    }
}
