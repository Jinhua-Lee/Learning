package cn.design.entity.user;

import cn.design.entity.EntitySupport;
import lombok.Getter;

/**
 * 【用户的抽象】<p>
 * 登录系统的用户，系统持久化其信息。
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
@Getter
public abstract class User implements EntitySupport {
    protected Integer id;
}
