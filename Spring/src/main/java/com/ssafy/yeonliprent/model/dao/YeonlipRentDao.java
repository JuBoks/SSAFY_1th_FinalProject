package com.ssafy.yeonliprent.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;

public interface YeonlipRentDao {
	
	List<YeonlipRentDto> listYeonlipRent(Integer yeonlipCode) throws SQLException;
	
	YeonlipRentDto search(Long no) throws SQLException;
	
	int insert(YeonlipRentDto yeonlipRentDto) throws SQLException;
	
	int update(YeonlipRentDto yeonlipRentDto) throws SQLException;
	
	int delete(Long no) throws SQLException;
	
}
