/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/factory/graphics
 * /AmdGraphicsCardFactory.java
 * LastModified:    2021/2/23 下午8:11
 */

package cn.designpattern.factory.abs.factory.graphics;

import cn.designpattern.factory.product.graphicscard.AmdGraphicsCard;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;

/**
 * 抽象产品B的工厂的实现 -> AMD显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:11
 */
public class AmdGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphicsCard() {
        return new AmdGraphicsCard();
    }
}
