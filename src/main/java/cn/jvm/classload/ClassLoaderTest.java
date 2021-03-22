package cn.jvm.classload;

import sun.misc.Launcher;
//import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

/**
 * 类加载器模拟<p>&emsp;
 * 1) 类加载器之间的关系；<p>&emsp;
 * 2) 各种类加载器的获取方式；
 *
 * @author Jinhua
 * @date 2020/10/12 23:00
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        func2();
    }

    /**
     * 各种类是由什么类加载器加载的，以及各种【类加载器的关系】模拟
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
     * 【系统类加载器】和【扩展类加载器】各自的加载路径模拟
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
//        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
//        System.out.println("classLoader1 = " + classLoader1);
    }


    /**
     * 4种获取类加载器的方式<p>&emsp;
     * 1. 获取当前类的ClassLoader；<p>&emsp;
     * 2. 获取当前线程上下文的ClassLoader；<p>&emsp;
     * 3. 获取系统默认的ClassLoader；<p>&emsp;
     * 4. 获取调用者的ClassLoader；
     */
    public void func3() throws ClassNotFoundException {
        /*
         * 1. 获取当前类的ClassLoader；
         */
        ClassLoader c1 = Class.forName("java.sql.Connection").getClassLoader();
        System.out.println("c1 = " + c1);

        /*
         * 2. 获取当前线程上下文的ClassLoader；
         */
        ClassLoader c2 = Thread.currentThread().getContextClassLoader();
        System.out.println("c2 = " + c2);

        /*
         * 3. 获取系统默认的ClassLoader；
         */
        ClassLoader c3 = ClassLoader.getSystemClassLoader();
        System.out.println("c3 = " + c3);
    }
}
