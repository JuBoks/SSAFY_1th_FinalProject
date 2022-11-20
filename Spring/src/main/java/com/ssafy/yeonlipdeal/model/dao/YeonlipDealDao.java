package com.ssafy.yeonlipdeal.model.dao;

import java.util.List;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;


public interface YeonlipDealDao {

	List<YeonlipDealDto> listYeonlipDeal(int yeonlipCode);
	
	YeonlipDealDto search(Long no);
	
	int insert(YeonlipDealDto yeonlipDealDto);
	
	int update(YeonlipDealDto yeonlipDealDto);
	
	int delete(Long no);
	
}
