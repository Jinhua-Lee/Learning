/*
 * Copyright (c)    2020/8/15 下午3:57.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/proxy/ProxyProductServiceImpl.java
 * LastModified:    2019/10/8 下午11:23
 */

package cn.designpattern.proxy;

/**
 * 静态代理类
 *
 * @author Jinhua
 */
public class ProxyProductServiceImpl implements ProductService {

    /**
     * 被代理对象的引用
     */
    private final ProductService ps;

    public ProxyProductServiceImpl(ProductService ps) {
        this.ps = ps;
    }

    @Override
    public void addPro() {
        System.out.println("添加之前……");
        ps.addPro();
        System.out.println("添加之后……");
    }

    @Override
    public void delPro() {
        System.out.println("删除之前……");
        ps.delPro();
        System.out.println("删除之后……");
    }

    public static void main(String[] args) {
        ProductService ps = new ProductServiceImpl();
        ps.addPro();
        ps.delPro();
        System.out.println("_____________________");
        ProxyProductServiceImpl psp = new ProxyProductServiceImpl(ps);
        psp.addPro();
        psp.delPro();
    }
}
