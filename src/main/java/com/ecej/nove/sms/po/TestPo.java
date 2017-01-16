package com.ecej.nove.sms.po;

import java.io.Serializable;

public class TestPo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7890593427037688485L;

	private String name;

	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: " + this.name + "age :" + this.age;
	}

}
