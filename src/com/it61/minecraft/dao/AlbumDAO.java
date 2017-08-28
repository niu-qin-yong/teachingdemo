package com.it61.minecraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.it61.minecraft.bean.Album;
import com.it61.minecraft.bean.Picture;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class AlbumDAO implements OnTransformListener<Album> {
	private DAOTemplate<Album> temp;
	
	public static void main(String[] args) {
	}
	
	public AlbumDAO(){
		temp = new DAOTemplate<Album>();
		temp.setOnTransformListener(this);
	}

	@Override
	public Album onTransform(ResultSet rs) {
		try {
			int id = rs.getInt("ID");
			int userId = rs.getInt("album_user_id");
			String name = rs.getString("album_name");
			String des = rs.getString("album_des");
			Timestamp createTime = rs.getTimestamp("album_create_time");
			
			return new Album(id, userId, name, des, createTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addAlbum(Album album) throws Exception{
		String sql = "insert into mc_album(album_user_id,album_name,album_des) values(?,?,?)";
		Object[] args = {album.getUserId(),album.getName(),album.getDes()};
		temp.update(sql,args);
	}

	public Album getAlbumLatest(int userId) {
		String sql = "select * from mc_album where album_user_id=? order by album_create_time desc";
		Object[] args = {userId};
		return temp.queryOne(sql,args);
	}

	public List<Album> getAllAlbums(int userId) {
		String sql = "select * from mc_album where album_user_id=?";
		Object[] args = {userId};
		return temp.queryAll(sql,args);
	}
	
}
