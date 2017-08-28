package com.it61.minecraft.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.it61.minecraft.bean.Comment;
import com.it61.minecraft.service.CommentService;
import com.it61.minecraft.service.impl.CommentServiceImpl;

public class CommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int momentId = Integer.valueOf(request.getParameter("momentId"));
		int commenterId = Integer.valueOf(request.getParameter("commenterId"));
		String commenterName = request.getParameter("commenterName");
		String content = request.getParameter("content");
		
		try {
			CommentService service = new CommentServiceImpl();
			service.addComment(new Comment(commenterId, momentId, commenterName, content));
			
			Comment comment = service.getCommentLatest(momentId, commenterId);
			//设置时间格式
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss"; 
			String json = JSON.toJSONString(comment,SerializerFeature.WriteDateUseDateFormat);
			
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			
			response.getWriter().write("comment-add-failure");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
