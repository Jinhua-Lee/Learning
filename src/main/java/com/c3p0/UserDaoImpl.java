package com.c3p0;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	QueryRunner qr = new QueryRunner(C3P0Utils.getDs());

	/**
	 * 获得指定ID用户
	 * @param id 外部传入用户ID
	 * @return 返回一个用户实体
	 */
	@Override
	public User getUserByID(int id) {
		String sql = "Select * From Usr Where id = ?";

		User user = null;
		try {
			user = (User) qr.query(sql, new BeanHandler(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {
		String sql = "Select * From Usr";
		List<User> users = new ArrayList<>();
		try {
			users = (List<User>) qr.query(sql, new BeanListHandler(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Test
	public void test() {
		List<User> users = getAllUser();
		for (User u : users ) {
			System.out.println(u.toString());
		}
	}
}
