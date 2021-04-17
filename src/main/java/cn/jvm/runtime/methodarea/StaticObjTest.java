package cn.jvm.runtime.methodarea;

/**
 * 《深入理解Java虚拟机》的例子<p>&emsp;
 * 各种变量指向的值的存放位置：<p>&emsp;&emsp;
 * 1) 静态属性
 * 2) 属性
 * 3) 方法内的局部变量
 *
 * @author Jinhua
 * @date 2021/4/17 15:48
 */
public class StaticObjTest {
    static class Test {
        /**
         * 静态属性
         */
        static ObjectHolder staticObj = new ObjectHolder();
        /**
         * 属性
         */
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            // 局部变量
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new StaticObjTest.Test();
        test.foo();
    }
}
