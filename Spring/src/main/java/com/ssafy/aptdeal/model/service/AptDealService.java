package com.ssafy.aptdeal.model.service;

import java.util.List;

import com.ssafy.aptdeal.model.dto.AptDealDto;

public interface AptDealService {

	List<AptDealDto> listAptDeal(int aptCode);
	
	AptDealDto search(Long no);
	
	boolean insert(AptDealDto aptDealDto);
	
	boolean update(AptDealDto aptDealDto);
	
	boolean delete(Long no);
	
}
