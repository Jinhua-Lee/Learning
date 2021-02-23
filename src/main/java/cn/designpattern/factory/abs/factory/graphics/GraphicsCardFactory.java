/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/graphics
 * /GraphicsCardFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;

/**
 * 抽象部件B的工厂 -> 显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/22 23:52
 */
public interface GraphicsCardFactory {

    /**
     * 生产显卡的方法
     *
     * @return 显卡
     */
    GraphicsCard createGraphicsCard();
}
