package com.se.privilege.story;

import com.se.privilege.character.MyCharacter;
import com.se.privilege.secrets.SharedSecrets;

/**
 * 故事
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/19 16:43
 */
public final class Story {

    /**
     * 介绍一个角色
     *
     * @param myCharacter 角色
     */
    public void introduce(MyCharacter myCharacter) {
        System.out.println(myCharacter.name() + " enters the room and says: "
                // 能使用不同包下的default方法
                + SharedSecrets.INSTANCE.secretCharacter.getPhrase(myCharacter));
    }
}
