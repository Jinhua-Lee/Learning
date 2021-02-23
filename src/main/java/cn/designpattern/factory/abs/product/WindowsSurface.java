/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/WindowsSurface.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.product;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.factory.cpu.CpuFactory;
import cn.designpattern.factory.abs.factory.cpu.IntelCpuFactory;
import cn.designpattern.factory.abs.factory.graphics.GraphicsCardFactory;
import cn.designpattern.factory.abs.factory.graphics.NvidiaGraphicsCardFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * 产品实现 -> Windows Surface
 *
 * @author Jinhua
 * @date 2021/2/23 0:23
 */
public class WindowsSurface implements PcProduct {

    /**
     * 产品采用的部件 -> CPU
     */
    private Cpu cpu;

    /**
     * 产品采用部件 -> 显卡
     */
    private GraphicsCard graphicsCard;

    /**
     * 部件工厂 -> Cpu工厂
     */
    private CpuFactory cpuFactory;

    /**
     * 部件工厂 -> 显卡工厂
     */
    private GraphicsCardFactory gcFactory;

    /**
     * 默认构造，采用工厂构造
     * 本类将工厂作为属性
     */
    public WindowsSurface() {
        this.cpuFactory = new IntelCpuFactory();
        this.gcFactory = new NvidiaGraphicsCardFactory();

        this.cpu = this.cpuFactory.createCpu();
        this.graphicsCard = this.gcFactory.createGraphicsCard();
    }

    /**
     * 带参构造，指定参数的部件
     *
     * @param cpu          CPU
     * @param graphicsCard 显卡
     */
    public WindowsSurface(Cpu cpu, GraphicsCard graphicsCard) {
        this.cpu = cpu;
        this.graphicsCard = graphicsCard;
    }

    @Override
    public Cpu getCpu() {
        return this.cpu;
    }

    @Override
    public GraphicsCard getGraphicsCard() {
        return this.graphicsCard;
    }

    @Override
    @SneakyThrows
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
