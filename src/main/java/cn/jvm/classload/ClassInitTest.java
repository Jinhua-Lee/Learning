package cn.jvm.classload;

/**
 * 类对象初始化模拟<p>&emsp;
 * 1) 类变量（static）的加载顺序 => 按源文件中的顺序；
 *
 * @author Jinhua
 * @date 2020/9/30 21:59
 */
public class ClassInitTest {

    /**
     * Linking：Prepare阶段 num = 1
     * Initial：num = 1 -> num = 2
     */
    private static int num = 1;

    static {
        num = 2;
        number = 20;
        System.out.println("num = " + num);
        // 报错，非法前向引用
//        System.out.println("number = " + number);
    }

    /**
     * Linking：Prepare阶段 number = 0;
     * Initial: number = 20 -> number = 10(源文件中的顺序)
     */
    private static int number = 10;

    public static void main(String[] args) {
        // num == 10
        System.out.println("num = " + num);

        // number == 20
        System.out.println("number = " + number);
    }
}
