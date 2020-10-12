/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/ClassLoaderTest.java
 * LastModified:    2020/10/12 下午11:00
 */

package com.jvm;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

/**
 * 类加载器模拟
 * @author Jinhua
 * @date 2020/10/12 23:00
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        func2();
    }

    /**
     * 各种类是由什么类加载器加载的，以及各种类加载器的关系模拟
     */
    private static void func1() {
        /*
         * 获取系统类加载器:
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);

        /*
         * 获取其上层，扩展类加载器
         *      sun.misc.Launcher$ExtClassLoader@42f30e0a
         */
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("extClassLoader = " + extClassLoader);

        /*
         * 试图获取其上层,引导类加载器
         *      null
         */
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("bootstrapClassLoader = " + bootstrapClassLoader);

        /*
         * 对用户自定义（当前）类ClassLoaderTest来说，类加载器为
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         */
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);

        /*
         * 对于String，使用引导类加载器加载 --> Java核心类库都是使用引导类加载器BootStrap ClassLoader加载的
         *      null
         */
        ClassLoader cl = String.class.getClassLoader();
        System.out.println("cl = " + cl);
    }

    /**
     * 系统类加载器和扩展类加载器各自的加载路径模拟
     */
    private static void func2() {

        System.out.println("========启动类加载器========");

        // 获取BootStrap ClassLoader能加载的类api路径
        URL[] bootstrapUrls = Launcher.getBootstrapClassPath().getURLs();
        for (URL bootstrapUrl : bootstrapUrls) {
            System.out.println(bootstrapUrl.toExternalForm());
        }
        /*
         * 从上面的路径中找到一个类，看看他们的类加载器是什么
         *      null -> BootStrap ClassLoader
         */
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);

        System.out.println("========扩展类加载器========");
        String extDirs = System.getProperty("java.ext.dirs");
        String separator = ";";
        for (String path : extDirs.split(separator)) {
            System.out.println("path = " + path);
        }

        /*
         * 从上面的路径中找到一个类，看看他们的类加载器是什么
         *      sun.misc.Launcher$ExtClassLoader@7946e1f4 -> 扩展类加载器
         */
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println("classLoader1 = " + classLoader1);
    }
}
