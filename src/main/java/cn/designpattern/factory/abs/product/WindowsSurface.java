/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/WindowsSurface.java
 * LastModified:    2021/2/23 上午12:23
 */

package cn.designpattern.factory.abs.product;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;

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

    @Override
    public Cpu getCpu() {
        return this.cpu;
    }

    @Override
    public GraphicsCard getGraphicsCard() {
        return null;
    }
}
