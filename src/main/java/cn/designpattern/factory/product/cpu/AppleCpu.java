/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/designpattern/factory/abs/product/cpu/AppleCpu.java
 * LastModified:    2021/2/23 下午8:17
 */

package cn.designpattern.factory.product.cpu;

/**
 * 抽象产品A的实现 -> Apple CPU
 *
 * @author Jinhua
 * @date 2021/2/23 20:17
 */
public class AppleCpu implements Cpu {
    @Override
    public String getName() {
        return "Apple CPU";
    }
}
