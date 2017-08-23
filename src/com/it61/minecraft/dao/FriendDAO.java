package com.it61.minecraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.it61.minecraft.bean.Friend;
import com.it61.minecraft.bean.User;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class FriendDAO implements OnTransformListener<Friend>{
	private DAOTemplate<Friend> temp;
	
	public FriendDAO(){
		temp = new DAOTemplate<Friend>();
		temp.setOnTransformListener(this);
	}

	@Override
	public Friend onTransform(ResultSet rs) {
		Friend friend = null;
		try {
			friend = new Friend();
			friend.setId(rs.getInt("ID"));
			friend.setOwerId(rs.getInt("user_id"));
			friend.setFriId(rs.getInt("friend_user_id"));
			friend.setFriName(rs.getString("friend_user_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friend;
	}
	

	public List<Friend> queryAllFriends(User user) {
		String sql = "select * from mc_friend where user_id=?";
		Object[] args = {user.getId()};
		return temp.queryAll(sql, args);
	}
}
