package cn.design.entity.user;

import java.io.Serializable;

/**
 * 【用户的抽象】<p>
 * 登录系统的用户，系统持久化其信息。
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/16 11:26
 */
public abstract class User implements Serializable {
    protected Integer id;
}
