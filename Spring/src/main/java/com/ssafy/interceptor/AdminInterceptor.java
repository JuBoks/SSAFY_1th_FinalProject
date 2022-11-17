package com.ssafy.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.user.model.dto.UserDto;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("진입경로: " + request.getServletPath());
		
		HttpSession session = request.getSession();
		UserDto loginUser = (UserDto) session.getAttribute("loginUser");
		
		if (loginUser.getUserAuth() == 0) {
			return true;  
		}
		else {
			response.sendRedirect(request.getContextPath() + "/index?msg=" + URLEncoder.encode("권한이 없습니다.","UTF-8"));
			return false;
		}
	}

	
}
