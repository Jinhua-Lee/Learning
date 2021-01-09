/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/classload/StringTest.java
 * LastModified:    2020/10/13 下午11:19
 */

package cn.jvm.classload;

/**
 * 双亲委派机制的模拟
 *      1. 一个类加载器收到了类加载请求，自己并不会去加载，而是把这个请求委托给父加载器去执行；
 * 		2. 如果父加载器还存在父加载器则继续向上委托，直至达到最顶层的启动类（BootStrap ClassLoader）
 * 		3. 如果父类加载器可以完成加载任务，就成功返回，若父类加载器无法完成此加载任务，子类加载器才会尝试去加载。
 * @author Jinhua
 * @date 2020/10/13 22:49
 */
public class StringTest {

    public static void main(String[] args) {
        /*
         * 此处加载的是核心类String还是自定义的Java.lang.String呢
         *      答案是核心类String
         */
        String str = new java.lang.String();
        System.out.println(str.getClass().getClassLoader());

        /*
         * 非核心类，非扩展类，所以是用AppClassLoader
         */
        StringTest test = new StringTest();
        System.out.println(test.getClass().getClassLoader());
    }
}
