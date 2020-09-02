/*
 * Copyright (c)    2020/8/15 下午3:58.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/dynamicproxy/UserServiceImpl.java
 * LastModified:    2019/10/8 下午11:23
 */

package com.designpattern.dynamicproxy;

public class UserServiceImpl implements UserService {
	@Override
	public String getName(int id) {
		System.out.println("----====GetName====----");
		return "dbutils";
	}

	@Override
	public int getAge(int id) {
		System.out.println("----====GetAge====----");
		return 21;
	}
}
