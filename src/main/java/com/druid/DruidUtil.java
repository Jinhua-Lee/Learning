/*
 * Copyright (c)    2019/9/25 下午7:25.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/druid/DruidUtil.java
 * LastModified:    2019/9/25 下午7:25
 */

package com.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.c3p0.User;
import com.dbutils.PropertiesResolver;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Druid连接工具类
 * @author Jinhua
 */
public class DruidUtil {

    /**
     * 事务专用连接
     */
    private static Connection conn = null;

    /**
     * 存放连接的本地线程池
     */
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    private static DruidDataSource dds = new DruidDataSource();

    static {
        dds.setDriverClassName(PropertiesResolver.getValue("jdbc.driver"));
        dds.setUrl(PropertiesResolver.getValue("jdbc.url"));
        dds.setUsername(PropertiesResolver.getValue("jdbc.user"));
        dds.setPassword(PropertiesResolver.getValue("jdbc.password"));
    }


    /**
     * 从数据库连接池拿到Connection
     * @return
     */
    public static Connection getConnection() throws SQLException {
        conn = tl.get();

        if (conn != null) {
            return conn;
        }

        return dds.getConnection();
    }

    /**
     * 开启事务
     * 1. 获取一个Connection，设置它的自动提交为FALSE；
     * 2. 还要保证DAO中使用的连接是刚刚创建的；
     * 3. 保证 commitTransaction() 或者 rollbackTransaction() 可以获取到。
     * @throws SQLException SQL异常
     */
    public static  void beginTransaction() throws SQLException {
        conn = tl.get();
        // 开启事务前判断是否为空，防止重复开启事务
        if (conn != null) {
            throw new RuntimeException("请勿重复开启事务！");
        }
        conn = getConnection();
        conn.setAutoCommit(false);
        // 将本次的连接保存到本地ThreadLocal中
        tl.set(conn);
    }

    /**
     * 提交事务，连接放回连接池
     * @throws SQLException SQL异常
     */
    public static void commitTransaction() throws SQLException {
        // 从本地线程池中拿到连接
        conn = tl.get();

        if (conn == null) {
            throw new RuntimeException("请先开启事务再提交！");
        }

        conn.commit();
        conn.close();

        // 从本地线程池中移除
        tl.remove();
    }

    /**
     * 回滚事务, 连接放回连接池
     * @throws SQLException SQL异常
     */
    public static void rollbackTransaction() throws SQLException {
        // 从本地线程池中拿到连接
        conn = tl.get();

        if (conn == null) {
            throw new RuntimeException("请先开启事务再回滚！");
        }

        conn.rollback();
        conn.close();

        // 从本地线程池中移除
        tl.remove();
    }

    /**
     * 此方法用于判断存在的连接是否是事务专用连接，不是则在事务结束时候关闭它
     * @param connection 已存在的连接
     * @throws SQLException	SQL异常
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        /**
         * 判断它是不是事务专用连接：
         * 如果不是事务专用，则需要关闭；
         * 反之不用关闭
         */


        conn = tl.get();
        // 如果 conn 为null，则没有事务， connection一定不是事务专用连接
        if (conn == null) {
            connection.close();
            // 如果有事务，且connection不是事务专用连接
        } else if (conn != connection) {
            connection.close();
        }

        // 从本地线程池中移除
        tl.remove();
    }


    public static void main(String[] args) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "Select * From Usr";
        Connection conn = DruidUtil.getConnection();
        List<User> users = qr.query(conn, sql, new BeanListHandler<>(User.class));

        for (User u : users) {
            System.out.println(u);
        }

    }

}
