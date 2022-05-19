package com.se.privilege.character;

import com.se.privilege.secrets.*;

/**
 * 秘密角色
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/19 16:43
 */
public enum MyCharacter {

    /**
     * 角色1
     */
    HARRY_POTTER {
        @Override
        String getPhrase() {
            return "Your bird, there was nothing I could do. He just caught fire.";
        }
    },

    /**
     * 角色2
     */
    RON_WEASLEY {
        @Override
        String getPhrase() {
            return "Who are you and what have you done with Hermione Granger?";
        }
    };

    static {
        SharedSecrets.INSTANCE.secretCharacter = MyCharacter::getPhrase;
    }

    /**
     * 【包内访问权限】需要将这个供能暴露给Story使用
     *
     * @return 角色台词
     */
    abstract String getPhrase();
}
