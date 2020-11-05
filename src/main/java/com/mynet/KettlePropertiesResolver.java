package com.mynet;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * kettle配置文件读取
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/11/4 13:58
 */
public class KettlePropertiesResolver {

    /**
     * 资源绑定，懒汉式单例，内部使用
     */
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("kettle");
    }

    public static String getProperty(String key) throws MissingResourceException {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException mex) {
            throw new MissingResourceException("未找到对应的key", KettlePropertiesResolver.class.getName(), key);
        }
    }

    public static void main(String[] args) {
        String property = KettlePropertiesResolver.getProperty("model_service_ip");
        System.out.println("property = " + property);
    }

}