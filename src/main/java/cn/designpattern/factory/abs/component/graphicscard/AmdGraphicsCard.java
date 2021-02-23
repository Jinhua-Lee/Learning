/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/component/graphicscard
 * /AmdGraphicsCard.java
 * LastModified:    2021/2/23 下午8:21
 */

package cn.designpattern.factory.abs.component.graphicscard;

/**
 * 抽象部件B的实现 -> AMD 显卡
 *
 * @author Jinhua
 * @date 2021/2/23 20:21
 */
public class AmdGraphicsCard implements GraphicsCard {
    @Override
    public String getName() {
        return "AMD Graphics Card";
    }
}
