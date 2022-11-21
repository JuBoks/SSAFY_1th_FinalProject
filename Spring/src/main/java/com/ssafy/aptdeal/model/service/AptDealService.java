package com.ssafy.aptdeal.model.service;

import java.util.List;

import com.ssafy.aptdeal.model.dto.AptDealDto;

public interface AptDealService {

	List<AptDealDto> listAptDeal(int aptCode) throws Exception;
	
	List<AptDealDto> listAptDealByYM(int aptCode) throws Exception;
	
	AptDealDto search(Long no) throws Exception;
	
	boolean insert(AptDealDto aptDealDto) throws Exception;
	
	boolean update(AptDealDto aptDealDto) throws Exception;
	
	boolean delete(Long no) throws Exception;
	
}
