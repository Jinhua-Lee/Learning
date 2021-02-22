/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/component/graphics
 * /GraphicsCardFactory.java
 * LastModified:    2021/2/22 下午11:52
 */

package cn.designpattern.factory.abs.component.graphicscard;

/**
 * 抽象部件工厂 -> 显卡工厂
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
    GraphicsCard createGraphics();
}
