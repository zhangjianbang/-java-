package com.phone.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.phone.biz.PhoneBizImpl;
import com.phone.po.Phone;

public class PhoneManager {
	static Scanner sc = new Scanner(System.in);
	//����ҵ��
	static PhoneBizImpl pbiz = new PhoneBizImpl();
	static ArrayList<Phone> list = new ArrayList<Phone>();
	public static void main(String[] args) {
		mainMenu();
	}
	private static void mainMenu() {
		while(true){
			System.out.println("1.����    2.ɾ��    3.�޸�    4.��ѯ    5.��ӡ�绰��    0.�˳�\n��ѡ��ҵ��");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				add();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				query();
				break;
			case 5:
				write();
				break;
			case 0:
				System.exit(0);
				break;
			default:System.out.println("ѡ������");
				break;
			}
		}
		
	}
	private static void write() {
		ArrayList<Phone> list = pbiz.select();
		System.out.println("------�绰��ȫ����Ϣ����------");
		for(Phone phone:list){
			System.out.println(phone);
		}
		System.out.println("������Ҫ�����·��(���磺D:\\\\phone.txt)");
		String path = sc.next();
		boolean result = pbiz.writePhonetxt(list, path);
		if(result==true){
			System.out.println("��ӡ�ɹ�");
		}else{
			System.out.println("��ӡʧ��");
		}
		
		
	}
	private static void query() {
		System.out.println("----------��ѯ����----------");
		System.out.println("������ؼ��֣�ȫ��������all��");
		String condition = sc.next();
		if(condition.equals("all")){
			condition = "";
		}
		List<Phone> list = pbiz.findByConditon(condition);
		for(Phone phone:list){
			System.out.println(phone);
		}
		
	}
	private static void update() {
		System.out.println("----------�޸Ľ���----------");
		System.out.println("��ţ�");
		int pid = sc.nextInt();
		System.out.println("������");
		String name = sc.next();
		System.out.println("�Ա�");
		String sex = sc.next();
		System.out.println("�ֻ��ţ�");
		String phoneno = sc.next();
		Phone phone = new Phone(pid,name,sex,phoneno);
		boolean result = pbiz.updatePhone(phone);
		if(result == true){
			System.out.println("���³ɹ�");
		}else{
			System.out.println("����ʧ��");
		}

	}
	private static void delete() {
		System.out.println("----------ɾ������----------");
		System.out.println("��ţ�");
		int pid = sc.nextInt();
		boolean result  = pbiz.deletePhone(pid);
		if(result == true){
			System.out.println("ɾ���ɹ�");
		}else{
			System.out.println("ɾ��ʧ��");
		}
	}
	private static void add() {
		System.out.println("----------���ӽ���----------");
		System.out.println("������");
		String name = sc.next();
		System.out.println("�Ա�");
		String sex = sc.next();
		System.out.println("�绰��");
		String phoneno = sc.next();
		//��϶���
		Phone phone = new Phone(name,sex,phoneno);
		//����ҵ��
		boolean result = pbiz.addPhone(phone);
		if(result == true){
			System.out.println("��ӳɹ�");
		}else{
			System.out.println("���ʧ��");
		}
		
	}
}
