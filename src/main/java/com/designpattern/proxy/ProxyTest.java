/*
 * Copyright (c)    2020/8/15 下午3:57.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/proxy/ProxyTest.java
 * LastModified:    2019/10/8 下午11:23
 */

package com.designpattern.proxy;

public class ProxyTest {
	public static void main(String[] args) {
		ProductService ps = new ProductServiceImpl();
		ps.addPro();
		ps.delPro();
		System.out.println("_____________________");
		ProductServiceImplProxy psp = new ProductServiceImplProxy(ps);
		psp.addPro();
		psp.delPro();
	}
}
