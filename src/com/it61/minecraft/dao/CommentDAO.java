package com.it61.minecraft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.it61.minecraft.bean.Comment;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class CommentDAO implements OnTransformListener<Comment> {
	private DAOTemplate<Comment> temp;
	
	public CommentDAO(){
		temp = new DAOTemplate<Comment>();
		temp.setOnTransformListener(this);
	}

	@Override
	public Comment onTransform(ResultSet rs) {
		Comment comment = null;
		try {
			int id = rs.getInt("ID");
			int mid = rs.getInt("comment_moment_id");
			int cid = rs.getInt("comment_user_id");
			String cn = rs.getString("comment_user_name");
			Timestamp timestamp = rs.getTimestamp("comment_send_time");
			String content = rs.getString("comment_content");
			comment = new Comment(id,cid,mid,cn,content,timestamp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public void insert(Comment comment) throws Exception {
		String sql = "insert into mc_comment(comment_moment_id,comment_user_id,comment_user_name,comment_content) values(?,?,?,?)";
		Object[] args = {comment.getMomentId(),comment.getCommenterId(),comment.getCommenterName(),comment.getContent()};
		temp.update(sql, args);
	}

	public Comment findCommentLatest(Integer momentId, Integer commenterId) {
		String sql = "select * from mc_comment where comment_moment_id=? and comment_user_id=? order by comment_send_time desc limit 1";
		Object[] args = {momentId,commenterId};
		return temp.queryOne(sql, args);
	}

	public List<Comment> findAllCommentsByMomentId(Integer momentId) {
		String sql = "select * from mc_comment where comment_moment_id=?";
		Object[] args = {momentId};
		return temp.queryAll(sql, args);
	}
	
}
