/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/graphics
 * /NvidiaGraphicsCardFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.abs.component.graphicscard.GraphicsCard;
import cn.designpattern.factory.abs.component.graphicscard.NvidiaGraphicsCard;

/**
 * 抽象部件B的工厂的实现 -> 英伟达显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:01
 */
public class NvidiaGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphicsCard() {
        return new NvidiaGraphicsCard();
    }
}
