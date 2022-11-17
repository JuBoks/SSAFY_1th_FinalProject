//package com.ssafy.favoritearea.controller;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ssafy.favoritearea.model.service.FavoriteAreaService;
//import com.ssafy.user.model.dto.UserDto;
//
//@WebServlet("/favorite")
//public class FavoriteAreaController extends HttpServlet {
//
//	private FavoriteAreaService service = FavoriteAreaService.getInstance();
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String act = req.getParameter("act");
//		String path = "";
//	
//		if ("favoritelist".equals(act)) {
//			path = favoritelist(req, resp);
//			forward(req, resp, path);
//		} else if ("favoriteAdd".equals(act)) {
//			path = doFavoriteAdd(req, resp);
//			forward(req, resp, path);
//		}
//
//
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		doGet(req, resp);
//	}
//
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private String favoritelist(HttpServletRequest req, HttpServletResponse resp) {
//
//		Cookie[] cookies = req.getCookies();
//		for(Cookie c : cookies) {
//			if("addr".equals(c.getName())) {
//				req.setAttribute("addr", c.getValue());
//				break;
//			}
//		}
//		
//		return "/index.jsp";
//	}
//	
//	private UserDto getUserInfoBySession(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Object obj = session.getAttribute("user");
//		if (obj instanceof UserDto) {
//			return (UserDto) obj;
//		}
//		return null;
//	}
//
//	private String doFavoriteAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String formSido = req.getParameter("formSido");
//		String formGugun = req.getParameter("formGugun");
//		String formDong = req.getParameter("formDong");
//		String valSido = req.getParameter("valSido");
//		String valGugun = req.getParameter("valGugun");
//		String valDong = req.getParameter("valDong");
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append(valSido);
//		sb.append(":");
//		sb.append(valGugun);
//		sb.append(":");
//		sb.append(valDong);
//		sb.append(":");
//		sb.append(formSido);
//		sb.append(":");
//		sb.append(formGugun);
//		sb.append(":");
//		sb.append(formDong);
//		sb.append(":");
//		
//		Cookie cookie = new Cookie("addr", sb.toString());
//		cookie.setMaxAge(60 * 60 * 24 * 365);
//		cookie.setPath(req.getContextPath() + "/");
//		
//		resp.addCookie(cookie);
//		
//		req.setAttribute("msg", "관심지역 추가되었습니다.");
//		
//		return "/index.jsp";
//
//	}
//
//}
