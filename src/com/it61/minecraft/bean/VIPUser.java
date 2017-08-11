package com.it61.minecraft.bean;

public class VIPUser extends User {

	public VIPUser(String userName, String password, String phoneNumber) {
		super(userName, password, phoneNumber);
		//VIPUser的初始经验值为120，普通User的经验值是30
		setExperience(120);
	}

}
