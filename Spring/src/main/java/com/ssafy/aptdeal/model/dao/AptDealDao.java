package com.ssafy.aptdeal.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.aptdeal.model.dto.AptDealDto;


public interface AptDealDao {
	
	List<AptDealDto> listAptDeal(int aptCode) throws SQLException;
	
	List<AptDealDto> listAptDealByYM(int aptCode) throws SQLException;
	
	AptDealDto search(Long no) throws SQLException;
	
	int insert(AptDealDto aptDealDto) throws SQLException;
	
	int update(AptDealDto aptDealDto) throws SQLException;

	int delete(Long no) throws SQLException;

	String getAptDealAvgByMonth(Integer aptCode) throws SQLException;

	List<AptDealDto> getAptDealByMonth(Map<String, Object> param) throws SQLException;
	
}
