/*
 * Copyright (c)    2020/8/15 下午3:57.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/proxy/ProductServiceImpl.java
 * LastModified:    2020/8/15 下午3:52
 */

package cn.designpattern.proxy;

/**
 * 产品集成相关方法的实现
 *
 * @author Jinhua
 */
public class ProductServiceImpl implements ProductService {
    /**
     * 定义添加商品的方法
     */
    @Override
    public void addPro() {
        System.out.println("添加商品");
    }

    /**
     * 定义删除商品的方法
     */
    @Override
    public void delPro() {
        System.out.println("删除商品");
    }
}
