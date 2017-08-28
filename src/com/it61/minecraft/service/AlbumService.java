package com.it61.minecraft.service;

import java.util.List;

import com.it61.minecraft.bean.Album;
import com.it61.minecraft.bean.Picture;

public interface AlbumService {
	/**
	 * 创建相册
	 * @param album
	 * @throws Exception
	 */
	void addAlbum(Album album)throws Exception;
	/**
	 * 获取最新添加的相册
	 * @return
	 */
	Album getAlbumLatest(int userId);
	/**
	 * 获取某个用户的所有相册
	 * @param userId
	 * @return
	 */
	List<Album> getAllAlbums(int userId);
}
