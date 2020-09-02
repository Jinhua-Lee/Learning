/*
 * Copyright (c)    2020/8/15 下午3:57.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/proxy/ProductServiceImplProxy.java
 * LastModified:    2019/10/8 下午11:23
 */

package com.designpattern.proxy;

/**
 * 静态代理类
 * @author Jinhua
 */
public class ProductServiceImplProxy implements ProductService {
	private ProductService ps;

	public ProductServiceImplProxy(ProductService ps) {
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
}
