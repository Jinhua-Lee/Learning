package com.se.privilege.secrets;

/**
 * 共享密钥机制
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/19 16:44
 */
public final class MySharedSecrets {
    public static MySharedSecrets INSTANCE = new MySharedSecrets();

    /**
     * 如何初始化这个变量
     */
    public SecretCharacter secretCharacter;

    /**
     * 懒汉单例
     */
    private MySharedSecrets() {
    }
}
