package com.ee.dbutils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC 连接池模拟
 * @author Jinhua
 */
public class JdbcUtils {

	/**
	 * 事务专用连接
	 */
	private static Connection conn = null;

	/**
	 * 存放事务连接的本地线程池
	 */
	private static final ThreadLocal<Connection> T_LOCAL = new ThreadLocal<>();

	/**
	 * C3P0数据源
	 */
	private static final ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource();

	/*
	 * 静态块，设置参数
	 */
	static {
		try {
			DATA_SOURCE.setDriverClass(PropertiesResolver.getValue("jdbc.driver"));
			DATA_SOURCE.setJdbcUrl(PropertiesResolver.getValue("jdbc.url"));
			DATA_SOURCE.setUser(PropertiesResolver.getValue("jdbc.user"));
			DATA_SOURCE.setPassword(PropertiesResolver.getValue("jdbc.password"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从连接池中拿到连接对象 Connection
	 * @return 返回 Connection连接对象
	 * @throws SQLException SQL异常
	 */
	public static Connection getConnection() throws SQLException {
		conn = T_LOCAL.get();
		// 如果 conn 不为空，说明开启了事务，直接获取到该连接
		if (conn != null) {
			return conn;
		}
		// 否则从连接池拿到新的连接
		return DATA_SOURCE.getConnection();
	}

	public static ComboPooledDataSource getDataSource() {
		return DATA_SOURCE;
	}

	/**
	 * 开启事务
	 * 1. 获取一个Connection，设置它的自动提交为FALSE；
	 * 2. 还要保证DAO中使用的连接是刚刚创建的；
	 * 3. 保证 commitTransaction() 或者 rollbackTransaction() 可以获取到。
	 * @throws SQLException SQL异常
	 */
	public static  void beginTransaction() throws SQLException {
		conn = T_LOCAL.get();
		// 开启事务前判断是否为空，防止重复开启事务
		if (conn != null) {
			throw new RuntimeException("请勿重复开启事务！");
		}
		conn = getConnection();
		conn.setAutoCommit(false);
		// 将本次的连接保存到本地ThreadLocal中
		T_LOCAL.set(conn);
	}

	/**
	 * 提交事务，连接放回连接池
	 * @throws SQLException SQL异常
	 */
	public static void commitTransaction() throws SQLException {
		// 从本地线程池中拿到连接
		conn = T_LOCAL.get();

		if (conn == null) {
			throw new RuntimeException("请先开启事务再提交！");
		}

		conn.commit();
		conn.close();

		// 从本地线程池中移除
		T_LOCAL.remove();
	}

	/**
	 * 回滚事务, 连接放回连接池
	 * @throws SQLException SQL异常
	 */
	public static void rollbackTransaction() throws SQLException {
		// 从本地线程池中拿到连接
		conn = T_LOCAL.get();

		if (conn == null) {
			throw new RuntimeException("请先开启事务再回滚！");
		}

		conn.rollback();
		conn.close();

		// 从本地线程池中移除
		T_LOCAL.remove();
	}

	/**
	 * 此方法用于判断存在的连接是否是事务专用连接，不是则在事务结束时候关闭它
	 * @param connection 已存在的连接
	 * @throws SQLException	SQL异常
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		/*
		 * 判断它是不是事务专用连接：
		 * 如果不是事务专用，则需要关闭；
		 * 反之不用关闭
		 */
		conn = T_LOCAL.get();
		// 如果 conn 为null，则没有事务， connection一定不是事务专用连接
		if (conn == null) {
			connection.close();
		} else if (conn != connection) {
			// 如果有事务，且connection不是事务专用连接
			connection.close();
		}
		// 从本地线程池中移除
		T_LOCAL.remove();
	}
}