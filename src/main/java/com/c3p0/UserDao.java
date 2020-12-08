package com.c3p0;

import java.util.List;

/**
 * 用户持久层
 *
 * @author Jinhua
 */
public interface UserDao {
    /**
     * 根据id返回用户
     *
     * @param id 用户id
     * @return 用户
     */
    User getUserById(int id);

    /**
     * 返回所有用户
     *
     * @return 返回所有用户
     */
    List<User> getAllUser();
}
