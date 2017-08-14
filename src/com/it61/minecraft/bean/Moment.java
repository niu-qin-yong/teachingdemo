package com.it61.minecraft.bean;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Moment {
	private int id;
	private String content;
	private InputStream picture;
	private int senderId;
	private String senderName;
	private Timestamp sendTime;
	private int senderExperience;
	private List<Favor> favors;
	private List<Comment> comments;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public InputStream getPicture() {
		return picture;
	}

	public void setPicture(InputStream picture) {
		this.picture = picture;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public int getSenderExperience() {
		return senderExperience;
	}

	public void setSenderExperience(int senderExperience) {
		this.senderExperience = senderExperience;
	}
	public List<Favor> getFavors() {
		return favors;
	}
	public void setFavors(List<Favor> favors) {
		this.favors = favors;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
