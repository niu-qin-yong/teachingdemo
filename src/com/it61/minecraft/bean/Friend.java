package com.it61.minecraft.bean;

public class Friend {
	private int id;
	private int owerId;
	private int friendId;
	private String friendName;
	
	public Friend(){
		
	}
	
	public Friend(int owerId,int friendId){
		this.owerId = owerId;
		this.friendId = friendId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwerId() {
		return owerId;
	}
	public void setOwerId(int owerId) {
		this.owerId = owerId;
	}
	public int getFriId() {
		return friendId;
	}
	public void setFriId(int friId) {
		this.friendId = friId;
	}
	public String getFriName() {
		return friendName;
	}
	public void setFriName(String friName) {
		this.friendName = friName;
	}
	
}
