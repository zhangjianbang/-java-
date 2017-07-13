package com.phone.biz;

import java.util.ArrayList;
import java.util.List;

import com.phone.po.Phone;

public interface PhoneBiz {
	//����
	public boolean addPhone(Phone phone);
	//ɾ��
	public boolean deletePhone(int pid);
	//�޸�
	public boolean updatePhone(Phone phone);
	//����
	public Phone getByID(int pid);
	//�����������Ҷ������
	public List<Phone> findByConditon(String Conditon);
	//��������ѯ
	public ArrayList<Phone> select();
	//��ӡ�绰����Ϣ
	public boolean writePhonetxt(ArrayList<Phone> list,String path);
	
}
