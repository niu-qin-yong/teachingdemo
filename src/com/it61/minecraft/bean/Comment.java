package com.it61.minecraft.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private int commenterId;
	private int momentId;
	private String commenterName;
	private Timestamp commentTime;
	private String content;
	
	public String getContent() {
		return content;
	}
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(int commenterId) {
		this.commenterId = commenterId;
	}
	public int getMomentId() {
		return momentId;
	}
	public void setMomentId(int momentId) {
		this.momentId = momentId;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
}
