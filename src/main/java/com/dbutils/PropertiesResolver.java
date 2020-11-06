package com.dbutils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 属性文件的解析类
 *
 * @author Jinhua
 */

public class PropertiesResolver {

    private static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("mysql");
    }

    public static String getValue(String key) throws MissingResourceException {
        return bundle.getString(key);
    }

    public static void main(String[] args) {
        String value = PropertiesResolver.getValue("jdbc.user");
        System.out.println("value = " + value);
    }

}
