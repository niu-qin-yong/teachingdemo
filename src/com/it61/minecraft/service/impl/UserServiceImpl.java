package com.it61.minecraft.service.impl;

import java.util.List;

import com.it61.minecraft.bean.User;
import com.it61.minecraft.dao.UserDAO;
import com.it61.minecraft.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void register(User user) throws Exception {
		UserDAO userDAO = new UserDAO();
		userDAO.insert(user);
	}

	@Override
	public User login(String userName, String passWord) {
		UserDAO dao = new UserDAO();
		return  dao.query(userName, passWord);
	}

	@Override
	public void updateInfo(User user) throws Exception {
		UserDAO userDAO = new UserDAO();
		userDAO.update(user);
	}

	@Override
	public List<User> getClassmates(User user) {
		UserDAO userDAO = new UserDAO();
		List<User> classmates = userDAO.queryClassmates(user);
		return classmates;
	}

	@Override
	public List<User> getAllUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.queryAll();
	}

	@Override
	public void addExperience(User user) throws Exception {
		// TODO Auto-generated method stub

	}

}
