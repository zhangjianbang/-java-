package com.phone.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER="scott";
	private static final String PASSWORD="tiger";
	
	//java环境和驱动环境建立连接，注册驱动
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//连接
	public static Connection getConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关闭数据
	public static void closeConn(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//关闭执行对象
	public static void closeStatement(Statement pstmt){
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//关闭结果集
	public static void closeRs(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//测试
	public static void main(String[] args) {
		System.out.println(getConn());
	}
}
