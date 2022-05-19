package com.se.privilege.secrets;

import com.se.privilege.character.MyCharacter;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/19 16:44
 */
public interface SecretCharacter {

    /**
     * 角色描述语句
     *
     * @param myCharacter 角色
     * @return 角色的描述语句
     */
    String getPhrase(MyCharacter myCharacter);
}
