package com.ssafy.user.model.dao;

import java.sql.SQLException;

import com.ssafy.user.model.dto.TmpNumDto;

public interface TmpNumDao {
	
	TmpNumDto select(String userId) throws SQLException;
	
	int insert(TmpNumDto tmpNumDto) throws SQLException;
	
	int delete(String userId) throws SQLException;
	
}
