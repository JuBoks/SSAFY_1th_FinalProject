package com.ssafy.user.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.user.model.dto.UserDto;

public interface UserDao {

	int insert(UserDto userDto) throws SQLException;

	UserDto login(UserDto userDto) throws SQLException;

	UserDto select(String id) throws SQLException;

	int update(UserDto userDto) throws SQLException;

	int delete(String userId) throws SQLException;
	
	int deleteAll() throws SQLException;

	List<UserDto> selectAll(Map<String, Object> map) throws SQLException;

	int selectCount() throws SQLException;

	int getCountUserList(Map<String, Object> map) throws SQLException; // 글 전체 개수 가져오기
	
}
