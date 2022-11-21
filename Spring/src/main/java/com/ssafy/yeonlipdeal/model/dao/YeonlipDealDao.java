package com.ssafy.yeonlipdeal.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;


public interface YeonlipDealDao {

	List<YeonlipDealDto> listYeonlipDeal(int yeonlipCode) throws SQLException;
	
	YeonlipDealDto search(Long no) throws SQLException;
	
	int insert(YeonlipDealDto yeonlipDealDto) throws SQLException;
	
	int update(YeonlipDealDto yeonlipDealDto) throws SQLException;
	
	int delete(Long no) throws SQLException;
	
}
