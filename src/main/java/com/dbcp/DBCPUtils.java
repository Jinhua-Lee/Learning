package com.dbcp;

import com.dbutils.PropertiesResolver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPUtils {
	private static Connection conn = null;
	private static BasicDataSource dataSource = new BasicDataSource();
	private static QueryRunner qr = null;

	static {
		dataSource.setDriverClassName(PropertiesResolver.getValue("jdbc.driver"));
		dataSource.setUrl(PropertiesResolver.getValue("jdbc.url"));
		dataSource.setUsername(PropertiesResolver.getValue("jdbc.user"));
		dataSource.setPassword(PropertiesResolver.getValue("jdbc.password"));
		qr = new QueryRunner(dataSource);
		try {
			 conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static QueryRunner getQr() {
		return qr;
	}
}
