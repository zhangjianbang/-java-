package com.phone.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.phone.dao.PhoneDao;
import com.phone.po.Phone;

public class PhoneBizImpl implements PhoneBiz {
	//引入Dao对象
	PhoneDao pdao = new PhoneDao();

	@Override
	public boolean addPhone(Phone phone) {
		String sql = "insert into phone values(seq_pid.nextval,?,?,?)";
		Object[] params = {phone.getName(),phone.getSex(),phone.getphoneno()};
		return pdao.update(sql, params);
	}

	@Override
	public boolean deletePhone(int pid) {
		String sql = "delete phone where pid=?";
		Object[] params = {pid};
		return pdao.update(sql, params);
	}

	@Override
	public boolean updatePhone(Phone phone) {
		String sql = "update phone set name=?, sex=?, phoneno=? where pid=?";
		Object[] params = {phone.getName(),phone.getSex(),phone.getphoneno(),phone.getPid()};
		return pdao.update(sql, params);
	}

	@Override
	public Phone getByID(int pid) {
		String sql = "select * from phone where pid=?";
		Object[]params = {pid};
		return pdao.get(sql, Phone.class, params);
	}

	@Override
	public List<Phone> findByConditon(String conditon) {
		String sql = "select * from phone where name like ? or sex like ? or phoneno like ?";
		Object[] params={"%"+conditon+"%","%"+conditon+"%","%"+conditon+"%"};
		return pdao.query(sql, Phone.class, params);
	}

	@Override
	public List<Phone> select() {
		String sql = "select * from phone";
		return pdao.query(sql, Phone.class, null);
	}

	@Override
	public boolean writePhonetxt(List<Phone> list, String path) {
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

	@Override
	public List<Phone> upload(String path) {
		List<Phone> list = new ArrayList<Phone>();
		File f = new File(path);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			while(true){
				String str = bf.readLine();
				if(str==null){
					break;
				}
				String[] strlist = str.split(",");
				Phone phone = new Phone(Integer.parseInt(strlist[0]),strlist[1],strlist[2],strlist[3]);
				list.add(phone);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
