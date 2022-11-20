package com.ssafy.yeonlipinfo.model.service;

import java.util.List;

import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;

public interface YeonlipInfoService {
	
	List<YeonlipInfoDto> listYeonlipInfo(String dongCode);
	
	YeonlipInfoDto search(Integer yeonlipCode);
	
	boolean insert(YeonlipInfoDto yeonlipInfoDto);
	
	boolean update(YeonlipInfoDto yeonlipInfoDto);
	
	boolean delete(Integer yeonlipCode);
	
}
