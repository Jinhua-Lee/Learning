package cn.jvm.runtime.stack;

/**
 * 栈测试，通过读class文件来演示
 *
 * @author Jinhua
 * @date 2020/10/18 16:44
 */
public class StackTest {

    public void methodA() {
        int i = 10;
        int j = 20;
        methodB();
    }

    public void methodB() {
        int k = 30;
        int m = 40;
    }

    public static void main(String[] args) {
        StackTest st = new StackTest();
        st.methodA();
    }
}
