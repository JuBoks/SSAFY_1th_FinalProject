package com.ssafy.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("진입경로123: " + request.getServletPath());
		
		HttpSession session = request.getSession();
		
		// 세션에 로그인 정보가 있다면 그대로 진행한다.
		if (session.getAttribute("loginUser") != null) {
			return true;  // 컨트롤러에게 요청 정보(객체)를 그대로 전달한다.
		}
		else {
			// 로그인 정보가 세션에 없다면 index 페이지로 리다이렉트 한다.
			response.sendRedirect(request.getContextPath() + "/alert?msg=" + URLEncoder.encode("로그인이 필요한 서비스입니다.","UTF-8"));
			return false;
		}
	}
	
}
