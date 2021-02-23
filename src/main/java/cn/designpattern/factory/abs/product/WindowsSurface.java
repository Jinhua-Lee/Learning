/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/WindowsSurface.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.product;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.factory.WidgetFactory;
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
     * 默认构造，采用工厂构造
     * 本类将工厂作为属性
     */
    public WindowsSurface(WidgetFactory factory) {
        this.cpu = factory.createCpu();
        this.graphicsCard = factory.createGraphicsCard();
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
