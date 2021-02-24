/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/MacBook.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.integrated;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.factory.WidgetFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * 产品集成实现 -> Apple MacBook
 *
 * @author Jinhua
 * @date 2021/2/23 0:30
 */
public class MacBook implements PcProduct {

    /**
     * 产品集成采用的产品 -> CPU
     */
    private Cpu cpu;

    /**
     * 产品集成采用产品 -> 显卡
     */
    private GraphicsCard graphicsCard;

    public MacBook(WidgetFactory factory) {
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
