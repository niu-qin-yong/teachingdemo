package com.it61.minecraft.bean;

public class Music {
	private int id;
	//音乐的路径
	private String audio;
	//海报
	private String poster;
	private String title;
	private String singer;
	private int likeCount;
	private int serverSide;
	private int userId;
	
	public int getServerSide() {
		return serverSide;
	}

	public void setServerSide(int serverSide) {
		this.serverSide = serverSide;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Music(){
		
	}
	
	public Music(int id, String audio, String poster, String title,
			String singer, int likeCount, int serverSide,int userId) {
		super();
		this.id = id;
		this.audio = audio;
		this.poster = poster;
		this.title = title;
		this.singer = singer;
		this.likeCount = likeCount;
		this.serverSide = serverSide;
		this.userId = userId;
	}




	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusic() {
		return audio;
	}
	public void setMusic(String music) {
		this.audio = music;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
