package com.se.reflect;

import com.se.reflect.annotation.AutoWired;

/**
 * 用户Controller模拟
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/5/25 19:49
 */
public class UserController {

    @AutoWired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
