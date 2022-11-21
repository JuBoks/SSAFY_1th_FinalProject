package com.ssafy.yeonlipdeal.model.service;

import java.util.List;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;

public interface YeonlipDealService {

	List<YeonlipDealDto> listYeonlipDeal(int yeonlipCode) throws Exception;
	
	YeonlipDealDto search(Long no) throws Exception;
	
	boolean insert(YeonlipDealDto yeonlipDealDto) throws Exception;
	
	boolean update(YeonlipDealDto yeonlipDealDto) throws Exception;
	
	boolean delete(Long no) throws Exception;
}
