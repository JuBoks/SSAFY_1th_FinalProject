//package com.ssafy.apt.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.apt.model.dto.AptDto;
//import com.ssafy.apt.model.service.AptServiceImpl;
//import com.ssafy.util.PageInfo;
//import com.ssafy.util.ParameterCheck;
//
//@WebServlet("/apt")
//public class AptController extends HttpServlet {
//
//	private AptServiceImpl service = AptServiceImpl.getInstance();
//	// 객체를 JSON 문자열로 변경하거나 반대로 JSON 문자열을 객체로 변경해주는 객체
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	private Map<String, Object> map;
//	int pgNo;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		pgNo = ParameterCheck.notNumberToOne(req.getParameter("pgno"));
//		if(pgNo == 0) pgNo = 1;
//
//		map = new HashMap<>();
//		map.put("pgno", pgNo + "");
//		
//		String act = req.getParameter("act");
//		
//		String path = "";
//		Object obj = null;
//		if("list".equals(act)) {
//			obj = doList(req, resp);
//		} else if("registAptByAdmin".equals(act)) {
//			path = doRegistApt(req, resp);
//			forward(req, resp, path);
//		} else if("listAptByAdmin".equals(act)) {
//			path = doSearchApt(req, resp);
//			forward(req, resp, path);
//		} else if("modifyAptByAdmin".equals(act)) {
//			path = doUpdateApt(req, resp);
//			forward(req, resp, path);
//		} else if("removeAptByAdmin".equals(act)) {
//			path = doDeleteApt(req, resp);
//			forward(req, resp, path);
//		}
//		
//		if(obj != null) {
//			// 응답 헤더 작성 (Header)
//			resp.addHeader("Content-Type", "application/json; charset=UTF-8");
//			
//			// 응답 Payload 작성
//			// 1. 객체를 JSON 문자열로 직렬화
//			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
//			
//			// 2. 직렬화 한 문자열을 응답
//			PrintWriter writer = resp.getWriter();
//			writer.write(json);
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		doGet(req, resp);
//	}
//	
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws IOException, ServletException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//	
//	/////////////////////////////////////////////////////
//
//	// 얘만 완료
//	private Object doList(HttpServletRequest req, HttpServletResponse resp) {
//		String dongCode = req.getParameter("dongCode");
//		String dealYear = req.getParameter("dealYear");
//		String dealMonth = req.getParameter("dealMonth");
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("dongCode", dongCode);
//		map.put("dealYear", dealYear);
//		map.put("dealMonth", dealMonth);
//		List<AptDto> list = null;
//		try {
//			list = service.getAptList(map);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
//	
//	private String doRegistApt(HttpServletRequest req, HttpServletResponse resp) {
//		String path = "";
//		
//		AptDto aptDto = new AptDto();
//		aptDto.setAptCode(req.getParameter("aptCode"));
//		aptDto.setAptName(req.getParameter("aptName"));
//		aptDto.setAptBuildYear(Integer.parseInt(req.getParameter("aptBuildYear")));
//		aptDto.setAptRoadName(req.getParameter("aptRoadName"));
//		aptDto.setAptSiGunguCode(req.getParameter("aptSiGunguCode"));
//		aptDto.setAptJibun(req.getParameter("aptJibun"));
//		aptDto.setAptDongCode(req.getParameter("aptDongCode"));
//		aptDto.setAptLng(req.getParameter("aptLng"));
//		aptDto.setAptLat(req.getParameter("aptLat"));
//
//		aptDto.setAptDealAmount(req.getParameter("aptDealAmount"));
//		aptDto.setAptArea(req.getParameter("aptArea"));
//		aptDto.setAptDealYear(Integer.parseInt(req.getParameter("aptDealYear")));
//		aptDto.setAptDealMonth(Integer.parseInt(req.getParameter("aptDealMonth")));
//		aptDto.setAptDealDay(Integer.parseInt(req.getParameter("aptDealDay")));
//		
//		try {
//			int cnt = service.registApt(aptDto);
//			
//			req.setAttribute("msg", cnt + "개가 추가되었습니다.");
//			
//			path = "/apt?act=listAptByAdmin";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			path = "/error/error.jsp";
//		}
//		
//		return path;
//	}
//
//	private String doSearchApt(HttpServletRequest req, HttpServletResponse resp) {
//		String path = "";
//		String sidoCode = req.getParameter("sido");
//		String gugunCode = req.getParameter("gugun");
//		String dongCode = req.getParameter("dong");
//		String aptName = req.getParameter("aptName");
//		
//		try {
//			map.put("dongCode", dongCode);
//			map.put("aptName", aptName);
//			List<AptDto> list = service.searchAptList(map);
//			
//			// 시작/종료 페이지 가져오기
//			int cnt = service.getAptSearchCount(map);
//			PageInfo.setPageInfo(pgNo, cnt);
//			
//			req.setAttribute("aptList", list);
//			req.setAttribute("dongCode", dongCode);
//			req.setAttribute("aptName", aptName);
//			req.setAttribute("startPage", PageInfo.startPage);
//			req.setAttribute("endPage", PageInfo.endPage);
//			req.setAttribute("activePgno", pgNo);
//			
//			path = "/apt/admin-apt-list.jsp";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			path = "/error/error.jsp";
//		}
//		
//		return path;
//	}
//	
//	private String doUpdateApt(HttpServletRequest req, HttpServletResponse resp) {
//		String path = "";
//		
//		AptDto aptDto = new AptDto();
//		aptDto.setAptCode(req.getParameter("aptCode"));
//		aptDto.setAptName(req.getParameter("aptName"));
//		aptDto.setAptBuildYear(Integer.parseInt(req.getParameter("aptBuildYear")));
//
//		aptDto.setAptDealAmount(req.getParameter("aptDealAmount"));
//		aptDto.setAptArea(req.getParameter("aptArea"));
//
//		try {
//			int cnt = service.updateApt(aptDto);
//			
//			req.setAttribute("msg", cnt + "개가 수정되었습니다.");
//			
//			path = "/apt?act=listAptByAdmin";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			path = "/error/error.jsp";
//		}
//		
//		return path;
//	}
//	
//	private String doDeleteApt(HttpServletRequest req, HttpServletResponse resp) {
//		String path = "";
//		
//		String aptCode = req.getParameter("aptCode");
//		
//		try {
//			int cnt = service.deleteApt(aptCode);
//			
//			req.setAttribute("msg", cnt + "개가 삭제되었습니다.");
//			
//			path = "/apt?act=listAptByAdmin";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			path = "/error/error.jsp";
//		}
//		
//		return path;
//	}
//}
