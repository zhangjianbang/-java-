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

	// ��������
	static {
		boolean result = DbUtils.loadDriver(DRIVER);
		if (result == false) {
			try {
				throw new SQLException("��������ʧ��");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ��������
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
	// ���ݿ���ʲ���
	/**
	 * ���ص�������
	 * 
	 * @param <T>
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            ���û�в�������Ϊ Object[] params={}
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
	 * ���ض������
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            ���û�в�������Ϊ Object[] params={}
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
	 * ������ɾ���Ƿ�ɹ�
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
	 * ��Ҫ�����������ʱ����ͬһ��������²���
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
	 * ������������Ҫ�õ�����
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
	 * ����ͳ�Ƶ�ֵ,
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
	 * ��������,ͨ����ִ��insert���ʱ���ص�ǰ������ֵ
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

	// ����
	public static void main(String[] args) {
		// ������Ӷ���
		Connection conn = new BaseDao().getConnection();
		// ��ӡ
		System.out.println(conn);
		// ����
		// Object[] params ={};
		// Number count = new BaseDao().getCount("select count(*) from product",
		// params);
		// System.out.println(count);
		// ���Է�������ֵ
		/*
		 * String sql = "insert into user(username,password) values(?,?)";
		 * Object[] params = {"asdf","asdfasd"}; Long key = new
		 * BaseDao().getCurrentKey(sql, params); System.out.println(key);
		 */

		// �ر�����
		DbUtils.closeQuietly(conn);

	}
}
