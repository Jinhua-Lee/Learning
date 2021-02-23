/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/MacBook.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.product;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.factory.cpu.AppleCpuFactory;
import cn.designpattern.factory.abs.factory.graphics.AmdGraphicsCardFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * 产品实现 -> Apple MacBook
 *
 * @author Jinhua
 * @date 2021/2/23 0:30
 */
public class MacBook implements PcProduct {

    /**
     * 产品采用的部件 -> CPU
     */
    private Cpu cpu;

    /**
     * 产品采用部件 -> 显卡
     */
    private GraphicsCard graphicsCard;

    public MacBook() {
        this.cpu = new AppleCpuFactory().createCpu();
        this.graphicsCard = new AmdGraphicsCardFactory().createGraphicsCard();
    }

    public MacBook(Cpu cpu, GraphicsCard graphicsCard) {
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
