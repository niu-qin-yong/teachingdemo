package com.it61.minecraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.it61.minecraft.bean.Favor;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class FavorDAO implements OnTransformListener<Favor> {
	private DAOTemplate<Favor> temp;
	
	public FavorDAO(){
		temp = new DAOTemplate<Favor>();
		temp.setOnTransformListener(this);
	}

	@Override
	public Favor onTransform(ResultSet rs) {
		Favor favor = null;
		try {
			int id = rs.getInt("ID");
			int mid = rs.getInt("favor_moment_id");
			int fid = rs.getInt("favor_user_id");
			String fn = rs.getString("favor_user_name");
			favor = new Favor(mid,fid,fn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favor;
	}
	
	public void delete(Favor favor) throws Exception{
		String sql = "delete from mc_favor where favor_moment_id=? and favor_user_id=?";
		Object[] args = {favor.getMomentId(),favor.getFavorId()};
		temp.update(sql, args);
	}

	public void insert(Favor favor) throws Exception {
		String sql = "insert into mc_favor(favor_moment_id,favor_user_id,favor_user_name) values(?,?,?)";
		Object[] args = {favor.getMomentId(),favor.getFavorId(),favor.getFavorName()};
		temp.update(sql, args);
	}

	public List<Favor> findAllFavorsByMomentId(Integer momentId) {
		String sql = "select * from mc_favor where favor_moment_id=?";
		Object[] args = {momentId};
		return temp.queryAll(sql, args);
	}
}
