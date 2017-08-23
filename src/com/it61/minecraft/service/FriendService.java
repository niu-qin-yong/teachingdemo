package com.it61.minecraft.service;

import java.util.List;

import com.it61.minecraft.bean.Friend;
import com.it61.minecraft.bean.User;

public interface FriendService {
	/**
	 * 获取用户user的所有好友
	 * @param user
	 * @return
	 */
	List<Friend> getAllFriends(User user);
}
