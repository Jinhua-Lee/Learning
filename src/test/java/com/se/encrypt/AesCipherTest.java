package com.se.encrypt;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/1/18 16:10
 */
public class AesCipherTest {

    /**
     * iv向量字节数组
     */
    private static final byte[] VECTOR = new byte[]{0x19, 0x34, 0x57, 0x72, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
            0x12, 0x64, 0x14, 0x78, (byte) 0x90, (byte) 0xAC, (byte) 0xAE, 0x45};

    @Test
    public void testEncrypt() {
        String str = "hello";
        String encrypt = AesCipher.encrypt(str);
        System.out.println("encrypt = " + encrypt);
    }

    @Test
    public void testBinaryString() {
        System.out.println(Base64.getEncoder().encodeToString(VECTOR));
    }
}