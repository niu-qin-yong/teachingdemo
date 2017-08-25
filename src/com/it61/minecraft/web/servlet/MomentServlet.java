package com.it61.minecraft.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.it61.minecraft.bean.Moment;
import com.it61.minecraft.bean.User;
import com.it61.minecraft.service.MomentService;
import com.it61.minecraft.service.UserService;
import com.it61.minecraft.service.impl.MomentServiceImpl;
import com.it61.minecraft.service.impl.UserServiceImpl;

public class MomentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断form表单的类型是否是 multipart/form-data
		if (ServletFileUpload.isMultipartContent(request)) {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			
			Moment moment = new Moment();
			moment.setSenderId(user.getId());
			moment.setSenderName(user.getUserName());
			moment.setSenderExperience(user.getExperience());
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					//普通表单项
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString("UTF-8");
						System.out.println("name = " + name + " ; value = "+ value);
						
						if(name.equals("moment")){
							moment.setContent(value);
						}
					}else{
						//type 是 file 的表单项
						String name = item.getFieldName();
						String type = item.getContentType();
						System.out.println("name:"+name+",filename = " + item.getName()+","+"size="+item.getSize()+",type:"+type);
						
						//判断类型，保证是图片，且大小不为0
						if((type.equals("image/jpeg") || type.equals("image/png"))
								&& item.getSize() > 0){
							moment.setPicture(item.getInputStream());
						}
						
					}
				}
				
				//调用MomentService发表
				MomentService service = new MomentServiceImpl();
				service.sendMoment(moment);
				
				//获取刚发表的动态返回给界面显示
				Moment latestMoment = service.getMomentLatest(user.getId());
				PropertyFilter filter = new PropertyFilter(){
					
					@Override
					public boolean apply(Object obj, String name, Object value) {
						//返回false表示过滤
						if(name.equals("picture")){
							return false;
						}
						return true;
					}  
					
				};
				String json = JSON.toJSONString(latestMoment,filter,SerializerFeature.WriteDateUseDateFormat); 

				
				response.getWriter().write(json);
			} catch (Exception e) {
				e.printStackTrace();
				//TODO 发表失败
			}
		}		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
