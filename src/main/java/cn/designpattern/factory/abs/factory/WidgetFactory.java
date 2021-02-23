/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/WidgetFactory.java
 * LastModified:    2021/2/23 下午10:20
 */

package cn.designpattern.factory.abs.factory;

import cn.designpattern.factory.abs.component.cpu.Cpu;
import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;

/**
 * 部件工厂 -> 生产CPU和显卡
 *
 * @author Jinhua
 * @date 2021/2/23 22:20
 */
public interface WidgetFactory {

    /**
     * 生产CPU
     *
     * @return CPU
     */
    Cpu createCpu();

    /**
     * 生产显卡
     *
     * @return 生产显卡
     */
    GraphicsCard createGraphicsCard();
}
