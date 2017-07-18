package com.phone.dao;

import java.sql.*;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BaseDao<T> {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";

	// 加载驱动
	static {
		boolean result = DbUtils.loadDriver(DRIVER);
		if (result == false) {
			try {
				throw new SQLException("加载驱动失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 创建连接
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////
	// 数据库访问操作
	/**
	 * 返回单个对象
	 * 
	 * @param <T>
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            如果没有参数就设为 Object[] params={}
	 * @return
	 */
	public <T> T get(String sql, Class<T> clazz, Object[] params) {
		T obj = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(conn, sql, new BeanHandler<T>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return obj;
	}

	/**
	 * 返回多个对象
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            如果没有参数就设为 Object[] params={}
	 * @return
	 */
	public <T> List<T> query(String sql, Class<T> clazz, Object[] params) {
		List beans = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, sql, new BeanListHandler<T>(
					clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return beans;
	}

	/**
	 * 返回增删改是否成功
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql, Object[] params) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(conn, sql, params);
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return flag;
	}

	/**
	 * 需要进行事务操作时，在同一事务管理下操作
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Connection conn, String sql, Object[] params)
			throws SQLException {
		boolean flag = false;
		QueryRunner qRunner = new QueryRunner();
		int i = qRunner.update(conn, sql, params);
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	/***
	 * 批量操作，需要用到事务
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean batchUpdate(Connection conn, String sql, Object[][] params)
			throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		int result = 0;
		boolean flag = false;
		result = qRunner.batch(conn, sql, params).length;
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 返回统计单值,
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Number getCount(String sql, Object[] params) {
		Number value = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			value = (Number) qRunner.query(conn, sql, new ScalarHandler(),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return value;
	}

	/**
	 * 返回主键,通常是执行insert语句时返回当前的主键值
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long getCurrentKey(String sql, Object[] params) {
		Connection conn = null;
		Long key = 0l;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			key = (Long) qRunner
					.insert(conn, sql, new ScalarHandler(1), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return key;
	}

	// 测试
	public static void main(String[] args) {
		// 获得连接对象
		Connection conn = new BaseDao().getConnection();
		// 打印
		System.out.println(conn);
		// 测试
		// Object[] params ={};
		// Number count = new BaseDao().getCount("select count(*) from product",
		// params);
		// System.out.println(count);
		// 测试返回主键值
		/*
		 * String sql = "insert into user(username,password) values(?,?)";
		 * Object[] params = {"asdf","asdfasd"}; Long key = new
		 * BaseDao().getCurrentKey(sql, params); System.out.println(key);
		 */

		// 关闭连接
		DbUtils.closeQuietly(conn);

	}
}
