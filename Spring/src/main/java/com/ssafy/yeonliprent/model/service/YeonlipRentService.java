package com.ssafy.yeonliprent.model.service;

import java.util.List;

import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;

public interface YeonlipRentService {
	
	List<YeonlipRentDto> listYeonlipRent(Integer yeonlipCode);
	
	YeonlipRentDto search(Long no);
	
	boolean insert(YeonlipRentDto yeonlipRentDto);
	
	boolean update(YeonlipRentDto yeonlipRentDto);
	
	boolean delete(Long no);
	
}
