package com.ssafy.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.PageNavigation;

public interface UserService {

	boolean join(UserDto userDto) throws Exception;

	UserDto login(UserDto userDto) throws Exception;

	UserDto getDetail(String id) throws Exception;

	boolean modify(UserDto userDto) throws Exception;

	boolean remove(String userId) throws Exception;

	public List<UserDto> getUserList(Map<String, Object> map) throws Exception;

	int getUserCount() throws Exception;

	PageNavigation makePageNavigation(Map<String, Object> map) throws Exception;

	void saveRefreshToken(String userid, String refreshToken) throws Exception;

	UserDto userInfo(String userid) throws Exception;

	void deleRefreshToken(String userid) throws Exception;

	Object getRefreshToken(String userId) throws Exception;
	
	void insertTmpNumAndSendEmail(UserDto userDto) throws Exception;

	boolean modifyPwd(Map<String, String> param) throws Exception;
	
}

