package com.xm.xmstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xm.xmstore.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//创建拦截对象
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		//白名单
		List<String> patterns = new ArrayList<>();
		patterns.add("/css/**");
		patterns.add("/image/**");
		patterns.add("/js/**");
		
		patterns.add("/xweb/login.html");
		patterns.add("/xweb/register.html");
		patterns.add("/xweb/index.html");
		patterns.add("/xweb/goods.html");
		patterns.add("/xweb/cart.html");
		
		
		patterns.add("/users/**");
		patterns.add("/districts/**");
		//注册拦截器
		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns); 
	}
		
	
}
