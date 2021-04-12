package cn.jvm.runtime.methodarea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 方法区内存溢出的例子<p>&emsp;
 * 1) VM参数：-XX:MetaspaceSize=10M -XXMaxMetaspaceSize=10M<p>&emsp;
 * 2) 实现：利用ClassWriter
 *
 * @author Jinhua
 * @date 2021/4/12 22:59
 */
public class MethodAreaOom extends ClassLoader {

    public static void main(String[] args) {
        int j = 0;
        MethodAreaOom oom = new MethodAreaOom();
        int num = 10_000;
        try {
            for (int i = 0; i < num; i++) {
                // 用于生成类的二进制字节码
                ClassWriter clWriter = new ClassWriter(0);
                // 版本号，修饰符，类名，包名，父类，接口
                clWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                byte[] code = clWriter.toByteArray();
                oom.defineClass("Class" + i, code, 0, code.length);
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
