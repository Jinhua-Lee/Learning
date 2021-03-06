package cn.jvm.classload;

/**
 * 类加载子系统初始化阶段模拟<p>&emsp;
 * 1) 类构造器方法<clinit>() 方法，只会对类变量（static）初始化，<p>&emsp;
 * 2) 若不存在类变量，则不会调用<clinit>()方法。
 *
 * @author Jinhua
 * @date 2020/9/30 22:43
 */
public class ClInitTest {

    private int a = 1;

//    private static int c = 3;

    public static void main(String[] args) {
        int b = 2;
    }

    public ClInitTest() {
        a = 10;
        int d = 20;
    }

    /*
     * 任何类声明以后，内部至少会存在一个构造器
     * 所以类加载都会调用构造器方法<init>
     */
}
