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
	/**
	 * 添加好友
	 * @param owerId
	 * @param friendId
	 * @param friendName
	 * @throws Exception
	 */
	void addFriend(int owerId, int friendId,String friendName)  throws Exception;
	/**
	 * 移除好友
	 * @param friend
	 * @throws Exception
	 */
	void removeFriend(Friend friend)  throws Exception;
}
