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
		
		//将数据封装到JavaBean
		User user = new User(userName,password,phoneNumber);
		
		//调用UserService的注册的方法
		try {
			UserService userService = new UserServiceImpl();
			userService.register(user);
			
			//注册成功，重定向到登录页面
			String application = getServletContext().getContextPath();
			response.sendRedirect(application+"/login.html");
		} catch (Exception e) {
			e.printStackTrace();
			//TODO 注册失败
			response.getWriter().write("抱歉，注册失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
