/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/component/graphicscard
 * /NvidiaGraphicsCard.java
 * LastModified:    2021/2/23 下午8:24
 */

package cn.designpattern.factory.abs.component.graphicscard;

/**
 * 抽象部件B的实现 -> 英伟达显卡
 *
 * @author Jinhua
 * @date 2021/2/23 20:24
 */
public class NvidiaGraphicsCard implements GraphicsCard {
    @Override
    public String getName() {
        return "Nvidia Graphics Card";
    }
}
