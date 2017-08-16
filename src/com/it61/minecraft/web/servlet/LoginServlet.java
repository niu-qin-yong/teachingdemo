package com.it61.minecraft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it61.minecraft.bean.User;
import com.it61.minecraft.service.UserService;
import com.it61.minecraft.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取表单数据
		String uname = request.getParameter("uname");
		String psw = request.getParameter("pw");
		
		
		System.out.println("Login  name:"+uname+" ps:"+psw);
		
		//调用UserService的登录的方法
		UserService userService = new UserServiceImpl();
		User user = userService.login(uname,psw);
		
		if(user != null){
			//登录成功,重定向到首页
			response.sendRedirect(getServletContext().getContextPath()+"/index.html");
		}else{
			//登录失败
			String text = "login fail,username or password isn't right";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("name:"+uname+"<br/>pwd:"+psw+"<br/>");
			response.getWriter().write(text);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
