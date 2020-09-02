package com.dbutils;

import java.util.ResourceBundle;

/**
 * 属性文件的解析类
 * @author 子期
 */

public class PropertiesResolver {
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("mysql");
	}

	public static String getValue(String key) {
		return bundle.getString(key);
	}
}
