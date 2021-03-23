package cn.jvm.runtime.stack.os;

/**
 * 操作数栈演示
 *
 * @author Jinhua
 * @date 2020/10/23 22:31
 */
public class OperandStackTest {

    public void testAddOperation() {
        byte i = 15;
        int j = 8;
        int k = i + j;
    }

    public int getSum() {
        int m = 10;
        int n = 20;
        int k = m + n;
        return k;
    }

    public void testGetSum() {
        // 获取上一个方法栈帧返回的结果，并保存到操作数栈中
        int i = getSum();
        int j = 10;
    }

    /**
     * 自增问题:
     * ++i 和 i++ 的区别，放到字节码篇章再讲
     */
    public void testSelfIncrease() {
        /*
         * 第一类问题
         */
        int i1 = 10;
        int i2 = 10;
        i1++;
        ++i2;

        /*
         * 第二类问题
         */
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        /*
         * 第三类问题
         */
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        /*
         * 第四类问题
         */
        int i9 = 10;
        int i10 = i9++ + ++i9;

    }
}
