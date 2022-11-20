package com.ssafy.aptrent.model.dao;

import java.util.List;

import com.ssafy.aptrent.model.dto.AptRentDto;

public interface AptRentDao {

	List<AptRentDto> listAptRent(Integer aptCode);
	
	AptRentDto search(Long no);
	
	int insert(AptRentDto aptRentDto);
	
	int update(AptRentDto aptRentDto);
	
	int delete(Long no);
	
}
