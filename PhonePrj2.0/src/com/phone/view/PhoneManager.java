package com.phone.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.phone.biz.PhoneBizImpl;
import com.phone.po.Phone;

public class PhoneManager {
	static Scanner sc = new Scanner(System.in);
	//引入业务
	static PhoneBizImpl pbiz = new PhoneBizImpl();
	static List<Phone> list = new ArrayList<Phone>();
	public static void main(String[] args) {
		mainMenu();
	}
	private static void mainMenu() {
		while(true){
			System.out.println("1.增加    2.删除    3.修改    4.查询    5.打印电话薄    6.上传电话薄    0.退出\n请选择业务");
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
			case 6:
				upload();
				break;
			case 0:
				System.exit(0);
				break;
			default:System.out.println("选择有误！");
				break;
			}
		}		
	}
	//上传电话薄
	private static void upload() {
		boolean result = false;
		System.out.println("请输入要上传的文件(例如：D:\\\\phone.txt)");
		String path = sc.next();
		list = pbiz.upload(path);
		for(Phone phone:list){
			Phone p = new Phone(phone.getName(),phone.getSex(),phone.getphoneno());
			result = pbiz.addPhone(p);
		}
		if(result==true){
			System.out.println("上传成功");
		}else{
			System.out.println("上传失败");
		}
	}
	//下载电话薄
	private static void write() {
		List<Phone> list = pbiz.select();
		System.out.println("------电话薄全部信息如下------");
		for(Phone phone:list){
			System.out.println(phone);
		}
		System.out.println("请输入要保存的路径(例如：D:\\\\phone.txt)");
		String path = sc.next();
		boolean result = pbiz.writePhonetxt(list, path);
		if(result==true){
			System.out.println("打印成功");
		}else{
			System.out.println("打印失败");
		}	
	}
	private static void query() {
		System.out.println("----------查询界面----------");
		System.out.println("请输入关键字（全部请输入all）");
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
		System.out.println("----------修改界面----------");
		System.out.println("编号：");
		int pid = sc.nextInt();
		System.out.println("姓名：");
		String name = sc.next();
		System.out.println("性别：");
		String sex = sc.next();
		System.out.println("手机号：");
		String phoneno = sc.next();
		Phone phone = new Phone(pid,name,sex,phoneno);
		boolean result = pbiz.updatePhone(phone);
		if(result == true){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}

	}
	private static void delete() {
		System.out.println("----------删除界面----------");
		System.out.println("编号：");
		int pid = sc.nextInt();
		boolean result  = pbiz.deletePhone(pid);
		if(result == true){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	private static void add() {
		System.out.println("----------增加界面----------");
		System.out.println("姓名：");
		String name = sc.next();
		System.out.println("性别：");
		String sex = sc.next();
		System.out.println("电话：");
		String phoneno = sc.next();
		//组合对象
		Phone phone = new Phone(name,sex,phoneno);
		//调用业务
		boolean result = pbiz.addPhone(phone);
		if(result == true){
			System.out.println("添加成功");
		}else{
			System.out.println("添加失败");
		}
		
	}
}
