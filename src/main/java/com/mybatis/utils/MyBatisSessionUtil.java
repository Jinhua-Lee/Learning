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

import java.util.Objects;

/**
 * MyBatis 会话工具类
 *
 * @author Jinhua
 * @date 2020/12/26 1:42
 */
public class MyBatisSessionUtil {

    /**
     * 供增删改的核心会话对象
     */
    private static final ThreadLocal<SqlSession> TRANSACTION_SESSION_LOCAL;

    /**
     * 供查询的会话对象
     */
    private static final ThreadLocal<SqlSession> SESSION_LOCAL;

    /**
     * 会话工厂
     */
    private static final SqlSessionFactory SQL_SESSION_FACTORY;


    static {
        final String resource = "SqlMapConfig.xml";
        // 创建 SqlSession 工厂
        SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder()
                .build(MyBatisSessionUtil.class.getClassLoader().getResourceAsStream(resource));
        // 线程绑定变量
        SESSION_LOCAL = new ThreadLocal<>();
        TRANSACTION_SESSION_LOCAL = new ThreadLocal<>();
    }

    /**
     * 获取会话对象
     *
     * @return 会话对象
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = SESSION_LOCAL.get();
        if (Objects.nonNull(sqlSession)) {
            return sqlSession;
        }
        SqlSession newSession = SQL_SESSION_FACTORY.openSession(true);
        SESSION_LOCAL.set(newSession);
        return newSession;
    }

    /**
     * 获取事务会话对象
     *
     * @return 事务会话对象
     */
    public static SqlSession getTransactionSession() {
        SqlSession sqlSession = TRANSACTION_SESSION_LOCAL.get();
        if (Objects.nonNull(sqlSession)) {
            return sqlSession;
        }
        SqlSession newSession = SQL_SESSION_FACTORY.openSession(false);
        TRANSACTION_SESSION_LOCAL.set(newSession);
        return newSession;
    }

    /**
     * 关闭会话
     */
    public static void releaseSession() {
        SqlSession sqlSession = SESSION_LOCAL.get();
        if (Objects.nonNull(sqlSession)) {
            sqlSession.close();
            SESSION_LOCAL.remove();
        }
        SqlSession transactionSession = TRANSACTION_SESSION_LOCAL.get();
        if (Objects.nonNull(transactionSession)) {
            transactionSession.close();
            TRANSACTION_SESSION_LOCAL.remove();
        }
    }
}
