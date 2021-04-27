package cn.jvm.stringtable;

/**
 * 练习题<p>
 * 这里考察的其实是方法参数传递的问题。java只有值传递类型。<p>&emsp;&emsp;
 * 1) 基本类型，传递的是值的副本，修改不影响原值；<p>&emsp;&emsp;
 * 2) 引用类型，传递的是【指针的值】，进入调用方法栈时候，将该指针值赋值给【方法的局部变量】（两个指针同时指向同一个对象）。<p>&emsp;
 * 所以如果不是传递的引用类型，修改是不会生效的。
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 14:10
 */
public class ParamTest {
    String str = "good";
    char[] chs = {'t', 'e', 's', 't'};
    Integer a = 1;

    /**
     * 替换方法测试
     *
     * @param localStr String类型
     * @param localChs char[] 类型
     * @param localA   Integer类型
     */
    public void change(String localStr, char[] localChs, Integer localA) {
        localStr = "test OK";
        localChs[0] = 'b';
        localA = 2;
    }

    public static void main(String[] args) {
        ParamTest exercise = new ParamTest();
        exercise.change(exercise.str, exercise.chs, exercise.a);
        // good
        System.out.println(exercise.str);
        // best
        System.out.println(exercise.chs);
        // 1
        System.out.println(exercise.a);
    }
}
