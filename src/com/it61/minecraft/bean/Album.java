package com.it61.minecraft.bean;

import java.sql.Timestamp;
import java.util.List;

public class Album {

	private int id;
	private int userId;
	private String name;
	private String des;
	private Timestamp createTime;
	private List<Picture> pics;
	
	public Album(){}
	
	public Album(int id, int userId, String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
	}
	
	public Album(int id, int userId, String name, String des,
			Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.des = des;
		this.createTime = createTime;
	}
	public Album(int userId, String albumName, String albumDes) {
		this.userId = userId;
		this.name = albumName;
		this.des = albumDes;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public List<Picture> getPics() {
		return pics;
	}
	public void setPics(List<Picture> pics) {
		this.pics = pics;
	}
}
