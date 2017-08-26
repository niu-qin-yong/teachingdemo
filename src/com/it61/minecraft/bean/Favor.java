package com.it61.minecraft.bean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Favor {
	private int id;
	private int momentId;
	private int favorId;
	private String favorName;
	
	public Favor(){}
	
	public Favor(int momentId, int favorId, String favorName) {
		this.momentId = momentId;
		this.favorId = favorId;
		this.favorName = favorName;
	}
	public Favor(int momentId, int favorId) {
		this.momentId = momentId;
		this.favorId = favorId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMomentId() {
		return momentId;
	}
	public void setMomentId(int momentId) {
		this.momentId = momentId;
	}
	public int getFavorId() {
		return favorId;
	}
	public void setFavorId(int favorId) {
		this.favorId = favorId;
	}
	public String getFavorName() {
		return favorName;
	}
	public void setFavorName(String favorName) {
		this.favorName = favorName;
	}
	
}
