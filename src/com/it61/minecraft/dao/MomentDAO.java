package com.it61.minecraft.dao;

import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class MomentDAO implements OnTransformListener<Moment>{
	private DAOTemplate<Moment> temp;
	
	//测试方法
	public static void main(String[] args) {
		MomentDAO dao = new MomentDAO();
		List<Integer> senderIds = new ArrayList<Integer>();
		senderIds.add(4);
		List<Moment> momentsPaging = dao.getMomentsPaging(senderIds, "2017-08-25 12:30:59", 5);
		System.out.println(momentsPaging.size());
	}
	
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

	public List<Moment> getMoments(List<Integer> senderIds) {
		String sql = "select * from mc_moment where moment_sender_id in (";
		Object[] args = senderIds.toArray();
		for(int i=0;i<senderIds.size();i++){
			sql+="?,";
		}
		//去掉最后多余的逗号
		sql = sql.substring(0, sql.length()-1);
		sql += ")";
		//按时间倒序排序
		sql+=" order by moment_send_time desc";
		
		return temp.queryAll(sql, args);
	}
	
	/**
	 * 根据动态id返回动态
	 * @param momentId
	 * @return
	 */
	public Moment findById(Integer momentId) {
		String sql = "select * from mc_moment where ID=?";
		Object[] args = {momentId};
		return temp.queryOne(sql, args);
	}
	
	/**
	 * 分页查询获取动态
	 * @param senderIds
	 * @param time	
	 * @param limit	
	 * @return 返回 senderIds 发表的动态中发表时间在 time 之前的 limit 条记录
	 */
	public List<Moment> getMomentsPaging(List<Integer> senderIds,String time,int limit){
		String sql = "select * from mc_moment where moment_sender_id in (";
		Object[] args = senderIds.toArray();
		for(int i=0;i<senderIds.size();i++){
			sql+="?,";
		}
		//去掉最后多余的逗号
		sql = sql.substring(0, sql.length()-1);
		sql += ")";
		//按时间倒序排序
		sql+="and moment_send_time < '"+time+"' order by moment_send_time desc limit "+limit;;
		
		return temp.queryAll(sql, args);
	}
}
