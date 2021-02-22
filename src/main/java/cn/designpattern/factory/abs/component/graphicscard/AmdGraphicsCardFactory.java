/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/component/graphics
 * /AmdGraphicsCardFactory.java
 * LastModified:    2021/2/23 上午12:11
 */

package cn.designpattern.factory.abs.component.graphicscard;

/**
 * 抽象部件工厂的实现 -> AMD显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:11
 */
public class AmdGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphics() {
        return () -> "AMD GraphicsCard";
    }
}
