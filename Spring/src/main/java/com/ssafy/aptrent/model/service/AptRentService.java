package com.ssafy.aptrent.model.service;

import java.util.List;

import com.ssafy.aptrent.model.dto.AptRentDto;

public interface AptRentService {
	
	List<AptRentDto> listAptRent(Integer aptCode);
	
	AptRentDto search(Long no);
	
	boolean insert(AptRentDto aptRentDto);
	
	boolean update(AptRentDto aptRentDto);
	
	boolean delete(Long no);
	
}
