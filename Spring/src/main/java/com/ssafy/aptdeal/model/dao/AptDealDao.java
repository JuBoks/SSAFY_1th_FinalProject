package com.ssafy.aptdeal.model.dao;

import java.util.List;

import com.ssafy.aptdeal.model.dto.AptDealDto;


public interface AptDealDao {
	
	List<AptDealDto> listAptDeal(int aptCode);
	
	AptDealDto search(Long no);
	
	int insert(AptDealDto aptDealDto);
	
	int update(AptDealDto aptDealDto);
	
	int delete(Long no);
	
}
