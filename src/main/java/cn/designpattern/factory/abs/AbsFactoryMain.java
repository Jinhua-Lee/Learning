/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/AbsFactoryMain.java
 * LastModified:    2021/2/23 下午8:50
 */

package cn.designpattern.factory.abs;

import cn.designpattern.factory.abs.product.MacBook;
import cn.designpattern.factory.abs.product.PcProduct;
import cn.designpattern.factory.abs.product.WindowsSurface;

/**
 * 抽象工厂模式演示【主方法】
 * <p>
 * 其中product包下为部件的组装，产品类的空参构造方法中有抽象工厂的应用
 *
 * @author Jinhua
 * @date 2021/2/23 20:50
 */
public class AbsFactoryMain {

    public static void main(String[] args) {

        PcProduct windowsSurface = new WindowsSurface();
        System.out.println(windowsSurface);

        PcProduct macBook = new MacBook();
        System.out.println(macBook);
    }
}
