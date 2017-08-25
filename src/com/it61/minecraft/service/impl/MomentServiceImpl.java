package com.it61.minecraft.service.impl;

import java.util.List;

import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.dao.MomentDAO;
import com.it61.minecraft.service.MomentService;

public class MomentServiceImpl implements MomentService {

	@Override
	public void sendMoment(Moment moment) throws Exception {
		MomentDAO dao = new MomentDAO();
		dao.insert(moment);
	}

	@Override
	public List<Moment> getMoments(List<Integer> senderIds) {
		MomentDAO dao = new MomentDAO();
		return dao.getMoments(senderIds);
	}
	
	@Override
	public List<Moment> getMomentsPaging(List<Integer> senderIds,String time,int limit){
		MomentDAO dao = new MomentDAO();
		return dao.getMomentsPaging(senderIds,time,limit);
	}

}
