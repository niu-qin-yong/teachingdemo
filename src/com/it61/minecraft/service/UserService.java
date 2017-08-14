package com.it61.minecraft.service;

import java.util.List;

import com.it61.minecraft.bean.User;

public interface UserService {
	/**
	 * 注册新用户
	 * @param user
	 * @throws Exception
	 */
	void register(User user) throws Exception;
	/**
	 * 用户登录，用户名密码正确返回该用户user，否则返回null
	 * @param userName
	 * @param passWord
	 * @return
	 */
	User login(String userName,String passWord);
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	void updateInfo(User user) throws Exception; 
	/**
	 * 获取用户user的同班同学
	 * @param user
	 * @return
	 */
	List<User> getClassmates(User user);
	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getAllUsers();
	/**
	 * 用户打卡后，增加经验值
	 * @param user
	 */
	void addExperience(User user)throws Exception;
}
