package com.it61.minecraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.it61.minecraft.bean.Album;
import com.it61.minecraft.bean.Picture;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class PictureDAO implements OnTransformListener<Picture> {
	private DAOTemplate<Picture> temp;
	
	public PictureDAO(){
		temp = new DAOTemplate<Picture>();
		temp.setOnTransformListener(this);
	}
	
	public static void main(String[] args) {
		
	}
	
	
	@Override
	public Picture onTransform(ResultSet rs) {
		try {
			int id = rs.getInt("ID");
			int userId = rs.getInt("picture_user_id");
			int albumId = rs.getInt("picture_album_id");
			String picName = rs.getString("picture_name");
			
			return new Picture(id,userId,albumId,picName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Picture> getPictures(Album album) {
		String sql = "select * from mc_picture where picture_user_id=? and picture_album_id=?";
		Object[] args = {album.getUserId(),album.getId()};
		return temp.queryAll(sql,args);
	}
	
	public List<Picture> getPictures(int userId,int albumId) {
		String sql = "select * from mc_picture where picture_user_id=? and picture_album_id=?";
		Object[] args = {userId,albumId};
		return temp.queryAll(sql,args);
	}
	
	public List<Picture> getPictures(Album album,int size) {
		String sql = "select * from mc_picture where picture_user_id=? and picture_album_id=? limit ?";
		Object[] args = {album.getUserId(),album.getId(),size};
		return temp.queryAll(sql,args);
	}
}
