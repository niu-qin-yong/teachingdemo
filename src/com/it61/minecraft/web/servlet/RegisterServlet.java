package com.it61.minecraft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it61.minecraft.bean.User;
import com.it61.minecraft.bean.VIPUser;
import com.it61.minecraft.service.UserService;
import com.it61.minecraft.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取表单数据
		//用户名
		String userName = request.getParameter("username");
		//密码
		String password = request.getParameter("password");
		//确认密码
		String repassword = request.getParameter("repassword");
		//电话号码
		String phoneNumber = request.getParameter("phonenumber");
		//邀请码
		String inviteCode = request.getParameter("invitecode");
		
		//打印注册信息
		String info = "userName:"+userName+"<br/>password:"+password+"<br/>repassword:"+repassword+"<br/>phoneNumber:"
				+phoneNumber+"<br/>inviteCode:"+inviteCode;
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("您提交的注册信息<hr/>"+info);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
