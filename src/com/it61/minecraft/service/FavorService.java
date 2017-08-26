package com.it61.minecraft.service;

import java.util.List;

import com.it61.minecraft.bean.Favor;

public interface FavorService {
	/**
	 * 添加点赞
	 * @param favor
	 * @throws Exception
	 */
	void addFavor(Favor favor)throws Exception;
	/**
	 * 取消点赞
	 * @param favor
	 * @throws Exception
	 */
	void removeFavor(Favor favor)throws Exception;
	/**
	 * 获取一条动态的点赞信息
	 * @param momentId
	 * @return
	 */
	List<Favor> getAllFavorsByMomentId(Integer momentId);

}
