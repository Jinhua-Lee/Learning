package com.se.io.base;

import java.io.InputStream;

/**
 * 基础输入流测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/1 13:13
 */
public abstract class BaseInputStreamTest {

    protected InputStream inputStream;

    /**
     * 处理末尾的空内容
     *
     * @param bytes 包含内容的字节数组
     * @return 处理后的字节数组
     */
    protected byte[] processEmpty(byte[] bytes) {
        // 从某个位置开始，都是无效数据
        int emptyStart = -1;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i++] == 0) {
                emptyStart = i - 1;
                break;
            }
        }

        // 仅创建有效长度的数据数组
        byte[] processed = new byte[emptyStart];
        System.arraycopy(bytes, 0, processed, 0, emptyStart);
        return processed;
    }
}
