package com.se.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/1/18 15:42
 */
@Slf4j
public class AesCipher {

    /**
     * 密钥
     */
    private static final String KEY = "8914534290ABCDEF1264147890ACAB55";

    /**
     * iv向量字节数组
     */
    private static final byte[] VECTOR = new byte[]{0x19, 0x34, 0x57, 0x72, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
            0x12, 0x64, 0x14, 0x78, (byte) 0x90, (byte) 0xAC, (byte) 0xAE, 0x45};

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String pwd) {
        try {
            // 实例化算法：加密方法 + 模式 + 填充模式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            byte[] dataBytes = pwd.getBytes(StandardCharsets.UTF_8);
            // 实例化【密钥规范】
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            // 实例化【向量】
            IvParameterSpec ivParameterSpec = new IvParameterSpec(VECTOR);

            // 初始化算法
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] original = cipher.doFinal(dataBytes);
            String base64Str = Base64.getEncoder().encodeToString(original);
            return base64Str.replace("\n", "");
        } catch (Exception e) {
            log.error("encrypt error: {}", e.getMessage());
            return null;
        }
    }

    public static String decrypt(String pwd) {
        try {
            // 获取实例
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            byte[] pwdBytes = Base64.getDecoder().decode(pwd);
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            // 向量
            IvParameterSpec ivParameterSpec = new IvParameterSpec(VECTOR);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] original = cipher.doFinal(pwdBytes);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("decrypt error: {}", e.getMessage());
            return null;
        }
    }
}
