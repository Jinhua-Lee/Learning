package com.se.math;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 安全的Random使用。<p>
 * VMOptions:<p>&emsp;
 * -ea
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/13 16:17
 */
public class SecureRandomHolder {

    public static Random secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException ignored) {
            // 最基础情况下，不会走到这个分支
        }
        assert secureRandom != null;
    }

    public static void main(String[] args) {
        int nextInt = secureRandom.nextInt(5);
        System.out.println("nextInt = " + nextInt);
    }
}
