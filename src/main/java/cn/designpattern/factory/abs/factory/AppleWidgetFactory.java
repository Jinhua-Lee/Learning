/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/AppleWidgetFactory.java
 * LastModified:    2021/2/23 下午10:25
 */

package cn.designpattern.factory.abs.factory;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.factory.cpu.AppleCpuFactory;
import cn.designpattern.factory.abs.factory.cpu.CpuFactory;
import cn.designpattern.factory.abs.factory.graphics.AmdGraphicsCardFactory;
import cn.designpattern.factory.abs.factory.graphics.GraphicsCardFactory;

/**
 * 抽象工厂实现 -> Apple产品工厂
 *
 * @author Jinhua
 * @date 2021/2/23 22:25
 */
public class AppleWidgetFactory implements WidgetFactory {

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
     * 主要是指定选用的CPU和显卡工厂
     */
    public AppleWidgetFactory() {
        this.cpuFactory = new AppleCpuFactory();
        this.gcFactory = new AmdGraphicsCardFactory();
    }

    public AppleWidgetFactory(CpuFactory cpuFactory, GraphicsCardFactory gcFactory) {
        this.cpuFactory = cpuFactory;
        this.gcFactory = gcFactory;
    }

    @Override
    public Cpu createCpu() {
        return this.cpuFactory.createCpu();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return this.gcFactory.createGraphicsCard();
    }
}
