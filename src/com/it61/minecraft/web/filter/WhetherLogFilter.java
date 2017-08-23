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
import javax.servlet.http.HttpSession;

import com.it61.minecraft.bean.User;

/**
 * 
 *
 */
public class WhetherLogFilter implements Filter{
	private String[] excludeUrls;

	@Override
	public void destroy() {
		System.out.println("WhetherLogFilter 销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//默认所有页面都要过滤
		boolean isIncludePage = true;
		//检测目前页面是否需要过滤
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String path = httpReq.getServletPath();
		for(String page : excludeUrls){
			if(path.equals(page)){
				isIncludePage = false;
				break;
			}
		}
		
		//规则内的页面检测是否登录,规则外的页面直接执行其逻辑
		//如果用户已经掉线，重定向到登录界面让其重新登录
		if(isIncludePage){
			HttpSession session = httpReq.getSession(false);
			if(session == null || session.getAttribute("user") == null){
				System.out.println("WhetherLogFilter：用户正在访问 "+path+",未登录，跳转到登录页面");
				
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				String host = request.getServletContext().getContextPath();
				//交给 index.html 处理(已登录，显示首页；没有登录，去登录页面)
				httpResponse.sendRedirect(host+"/index.html");
				return;
			}
		}
		
		System.out.println("WhetherLogFilter：用户正在访问 "+path+",已登录或将要去登录，正常访问");
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("WhetherLogFilter 初始化");
		String excludePages = config.getInitParameter("excludedPages");
		//不过滤的页面之间用逗号隔开
		excludeUrls = excludePages.split(",");
	}

}
