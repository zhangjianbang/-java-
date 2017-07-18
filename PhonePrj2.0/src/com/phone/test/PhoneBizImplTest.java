package com.phone.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Test;

import com.phone.biz.PhoneBizImpl;
import com.phone.po.Phone;

public class PhoneBizImplTest {
	PhoneBizImpl pb = new PhoneBizImpl();

	@Test
	public void testAddPhone() {
		Phone phone  = new Phone("�Ž���","��","123456");
		boolean result = pb.addPhone(phone);
		assertEquals(true, result);
	}

	@Test
	public void testDeletePhone() {
		boolean result =  pb.deletePhone(21);
		assertEquals(true, result);
	}

	@Test
	public void testUpdatePhone() {
		Phone phone = new Phone(21,"�Ų���","��","123456");
		boolean result = pb.updatePhone(phone);
		assertEquals(true, result);
	}

	@Test
	public void testGetByID() {
		Phone phone = pb.getByID(21);
		System.out.println(phone);
	}

	@Test
	public void testFindByConditon() {
		List<Phone> list = pb.findByConditon("��");
		for(Phone phone:list){
			System.out.println(phone);
		}
	}

	@Test
	public void testSelect() {
		List<Phone> list = pb.select();
		for(Phone phone:list){
			System.out.println(phone);
		}
	}

	@Test
	public void testWritePhonetxt() {
		fail("Not yet implemented");
	}

}
