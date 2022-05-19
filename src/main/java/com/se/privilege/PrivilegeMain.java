package com.se.privilege;

import com.se.privilege.character.MyCharacter;
import com.se.privilege.story.Story;

/**
 * 测试私有权限主方法
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/19 16:43
 */
public class PrivilegeMain {

    public static void main(String[] args) {
        
        Story story = new Story();
        story.introduce(MyCharacter.HARRY_POTTER);
        story.introduce(MyCharacter.RON_WEASLEY);
    }
}
