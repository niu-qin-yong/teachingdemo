package com.it61.minecraft.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.bean.User;
import com.it61.minecraft.dao.UserDAO;

/**
 *返回图片的流，可用于返回用户图像或者动态的图片，根据type来区分
 */
public class ShowPicServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		
		//设置响应类型
		response.setContentType("image/jpeg,image/png");
		
		//获取图片的二进制流
		BufferedInputStream bis = null;

		//返回用户头像
		if(type.equals("user")){
			UserDAO dao = new UserDAO();
			User user = dao.getUserById(Integer.valueOf(id));
			
			if(user.getPhoto() ==  null){
				//如果用户么有上传头像，则使用默认头像
				InputStream is = getServletContext().getResourceAsStream("/imgs/default-photo.png");
				bis = new BufferedInputStream(is);
				
				//设置响应大小
				response.setContentLength(is.available());
			}else{
				bis = new BufferedInputStream(user.getPhoto());
				
				//设置响应大小
				response.setContentLength(bis.available());
			}
		}else if(type.equals("moment")){
			//返回朋友圈动态图片
/*			MomentDAO dao = new MomentDAO();
			Moment moment = dao.findById(Integer.valueOf(id));
			
			bis = new BufferedInputStream(moment.getPic());
			
			//设置响应大小
			response.setContentLength(bis.available());*/
		}
		
		//将图片二进制流输出到response的输出流
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[4*1024];
		int len = -1;
		while((len=bis.read(buff)) != -1){
			bos.write(buff,0,len);
		}
		
		bos.flush();
		bos.close();
		bis.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

