package com.jvm;

/**
 * JVM 指令操作模拟
 * @author Jinhua
 * @version 1.0
 * @date 2020/9/27 16:43
 */
public class InstructionTest {

    public static InstructionTest instructionTest;

    public static InstructionTest getInstance() {
        if (instructionTest == null) {
            return new InstructionTest();
        } else {
            return instructionTest;
        }
    }

    public static void main(String[] args) {
        InstructionTest.getInstance().testSingle();
    }

    /**
     * 测试一个变量
     */
    public int testSingle() {
        // 编译完成直接识别为5
        return  2 + 3;
    }

    /**
     * 测试定义两个变量相加
     */
    public int test2() {
        // 需要8行指令
        int i = 2;
        int j = 3;
        return i + j;
    }

    /**
     * 测试较复杂计算
     */
    public int testComplex() {
        int a = 100;
        int b = 200;
        int c = 300;

        return (a + b) * c;
    }

}
