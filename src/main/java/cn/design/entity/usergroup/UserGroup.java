package cn.design.entity.usergroup;

import cn.design.entity.user.User;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 【用户组】
 * 进行用户的树形管理
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/21 14:52
 */
@EqualsAndHashCode(of = "id")
public class UserGroup {
    private Integer id;
    private List<User> users;
}
