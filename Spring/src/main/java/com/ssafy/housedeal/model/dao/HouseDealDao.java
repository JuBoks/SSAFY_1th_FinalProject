package com.ssafy.housedeal.model.dao;

import com.ssafy.housedeal.model.dto.HouseDealDto;

public interface HouseDealDao {

	HouseDealDto select(Long aptCode);
	
	int insert(HouseDealDto houseDealDto);
	
	int update(HouseDealDto houseDealDto);
	
	int delete(Long aptCode);
	
}
