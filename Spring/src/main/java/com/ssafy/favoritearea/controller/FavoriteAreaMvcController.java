package com.ssafy.favoritearea.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/favorite")
public class FavoriteAreaMvcController {

	@GetMapping("/list")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			if("addr".equals(c.getName())) {
				mav.setViewName("index");
				mav.addObject("addr", c.getValue());
				break;
			}
		}
		
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		
		String formSido = req.getParameter("formSido");
		String formGugun = req.getParameter("formGugun");
		String formDong = req.getParameter("formDong");
		String valSido = req.getParameter("valSido");
		String valGugun = req.getParameter("valGugun");
		String valDong = req.getParameter("valDong");
		
		StringBuilder sb = new StringBuilder();
		sb.append(valSido);
		sb.append(":");
		sb.append(valGugun);
		sb.append(":");
		sb.append(valDong);
		sb.append(":");
		sb.append(formSido);
		sb.append(":");
		sb.append(formGugun);
		sb.append(":");
		sb.append(formDong);
		sb.append(":");
		
		Cookie cookie = new Cookie("addr", sb.toString());
		cookie.setMaxAge(60 * 60 * 24 * 365);
		cookie.setPath(req.getContextPath() + "/");
		
		resp.addCookie(cookie);
		
		mav.setViewName("index");
		mav.addObject("msg", "관심지역 추가되었습니다.");
		
		return mav;

	}
}
