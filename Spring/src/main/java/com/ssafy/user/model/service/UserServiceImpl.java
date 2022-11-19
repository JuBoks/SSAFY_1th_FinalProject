package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.model.dao.UserDao;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	private UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

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
	public boolean remove(String userId) throws SQLException {
		return userDao.delete(userId) == 1;
	}

	@Override
	public List<UserDto> getUserList(Map<String, Object> map) throws SQLException {
		Object pgno = map.get("pgno");
		Object key = map.get("key");
		Object word = map.get("word");

		map.put("pgno", pgno == null ? 1 : pgno);
		map.put("key", key == null ? "" : key);
		map.put("word", word == null ? "" : word);
		
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
		System.out.println(userid);
		userDao.saveRefreshToken(map);
		System.out.println("DAO로 출발");
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

}
