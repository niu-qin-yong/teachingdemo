package com.it61.minecraft.bean;

import java.io.InputStream;
import java.sql.Timestamp;

public class User {
	private int id;
	private String userName;
	private String password;
	private String nickName;
	private String gender;
	private int age;
	private int banji;
	private String phoneNumber;
	private String star;
	private String email;
	private int grade;
	private int experience;
	private String birth;
	private InputStream photo;
	private Timestamp registerTime;
	
	/**
	 * 空构造方法
	 */
	public User(){};
	
	/**
	 * 构造方法
	 * @param userName
	 * @param password
	 * @param phoneNumber
	 */
	public User(String userName, String password, String phoneNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public User(int id, String userName, String password, String nickName,
			String gender, int age, int banji, String phoneNumber,
			String star, String email, int grade, int experience,
			String birth, InputStream photo, Timestamp registerTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.gender = gender;
		this.age = age;
		this.banji = banji;
		this.phoneNumber = phoneNumber;
		this.star = star;
		this.email = email;
		this.grade = grade;
		this.experience = experience;
		this.birth = birth;
		this.photo = photo;
		this.registerTime = registerTime;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBanji() {
		return banji;
	}
	public void setBanji(int banji) {
		this.banji = banji;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
}
