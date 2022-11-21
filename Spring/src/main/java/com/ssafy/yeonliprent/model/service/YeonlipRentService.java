package com.ssafy.yeonliprent.model.service;

import java.util.List;

import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;

public interface YeonlipRentService {
	
	List<YeonlipRentDto> listYeonlipRent(Integer yeonlipCode) throws Exception;
	
	YeonlipRentDto search(Long no) throws Exception;
	
	boolean insert(YeonlipRentDto yeonlipRentDto) throws Exception;
	
	boolean update(YeonlipRentDto yeonlipRentDto) throws Exception;
	
	boolean delete(Long no) throws Exception;
	
}
