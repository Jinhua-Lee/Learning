package cn.jvm.stringtable;

/**
 * MemoryTest
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 17:27
 */
public class MemoryTest {

    @SuppressWarnings("all")
    public static void main(String[] args) {// line 1
        int i = 1;  // line 2
        Object obj = new Object();  // line 3
        MemoryTest mTest = new MemoryTest(); // line 4
        mTest.foo(obj);
    }   // line 5

    @SuppressWarnings("all")
    private void foo(Object obj) { // line 6
        String str = obj.toString();    // line 7
        System.out.println(str);
    }   // line 8
}
