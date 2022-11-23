package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.PageNavigation;

public interface UserService {

	boolean join(UserDto userDto) throws Exception;

	UserDto login(UserDto userDto) throws SQLException;

	UserDto getDetail(String id) throws SQLException;

	boolean modify(UserDto userDto) throws SQLException;

	boolean remove(String userId) throws SQLException;

	public List<UserDto> getUserList(Map<String, Object> map) throws SQLException;

	int getUserCount() throws SQLException;

	PageNavigation makePageNavigation(Map<String, Object> map) throws Exception;

	void saveRefreshToken(String userid, String refreshToken) throws Exception;

	UserDto userInfo(String userid) throws Exception;

	void deleRefreshToken(String userid) throws SQLException;

	Object getRefreshToken(String userId) throws SQLException;
	
	void insertTmpNumAndSendEmail(UserDto userDto) throws Exception;
	
}

