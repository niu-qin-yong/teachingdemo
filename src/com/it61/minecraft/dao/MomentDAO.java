package com.it61.minecraft.dao;

import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class MomentDAO implements OnTransformListener<Moment>{
	private DAOTemplate<Moment> temp;
	
	public MomentDAO(){
		temp = new DAOTemplate<Moment>();
		temp.setOnTransformListener(this);
	}

	public void insert(Moment moment) throws Exception {
		String sql = "insert into mc_moment(moment_sender_id,moment_sender_name,moment_content,moment_picture,moment_sender_experience) values(?,?,?,?,?)";
		Object[] args = {moment.getSenderId(),moment.getSenderName(),moment.getContent(),moment.getPicture(),moment.getSenderExperience()};
		temp.update(sql, args);
	}

	@Override
	public Moment onTransform(ResultSet rs) {
		try {
			int id = rs.getInt("ID");
			int sId = rs.getInt("moment_sender_id");
			int sLevel = rs.getInt("moment_sender_experience");
			InputStream pic = rs.getBinaryStream("moment_picture");
			String sName = rs.getString("moment_sender_name");
			String content = rs.getString("moment_content");
			Timestamp stamp = rs.getTimestamp("moment_send_time");
			
			return new Moment(id,sId,sName,content,pic,stamp,sLevel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
