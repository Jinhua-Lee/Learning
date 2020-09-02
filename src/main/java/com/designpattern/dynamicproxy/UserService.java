/*
 * Copyright (c)    2020/8/15 下午3:58.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/designpattern/dynamicproxy/UserService.java
 * LastModified:    2019/10/8 下午11:23
 */

package com.designpattern.dynamicproxy;

/**
 * @author Jinhua
 */
public interface UserService {
	/**
	 * 根据id获取名字
	 * @param id ID
	 * @return 名字
	 */
	String getName(int id);

	/**
	 * 根据id获取年龄
	 * @param id ID
	 * @return 年龄
	 */
	int getAge(int id);
}
