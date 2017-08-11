package com.it61.minecraft.bean;

import java.sql.Timestamp;

public class Sign {
	private int id;
	private int userId;
	private Timestamp signTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return userId;
	}
	public void setUser_id(int user_id) {
		this.userId = user_id;
	}
	public Timestamp getSign_time() {
		return signTime;
	}
	public void setSign_time(Timestamp sign_time) {
		this.signTime = sign_time;
	}
}
