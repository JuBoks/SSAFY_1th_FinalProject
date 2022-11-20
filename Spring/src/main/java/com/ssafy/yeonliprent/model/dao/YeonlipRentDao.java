package com.ssafy.yeonliprent.model.dao;

import java.util.List;

import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;

public interface YeonlipRentDao {
	
	List<YeonlipRentDto> listYeonlipRent(Integer yeonlipCode);
	
	YeonlipRentDto search(Long no);
	
	int insert(YeonlipRentDto yeonlipRentDto);
	
	int update(YeonlipRentDto yeonlipRentDto);
	
	int delete(Long no);
	
}
