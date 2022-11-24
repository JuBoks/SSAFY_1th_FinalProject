package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.favoritearea.model.dao.FavoriteAreaDao;
import com.ssafy.user.model.dao.TmpNumDao;
import com.ssafy.user.model.dao.UserDao;
import com.ssafy.user.model.dto.TmpNumDto;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TmpNumDao tmpNumDao;
	@Autowired
	private FavoriteAreaDao favoriteAreaDao;
	@Autowired
	private TmpNumService tmpNumService;

	@Override
	public boolean join(UserDto userDto) throws Exception {
		return userDao.insert(userDto) == 1;
	}
	
	@Override
	public UserDto login(UserDto userDto) throws SQLException {
		return userDao.login(userDto);
	}

	@Override
	public UserDto getDetail(String id) throws SQLException {
		return userDao.select(id);
	}

	@Override
	public boolean modify(UserDto userDto) throws SQLException {
		return userDao.update(userDto) == 1;
	}

	@Override
	@Transactional
	public boolean remove(String userId) throws SQLException {
		tmpNumDao.delete(userId);
		favoriteAreaDao.deleteByUser(userId);
		return userDao.delete(userId) == 1;
	}

	@Override
	public List<UserDto> getUserList(Map<String, Object> map) throws SQLException {
		Object pgno = map.get("pgno");
		Object key = map.get("key");
		Object word = map.get("word");

		map.put("pgno", pgno == null ? 1 : pgno);
		
		int pgNo = Integer.parseInt(map.get("pgno")+"");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		
		map.put("start", start);
		map.put("spl", SizeConstant.SIZE_PER_LIST);
		
		return userDao.selectAll(map);
	}

	@Override
	public int getUserCount() throws SQLException {
		return userDao.selectCount();
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, Object> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.SIZE_PER_LIST;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno") + "");

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("word"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		
		int totalCount = userDao.getCountUserList(param);
		
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return userDao.userInfo(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws SQLException {
		return userDao.getRefreshToken(userId);
	}


	@Override
	public void insertTmpNumAndSendEmail(UserDto userDto) throws Exception {
		// 1. 인증번호 생성
		String tmpNum = randNum();
		
		// 2. 인증번호 DB에 삽입
		TmpNumDto tmpNumDto = new TmpNumDto(userDto.getUserId(), tmpNum);
		boolean isInserted = tmpNumService.insert(tmpNumDto);
		
		if(isInserted) {
			sendEmail(userDto, tmpNum);
		} else {
			throw new Exception();
		}
	}
	
	private void sendEmail(UserDto userDto, String tmpNum) throws Exception {
		String userId = userDto.getUserId();
		String userEmail = userDto.getUserAddr();
		
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "ptjtest1017@naver.com";
		String hostSMTPpwd = "wogh1017";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "ptjtest1017@naver.com";
		String fromName = "ptjtest1017";
		String subject = "[구해줘홈즈] 임시 비밀번호 입니다.";
		String msg = "";

		subject = "구해줘 홈즈! 인증번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += userId + "님의 인증번호 입니다.</h3>";
		msg += "<p>인증번호 : ";
		msg += tmpNum + "</p></div>";

		// 받는 사람 E-Mail 주소
		String mail = userEmail;
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(false);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); //네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(false);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
	
	private String randNum() {
		Random random = new Random();		//랜덤 함수 선언
		int createNum = 0;  			//1자리 난수
		String ranNum = ""; 			//1자리 난수 형변환 변수
        int letter    = 6;			//난수 자릿수:6
		String resultNum = "";  		//결과 난수
		
		for (int i=0; i<letter; i++) { 
			createNum = random.nextInt(9);		//0부터 9까지 올 수 있는 1자리 난수 생성
			ranNum =  Integer.toString(createNum);  //1자리 난수를 String으로 형변환
			resultNum += ranNum;			//생성된 난수(문자열)을 원하는 수(letter)만큼 더하며 나열
		}	
        	
    	return resultNum;
	}

	@Override
	public boolean modifyPwd(Map<String, String> param) throws Exception {
		return userDao.updatePwd(param) == 1;
	}


}
