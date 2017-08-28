package com.it61.minecraft.service;

import java.util.List;

import com.it61.minecraft.bean.Comment;

public interface CommentService {
	/**
	 * 添加新留言
	 * @param comment
	 * @throws Exception
	 */
	void addComment(Comment comment)throws Exception;
	/**
	 * 返回最新的一条留言
	 * @param momentId
	 * @param commenterId
	 * @return
	 * @throws Exception
	 */
	Comment getCommentLatest(Integer momentId,Integer commenterId)throws Exception;
	/**
	 * 获取某条动态的评论
	 * @param momentId
	 * @param commenterId
	 * @return
	 * @throws Exception
	 */
	List<Comment> getAllCommentsByMomentId(Integer momentId);
}
