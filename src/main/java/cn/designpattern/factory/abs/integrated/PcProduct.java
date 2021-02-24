/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/PcProduct.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.integrated;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;

/**
 * 产品集成抽象 -> 个人电脑
 *
 * @author Jinhua
 * @date 2021/2/23 0:15
 */
public interface PcProduct {

    /**
     * 获取CPU
     *
     * @return 获取CPU
     */
    Cpu getCpu();

    /**
     * 获取显卡
     *
     * @return 获取显卡
     */
    GraphicsCard getGraphicsCard();
}
