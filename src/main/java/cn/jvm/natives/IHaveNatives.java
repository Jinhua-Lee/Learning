package cn.jvm.natives;

/**
 * 本地方法定义的演示类。<p>
 * 1. native修饰的方法都没有方法体。<p>
 * 2. native和abstract关键字不能同用。<p>&emsp;
 * a. native的方法实现体不是Java语言，但不表示没有。<p>&emsp;
 * b. abstract方法是 虚方法 / 抽象方法。<p>&emsp;
 *
 * @author Jinhua
 * @date 2020/10/29 22:41
 */
public class IHaveNatives {

    public native void native1(int x);

    native static public long native2();

    native synchronized private float native3(Object o);

    native void native4(int[] array) throws Exception;
}
