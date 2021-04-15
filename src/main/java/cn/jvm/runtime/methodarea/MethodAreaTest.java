package cn.jvm.runtime.methodarea;

/**
 * 模拟方法区的使用情况<p>&emsp;
 * 静态方法，除了out对象，未用到堆，目前PPT中暂未展示堆结构变化。
 *
 * @author Jinhua
 * @date 2021/4/15 22:31
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        int x = 500;
        int y = 100;
        int a = x / y;
        int b = 50;
        System.out.println(a + b);
    }
}
