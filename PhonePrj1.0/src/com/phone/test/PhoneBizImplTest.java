package com.phone.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.phone.biz.PhoneBizImpl;
import com.phone.po.Phone;

public class PhoneBizImplTest {
	//���뱻�������
	PhoneBizImpl pbiz = new PhoneBizImpl();
	

	@Test
	public void testAddPhone() {
		Phone p = new Phone("����","��","123456789");//����ҵ�񷽷�
		boolean result =  pbiz.addPhone(p);
		assertEquals(true, result);//����ֵ��ʵ��ֵ
	}

	@Test
	public void testDeletePhone() {
		int pid=1;
		boolean result = pbiz.deletePhone(pid);
		assertEquals(true, result);
	}

	@Test
	public void testUpdatePhone() {
		Phone p = new Phone(1,"����","��","123456789");//����ҵ�񷽷�
		boolean result =  pbiz.addPhone(p);
		assertEquals(true, result);//����ֵ��ʵ��ֵ
	}

	@Test
	public void testGetByID() {
		int pid = 6;
		Phone phone = pbiz.getByID(pid);
		System.out.println(phone);
	}

	@Test
	public void testFindByConditon() {
		String conditon = "��";
		List<Phone> list = new ArrayList<Phone>();
		list = pbiz.findByConditon(conditon);
		for(Phone phone:list){
			System.out.println(phone);
		}
	}

}
