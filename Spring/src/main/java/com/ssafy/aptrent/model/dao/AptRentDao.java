package com.ssafy.aptrent.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.aptrent.model.dto.AptRentDto;

public interface AptRentDao {

	List<AptRentDto> listAptRent(Integer aptCode) throws SQLException;
	
	AptRentDto search(Long no) throws SQLException;
	
	int insert(AptRentDto aptRentDto) throws SQLException;
	
	int update(AptRentDto aptRentDto) throws SQLException;
	
	int delete(Long no) throws SQLException;
	
}
