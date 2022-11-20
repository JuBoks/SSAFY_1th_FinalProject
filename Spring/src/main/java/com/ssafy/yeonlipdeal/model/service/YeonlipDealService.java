package com.ssafy.yeonlipdeal.model.service;

import java.util.List;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;

public interface YeonlipDealService {

	List<YeonlipDealDto> listYeonlipDeal(int yeonlipCode);
	
	YeonlipDealDto search(Long no);
	
	boolean insert(YeonlipDealDto yeonlipDealDto);
	
	boolean update(YeonlipDealDto yeonlipDealDto);
	
	boolean delete(Long no);
}
