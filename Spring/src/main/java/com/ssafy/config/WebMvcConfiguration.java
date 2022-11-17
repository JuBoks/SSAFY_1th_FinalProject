package com.ssafy.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.interceptor.AdminInterceptor;
import com.ssafy.interceptor.SessionInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	// interceptor 를 적용할 곳을 패턴으로 설정
	private final List<String> sessionIncludePatterns = Arrays.asList("/board/**", "/admin/**", "/user/**");
	private final List<String> sessionExcludePatterns = Arrays.asList("/user/login", "/user/join");
	private final List<String> adminIncludePatterns = Arrays.asList("/admin/**");
	
	@Autowired
	private SessionInterceptor sessionInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
			.maxAge(1800);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 1. session 인터셉터 추가
//		registry.addInterceptor(sessionInterceptor).addPathPatterns(sessionIncludePatterns)
//			.excludePathPatterns(sessionExcludePatterns);
//		
//		// 2. admin 인터셉터 추가
//		registry.addInterceptor(adminInterceptor).addPathPatterns(adminIncludePatterns);
	}

	
	
}
