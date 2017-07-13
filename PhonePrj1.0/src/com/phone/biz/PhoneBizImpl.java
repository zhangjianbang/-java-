package com.phone.biz;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phone.po.Phone;
import com.phone.util.DBConnection;

public class PhoneBizImpl implements PhoneBiz {

	/**
	 * 根据id查询数据
	 */
	@Override
	public boolean addPhone(Phone phone) {
		boolean result = false;
		Connection conn = null;//连接对象
		PreparedStatement pstmt = null;//执行对象
		String sql = "insert into phone values(seq_pid.nextval,?,?,?)";//发送的sql语句
		//访问数据库
		try {
			//1.获取连接
			conn = DBConnection.getConn();
			//2.创建执行对象
			pstmt = conn.prepareStatement(sql);//执行对象功能：负责把sql语句通过conn发送到数据库服务器
			//sql语句如果有参数，需要给参数赋值
			pstmt.setString(1, phone.getName());
			pstmt.setString(2, phone.getSex());
			pstmt.setString(3, phone.getphoneno());			
			//3.执行
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.关闭资源
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * 根据id删除数据
	 */
	@Override
	public boolean deletePhone(int pid) {
		boolean result = false;
		Connection conn = null;//连接对象
		PreparedStatement pstmt = null;//执行对象
		String sql = "delete phone where pid=?";//发送的sql语句
		//访问数据库
		try {
			//1.获取连接
			conn = DBConnection.getConn();
			//2.创建执行对象
			pstmt = conn.prepareStatement(sql);//执行对象功能：负责把sql语句通过conn发送到数据库服务器
			//sql语句如果有参数，需要给参数赋值
			pstmt.setInt(1, pid);	
			//3.执行
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.关闭资源
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * 根据pid更新数据
	 */
	@Override
	public boolean updatePhone(Phone phone) {
		boolean result = false;
		Connection conn = null;//连接对象
		PreparedStatement pstmt = null;//执行对象
		String sql = "update phone set name=?, sex=?, phoneno=? where pid=?";//发送的sql语句
		//访问数据库
		try {
			//1.获取连接
			conn = DBConnection.getConn();
			//2.创建执行对象
			pstmt = conn.prepareStatement(sql);//执行对象功能：负责把sql语句通过conn发送到数据库服务器
			//sql语句如果有参数，需要给参数赋值
			pstmt.setString(1, phone.getName());
			pstmt.setString(2, phone.getSex());
			pstmt.setString(3, phone.getphoneno());	
			pstmt.setInt(4, phone.getPid());
			//3.执行
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.关闭资源
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * 根据id查询
	 */
	@Override
	public Phone getByID(int pid) {
		Phone phone = null;
		Connection conn = null;//连接对象
		PreparedStatement pstmt = null;//执行对象
		ResultSet rs = null;//查询返回结果集
		String sql = "select * from phone where pid=?";//发送的sql语句
		//访问数据库
		try {
			//1.获取连接
			conn = DBConnection.getConn();
			//2.创建执行对象
			pstmt = conn.prepareStatement(sql);//执行对象功能：负责把sql语句通过conn发送到数据库服务器
			//sql语句如果有参数，需要给参数赋值
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();//查询使用executeQuery
			//将结果集转成对象
			if(rs.next()){
				phone  = new Phone();
				phone.setPid(rs.getInt(1));
				phone.setName(rs.getString(2));
				phone.setSex(rs.getString(3));
				phone.setphoneno(rs.getString(4));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.关闭资源
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
			DBConnection.closeRs(rs);
		}
		return phone;
	}
	/**
	 * 条件模糊查询
	 */
	@Override
	public List<Phone> findByConditon(String conditon) {
		List<Phone> list = new ArrayList<Phone>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Phone phone = null;
		String sql = "select * from phone where name like ? or sex like ? or phoneno like ?";
		try {
			conn = DBConnection.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+conditon+"%");
			pstmt.setString(2, "%"+conditon+"%");
			pstmt.setString(3, "%"+conditon+"%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				phone = new Phone();
				phone.setPid(rs.getInt(1));
				phone.setName(rs.getString(2));
				phone.setSex(rs.getString(3));
				phone.setphoneno(rs.getString(4));
				list.add(phone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 无条件查询
	 */
	@Override
	public ArrayList<Phone> select() {
		ArrayList<Phone> list = new ArrayList<Phone>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from phone";	
		try {
			conn = DBConnection.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Phone phone = new Phone(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	/**
	 * 打印电话本
	 */
	@Override
	public boolean writePhonetxt(ArrayList<Phone> list,String path) {
		boolean result = false;
		File f = new File(path);
		try {
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			for(Phone phone:list){
				pw.println(phone.getPid()+","+phone.getName()+","+phone.getSex()+","+phone.getphoneno());
			}
			pw.close();
			fw.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

}
