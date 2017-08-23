package com.it61.minecraft.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it61.minecraft.bean.User;

public class CharactorFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("CharactorFilter----过滤器销毁----");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String path = httpReq.getServletPath();
		
		//设置解析请求参数的编码集
		request.setCharacterEncoding("UTF-8");
		//设置响应数据的编码集，并告知浏览器解码方式
		response.setContentType("text/html;charset=UTF-8");
		
		chain.doFilter(request, response);  //让目标资源执行，放行
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("CharactorFilter----过滤器初始化----");
	}

}
