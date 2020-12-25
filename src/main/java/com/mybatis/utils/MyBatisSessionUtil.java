/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mybatis/utils/MyBatisSessionUtil.java
 * LastModified:    2020/12/26 上午1:42
 */

package com.mybatis.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis 会话工具类
 *
 * @author Jinhua
 * @date 2020/12/26 1:42
 */
public class MyBatisSessionUtil {

    /**
     * MyBatis的SQL核心会话对象
     */
    private static final SqlSession SQL_SESSION;

    static {
        final String resource = "SqlMapConfig.xml";
        // 创建 SqlSession 工厂
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder()
                .build(MyBatisSessionUtil.class.getClassLoader().getResourceAsStream(resource));
        // 打开session会话
        SQL_SESSION = ssf.openSession();
    }

    /**
     * 获取会话对象
     *
     * @return 会话对象
     */
    public static SqlSession getSqlSession() {
        return SQL_SESSION;
    }

    /**
     * 关闭会话
     */
    public static void closeSqlSession() {
        SQL_SESSION.close();
    }
}
