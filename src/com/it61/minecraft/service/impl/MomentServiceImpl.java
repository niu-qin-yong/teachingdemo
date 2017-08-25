package com.it61.minecraft.service.impl;

import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.dao.MomentDAO;
import com.it61.minecraft.service.MomentService;

public class MomentServiceImpl implements MomentService {

	@Override
	public void sendMoment(Moment moment) throws Exception {
		MomentDAO dao = new MomentDAO();
		dao.insert(moment);
	}

}
