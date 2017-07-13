package com.phone.po;

public class Phone {
	private int pid;
	private String name;
	private String sex;
	private String phoneno;
	
	//无参构造方法
	public Phone() {
		super();
	}
	//全参构造
	public Phone(int pid, String name, String sex, String phoneno) {
		super();
		this.pid = pid;
		this.name = name;
		this.sex = sex;
		this.phoneno = phoneno;
	}
	//无主键构造
	public Phone(String name, String sex, String phoneno) {
		super();
		this.name = name;
		this.sex = sex;
		this.phoneno = phoneno;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getphoneno() {
		return phoneno;
	}
	public void setphoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	@Override
	public String toString() {
		return "Phone [pid=" + pid + ", name=" + name + ", sex=" + sex
				+ ", phoneno=" + phoneno + "]";
	}
	
}
