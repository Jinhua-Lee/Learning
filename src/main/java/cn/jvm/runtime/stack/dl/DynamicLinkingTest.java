package cn.jvm.runtime.stack.dl;

/**
 * 动态链接演示
 *
 * @author Jinhua
 * @date 2020/10/25 0:27
 */
public class DynamicLinkingTest {

    private int num = 10;

    public void methodA() {
        System.out.println("methodA()...");
    }

    public void methodB() {
        System.out.println("methodB()...");
        methodA();
        num++;
    }
}
