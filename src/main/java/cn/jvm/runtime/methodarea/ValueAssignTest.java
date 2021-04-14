package cn.jvm.runtime.methodarea;

/**
 * 变量赋值的时机测试<p>&emsp;
 * 1) non-final变量测试<p>&emsp;
 * 2) static final变量测试
 *
 * @author Jinhua
 * @date 2021/4/14 22:40
 */
@SuppressWarnings("all")
public class ValueAssignTest {

    public static void main(String[] args) {
        Order order = null;
        // 类的实例为null，仍然可以访问
        order.hello();
        System.out.println(order.count);
        System.out.println(order.NUMBER);
    }
}

class Order {
    /**
     * 【链接 -> 准备】阶段仅默认赋值（0值，引用类型为null值）
     * 【初始化】过程 执行class对象构造，<clinit>显式赋值为1
     */
    public static int count = 1;

    /**
     * 类加载过程中，【链接 -> 准备】阶段已经显式赋值
     */
    public static final int NUMBER = 2;

    public static void hello() {
        System.out.println("hello");
    }
}