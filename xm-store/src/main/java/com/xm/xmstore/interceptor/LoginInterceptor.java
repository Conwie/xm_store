package com.xm.xmstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * @author soft01
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取httpSession对象
		HttpSession session = request.getSession();
		//判断session是否有登录信息
		if(session.getAttribute("uid")==null) {
			//System.err.println("LoginInterceptor...");
			//没有登录信息,则重定向到登录页面
			response.sendRedirect("/xweb/login.html");
			
			return false;
		}	
		return true;
	}
	
	
}
