package com.ssafy.apt.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.apt.model.dto.AptDto;

public interface AptDao {

	List<AptDto> searchAptList(Map<String, Object> map) throws SQLException;

	int selectAptSearchCount(Map<String, Object> map) throws SQLException;

	int insert(AptDto aptDto) throws SQLException;

	int update(AptDto aptDto) throws SQLException;

	int delete(String aptCode) throws SQLException;

}
