/*
 * Copyright (c)    2020/8/15 下午3:58.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/dynamicproxy/MyInvocationHandler.java
 * LastModified:    2019/10/8 下午11:23
 */

package com.designpattern.dynamicproxy;

import java.lang.reflect.*;

/**
 * @author Jinhua
 */
public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler() {
		super();
	}

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	/**
	 * 产生代理对象的方法
	 * @param proxy 代理对象
	 * @param method 原有要增强的方法
	 * @param args 方法的参数
	 * @return 返回动态代理类对象
	 * @throws Throwable 反射可能抛出的异常
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 添加了代理方法
		if("getName".equals(method.getName())){
			System.out.println("s++++++before " + method.getName() + "++++++");
			Object result = method.invoke(target, args);
			System.out.println("++++++after " + method.getName() + "++++++");
			return result;
		} else {	// 未添加代理方法
			Object result = method.invoke(target, args);
			return result;
		}
	}

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		InvocationHandler ih = new MyInvocationHandler(userService);
		// 代理对象
		UserService usp = (UserService) Proxy.newProxyInstance
				(userService.getClass().getClassLoader(),
						userService.getClass().getInterfaces(), ih);
		System.out.println(usp.getName(1) + "---" + usp.getAge(1));
	}
}
