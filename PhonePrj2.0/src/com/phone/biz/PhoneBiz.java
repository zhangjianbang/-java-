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
	public List<Phone> select();
	//��ӡ�绰��
	public boolean writePhonetxt(List<Phone> list,String path);
	//�ϴ��绰��
	public List<Phone> upload(String path);
	
}
