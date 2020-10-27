/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/ra/ReturnAddressTest.java
 * LastModified:    2020/10/27 下午10:24
 */

package com.jvm.runtime.stack.ra;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * 返回指令包含
 *      1. ireturn -> 返回值为boolean, char, byte, short, int类型
 *      2. lreturn, freturn, dreturn -> 对应返回值为long, float, double
 *      3. return -> 对应void
 *      4. areturn -> 对应引用类型，返回其引用地址。
 * @author Jinhua
 * @date 2020/10/27 22:24
 */
public class ReturnAddressTest {

    public boolean methodBoolean() {
        return false;
    }

    public byte methodByte() {
        return 0;
    }

    public short methodShort() {
        return 0;
    }

    public char methodChar() {
        return 'a';
    }

    public int methodInt() {
        return 0;
    }

    public long methodLong() {
        return 0L;
    }

    public float methodFloat() {
        return 0.0f;
    }

    public double methodDouble() {
        return 0.0;
    }

    public String methodString() {
        return null;
    }

    public Date methodDate() {
        return null;
    }

    public void methodVoid() {

    }

    static {
        int i = 10;
    }

    public void method2() {
        methodVoid();

        try {
            method1();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

    }

    public void method1() throws IOException {
        File file = new File("C:/Users/Jinhua/Desktop", "temp.txt");
        FileReader fis = new FileReader(file);
        char[] cBuffer = new char[1024];
        int len;
        while ((len = fis.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.println(str);
        }
        fis.close();
    }

}
