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
	 * ����id��ѯ����
	 */
	@Override
	public boolean addPhone(Phone phone) {
		boolean result = false;
		Connection conn = null;//���Ӷ���
		PreparedStatement pstmt = null;//ִ�ж���
		String sql = "insert into phone values(seq_pid.nextval,?,?,?)";//���͵�sql���
		//�������ݿ�
		try {
			//1.��ȡ����
			conn = DBConnection.getConn();
			//2.����ִ�ж���
			pstmt = conn.prepareStatement(sql);//ִ�ж����ܣ������sql���ͨ��conn���͵����ݿ������
			//sql�������в�������Ҫ��������ֵ
			pstmt.setString(1, phone.getName());
			pstmt.setString(2, phone.getSex());
			pstmt.setString(3, phone.getphoneno());			
			//3.ִ��
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.�ر���Դ
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * ����idɾ������
	 */
	@Override
	public boolean deletePhone(int pid) {
		boolean result = false;
		Connection conn = null;//���Ӷ���
		PreparedStatement pstmt = null;//ִ�ж���
		String sql = "delete phone where pid=?";//���͵�sql���
		//�������ݿ�
		try {
			//1.��ȡ����
			conn = DBConnection.getConn();
			//2.����ִ�ж���
			pstmt = conn.prepareStatement(sql);//ִ�ж����ܣ������sql���ͨ��conn���͵����ݿ������
			//sql�������в�������Ҫ��������ֵ
			pstmt.setInt(1, pid);	
			//3.ִ��
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.�ر���Դ
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * ����pid��������
	 */
	@Override
	public boolean updatePhone(Phone phone) {
		boolean result = false;
		Connection conn = null;//���Ӷ���
		PreparedStatement pstmt = null;//ִ�ж���
		String sql = "update phone set name=?, sex=?, phoneno=? where pid=?";//���͵�sql���
		//�������ݿ�
		try {
			//1.��ȡ����
			conn = DBConnection.getConn();
			//2.����ִ�ж���
			pstmt = conn.prepareStatement(sql);//ִ�ж����ܣ������sql���ͨ��conn���͵����ݿ������
			//sql�������в�������Ҫ��������ֵ
			pstmt.setString(1, phone.getName());
			pstmt.setString(2, phone.getSex());
			pstmt.setString(3, phone.getphoneno());	
			pstmt.setInt(4, phone.getPid());
			//3.ִ��
			int row = pstmt.executeUpdate();
			if(row>0){
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//4.�ر���Դ
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	/**
	 * ����id��ѯ
	 */
	@Override
	public Phone getByID(int pid) {
		Phone phone = null;
		Connection conn = null;//���Ӷ���
		PreparedStatement pstmt = null;//ִ�ж���
		ResultSet rs = null;//��ѯ���ؽ����
		String sql = "select * from phone where pid=?";//���͵�sql���
		//�������ݿ�
		try {
			//1.��ȡ����
			conn = DBConnection.getConn();
			//2.����ִ�ж���
			pstmt = conn.prepareStatement(sql);//ִ�ж����ܣ������sql���ͨ��conn���͵����ݿ������
			//sql�������в�������Ҫ��������ֵ
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();//��ѯʹ��executeQuery
			//�������ת�ɶ���
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
			//4.�ر���Դ
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConn(conn);
			DBConnection.closeRs(rs);
		}
		return phone;
	}
	/**
	 * ����ģ����ѯ
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
	 * ��������ѯ
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
	 * ��ӡ�绰��
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
