package com.it61.minecraft.service.impl;

import java.util.List;

import com.it61.minecraft.bean.User;
import com.it61.minecraft.dao.UserDAO;
import com.it61.minecraft.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void register(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public User login(String userName, String passWord) {
		UserDAO dao = new UserDAO();
		return  dao.query(userName, passWord);
	}

	@Override
	public void updateInfo(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getClassmates(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addExperience(User user) throws Exception {
		// TODO Auto-generated method stub

	}

}
