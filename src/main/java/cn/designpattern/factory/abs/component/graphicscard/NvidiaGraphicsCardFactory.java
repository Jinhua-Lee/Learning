/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/component/graphics
 * /NvidiaGraphicsCardFactory.java
 * LastModified:    2021/2/23 上午12:01
 */

package cn.designpattern.factory.abs.component.graphicscard;

/**
 * 抽象部件工厂的实现 -> 英伟达显卡工厂
 *
 * @author Jinhua
 * @date 2021/2/23 0:01
 */
public class NvidiaGraphicsCardFactory implements GraphicsCardFactory {
    @Override
    public GraphicsCard createGraphics() {
        return () -> "Nvidia GraphicsCard";
    }
}
