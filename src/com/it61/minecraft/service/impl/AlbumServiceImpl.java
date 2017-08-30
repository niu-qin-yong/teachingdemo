package com.it61.minecraft.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.it61.minecraft.bean.Album;
import com.it61.minecraft.bean.Picture;
import com.it61.minecraft.dao.AlbumDAO;
import com.it61.minecraft.service.AlbumService;
import com.it61.minecraft.service.PictureService;


public class AlbumServiceImpl implements AlbumService {
	public static void main(String[] args) {
	}

	@Override
	public void addAlbum(Album album) throws Exception {
		AlbumDAO dao = new AlbumDAO();
		dao.addAlbum(album);
	}

	@Override
	public Album getAlbumLatest(int userId) {
		AlbumDAO dao = new AlbumDAO();
		return dao.getAlbumLatest(userId);
	}

	@Override
	public List<Album> getAllAlbums(int userId) {
		AlbumDAO dao = new AlbumDAO();
		return dao.getAllAlbums(userId);
	}
	
	@Override
	public void addPictures(List<Picture> pics) throws Exception{
		AlbumDAO dao = new AlbumDAO();
		dao.addPictures(pics);
	}
	@Override
	public List<Picture> getBannerPics(int userId,int count) {
		List<Picture> bannerPics = new ArrayList<Picture>();
		
		PictureService picService = new PictureServiceImpl();
		
		//获取所有相册
		List<Album> allAlbums = getAllAlbums(userId);
		//遍历相册获取图片
		for(Album album : allAlbums){
			List<Picture> pictures = picService.getPictures(album);
			//把重复的图片去除
			for(Picture pic : bannerPics){
				if(pictures.contains(pic)){
					pictures.remove(pic);
				}
			}
			//把获取到的图片存起来
			for(int i=0;i<pictures.size();i++){
				bannerPics.add(pictures.get(i));
				//图片个数取够了,跳出循环，不再获取
				if(bannerPics.size() == count){
					break;
				}
			}
		}
		
		return bannerPics;
	}

}
