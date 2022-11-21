package com.ssafy.aptrent.model.service;

import java.util.List;

import com.ssafy.aptrent.model.dto.AptRentDto;

public interface AptRentService {
	
	List<AptRentDto> listAptRent(Integer aptCode) throws Exception;
	
	AptRentDto search(Long no) throws Exception;
	
	boolean insert(AptRentDto aptRentDto) throws Exception;
	
	boolean update(AptRentDto aptRentDto) throws Exception;
	
	boolean delete(Long no) throws Exception;
	
}
