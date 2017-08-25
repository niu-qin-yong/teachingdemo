package com.it61.minecraft.web.listener;

import java.util.ArrayList;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.it61.minecraft.bean.User;

public class WebContextListener implements ServletContextListener,ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextListenerImpl contextDestroyed");
		ArrayList<User> onlineUsers = (ArrayList<User>) sce.getServletContext().getAttribute("online_users");
		onlineUsers.clear();
		onlineUsers = null;		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextListenerImpl contextInitialized");
		sce.getServletContext().setAttribute("online_users", new ArrayList<User>());		
	}

}
